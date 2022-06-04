package edu.team5.finalproject.controller;

import edu.team5.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    //@GetMapping
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal){
        ModelAndView mav = new ModelAndView("");
        if(error != null) mav.addObject("error","Invalid email or password");
        if(logout != null) mav.addObject("logout", "You have successfully exisd the platform");
        if(principal != null) mav.setViewName("redirect:/");
        return  mav;

    }
     //@GetMapping
    public ModelAndView signup(HttpServletRequest request, Principal principal){
        ModelAndView mav = new ModelAndView("");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if(principal != null) mav.setViewName("redirect:/");
        if(inputFlashMap !=null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("user", inputFlashMap.get("user"));
        }else {
            mav.addObject("user", new User());
        }
        return mav;
    }

    //@PostMapping
    public RedirectView signup(User user1, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/");
        //try{
        //  userService.create(user1);
    //catch(Exeption e){
        attributes.addFlashAttribute("user",user1);
        attributes.addFlashAttribute("exception");
        redirect.setUrl("");
        return redirect;
    }
}
