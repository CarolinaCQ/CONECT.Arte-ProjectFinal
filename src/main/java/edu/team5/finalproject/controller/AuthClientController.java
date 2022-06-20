package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.ClientUserDto;
import edu.team5.finalproject.dto.UserDto;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.service.ClientService;
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
@RequestMapping("/auth-client")
@RequiredArgsConstructor
public class AuthClientController {

    private final UserService userService;
    private final ClientService clientService;
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
        ModelAndView mav = new ModelAndView("form-sign-up-client");                          
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if(principal != null) mav.setViewName("redirect:/");

        if(inputFlashMap !=null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("client", inputFlashMap.get("client"));           
        }else {
            mav.addObject("client", new ClientUserDto());            
        }
        mav.addObject("action", "register");
        return mav;
    }

    @PostMapping("/register")
    public RedirectView signup(ClientUserDto dto, @RequestParam(required = false) MultipartFile image, RedirectAttributes attributes) { 
        RedirectView redirect = new RedirectView("/");
        
        UserDto userDto = mapper.map(dto, UserDto.class);
        ClientUserDto clientUserDto = mapper.map(dto, ClientUserDto.class);

        try {            
            if(userDto.getUserEmail() != null) userService.create(userDto);            
            if(clientUserDto.getClientNickname() != null) clientService.create(clientUserDto, image);         
            
        } catch (IllegalArgumentException | MyException e) {
            attributes.addFlashAttribute("client", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/auth-client/sign-up");
        }

        return redirect;
    }
}
