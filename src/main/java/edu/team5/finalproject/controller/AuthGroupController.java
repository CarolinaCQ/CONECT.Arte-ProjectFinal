package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.ContactDto;
import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.dto.UserDto;
import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Style;
import edu.team5.finalproject.entity.enums.Type;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.service.ContactService;
import edu.team5.finalproject.service.GroupService;
import edu.team5.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/auth-group")
@RequiredArgsConstructor
public class AuthGroupController {
    
    private final UserService userService;
    private final GroupService groupService;
    private final ContactService contactService;
    private final GenericModelMapper mapper;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal){
        ModelAndView mav = new ModelAndView("form-login");

        if(error != null) mav.addObject("error","Email o contraseña inválido");
        if(logout != null) mav.addObject("logout", "Ha salido con éxito de la plataforma");
        if(principal != null) mav.setViewName("redirect:/");

        return  mav;
    }
    
    @GetMapping("/sign-up")
    public ModelAndView signup(HttpServletRequest request, Principal principal){
        ModelAndView mav = new ModelAndView("form-sign-up-group");                          
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if(principal != null) mav.setViewName("redirect:/");

        if(inputFlashMap !=null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("group", inputFlashMap.get("group"));           
        }else {
            mav.addObject("group", new GroupUserContactDto());           
        }

        mav.addObject("types", Type.values());
        mav.addObject("styles", Style.values());
        mav.addObject("locales", Locale.values());
        mav.addObject("action", "register");
        
        return mav;
    }

    @PostMapping("/register")
    public RedirectView signup(GroupUserContactDto dto, @RequestParam(required = false) MultipartFile image, RedirectAttributes attributes) { 
        RedirectView redirect = new RedirectView("/");
    
        try {
            groupService.validateGroupDto(dto);

            UserDto userDto = mapper.map(dto, UserDto.class);        
            ContactDto contactDto = mapper.map(dto, ContactDto.class);

            userService.create(userDto);
            contactService.create(contactDto);                        
            groupService.create(dto, image);
            
        } catch (IllegalArgumentException | MyException e) {
            attributes.addFlashAttribute("group", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/auth-group/sign-up");
        }
        return redirect;
    }

}
