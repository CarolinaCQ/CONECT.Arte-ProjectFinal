package edu.team5.finalproject.controller;

import edu.team5.finalproject.entity.Contact;
import edu.team5.finalproject.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    public ModelAndView getById(Long id){
        ModelAndView mav = new ModelAndView("");
        mav.addObject("contact", contactService.getById(id));
        return mav;
    }

    public ModelAndView getForm(){
        return  new ModelAndView("");
    }

    public RedirectView create(@RequestParam String facebookUrl, @RequestParam String instagramUrl, @RequestParam Long whatsAppNumber){
       // contactService.create(facebookUrl, instagramUrl, whatsAppNumber);
        return  new RedirectView("/");
    }

    public RedirectView update(Contact contact, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/");
        //contactService.update(contact);
        attributes.addFlashAttribute("success","");
        return redirect;

    }

    public RedirectView delete(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("");
        contactService.deleteById(id);
        return redirect;
    }
}
