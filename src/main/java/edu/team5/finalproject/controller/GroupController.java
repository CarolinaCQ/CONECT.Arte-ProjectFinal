package edu.team5.finalproject.controller;


import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public RedirectView create(Group group){
        //groupService.create(group);
        return  new RedirectView("/");
    }

    @PostMapping("/")
    public RedirectView update(Group group, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/");
        //groupService.update(group);
        attributes.addFlashAttribute("success","");
        return redirect;

    }
    @GetMapping
    public ModelAndView getById(Long id){
        ModelAndView mav = new ModelAndView("");
        mav.addObject("contact", groupService.getById(id));
        return mav;
    }

    @PostMapping
    public RedirectView deleteById(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("");
        groupService.deleteById(id);
        return redirect;
    }
}
