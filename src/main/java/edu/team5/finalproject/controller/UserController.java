package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.UserDto;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final GenericModelMapper mapper;

    @GetMapping
    public ModelAndView getUsers(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("table-user");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        List<UserDto> userListDto = mapper.mapAll(userService.getAll(), UserDto.class);

        if(inputFlashMap!= null) mav.addObject("success", inputFlashMap.get("success"));
         mav.addObject("users", userListDto);

         return mav;
    }

    //PreAuthorize("")
    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("form-sign-up-user-admin");              //que form va acá?
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("user", inputFlashMap.get("user"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("user", new UserDto());
        }

        mav.addObject("action", "create");
        return mav;
    }

    //@PreAuthorize("")
    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("");               //que form va acá?
        mav.addObject("user", userService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

   // @PreAuthorize("")
   @PostMapping("/create")
   public RedirectView create(UserDto dto, RedirectAttributes attributes) {
       RedirectView redirect = new RedirectView("/users");

       try {
           userService.create(dto); // createadmin 
           attributes.addFlashAttribute("success", "The operation has been carried out successfully");
       } catch (IllegalArgumentException | MyException e) {
           attributes.addFlashAttribute("user", dto);
           attributes.addFlashAttribute("exception", e.getMessage());
           redirect.setUrl("");                 // redirección acá
       }

       return redirect;
   }

    //@PreAuthorize("")
    @PostMapping("/update")
    public RedirectView update(UserDto dto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/users");

        try{
            userService.update(dto);
            attributes.addFlashAttribute("success", "The operation has been carried out successfully");
        }catch (IllegalArgumentException | MyException e){
            attributes.addFlashAttribute("user", dto);
           attributes.addFlashAttribute("exception", e.getMessage());
           redirect.setUrl("");                       // redirección acá
        }
        return redirect;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public RedirectView updateDeletedHigh(@PathVariable Long id) throws MyException{
        RedirectView redirect = new RedirectView("/"); 
        userService.updateDeletedHigh(id);               
        return redirect;
    }

    //@PreAuthorize("")
    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/users");
        userService.deleteById(id);
        return redirect;
    }
}
