package edu.team5.finalproject.controller;

import edu.team5.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
//@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //@GetMapping
    public ModelAndView getUser(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if(inputFlashMap!= null) mav.addObject("success", inputFlashMap.get("success"));
         mav.addObject("users", userService.getAll());
         return mav;
    }

    //PreAuthorize("")
    //@GetMapping
    public ModelAndView getForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("user", inputFlashMap.get("user"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("user", new User());
        }

        mav.addObject("action", "create");
        return mav;
    }

    //@PreAuthorize("")
   // @GetMapping("//{id}")
    public ModelAndView getForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("");
        mav.addObject("user", userService.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

   // @PreAuthorize("")
   // @PostMapping("/")
    public RedirectView create(User user1, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("");
        //try{
        //userService.create(user1);
        attributes.addFlashAttribute("Success", "");
        //catch(Exception e){
        attributes.addFlashAttribute("user", user1);
        // attributes.addFlashAttribute("exception",message );
        redirect.setUrl("");
        return redirect;
    }

    //@PreAuthorize("")
   //@PostMapping("/")
    public RedirectView update(User user1, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("");
       // userService.update(user1);
        attributes.addFlashAttribute("success", "");
        return redirect;
    }
    //@PreAuthorize("")
    //@PostMapping("//{id}")
    public RedirectView delete(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("");
        userService.deleteById(id);
        return redirect;
    }
}
