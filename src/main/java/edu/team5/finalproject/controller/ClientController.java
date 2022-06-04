package edu.team5.finalproject.controller;

import edu.team5.finalproject.entity.Client;
import edu.team5.finalproject.entity.Contact;
import edu.team5.finalproject.entity.User;
import edu.team5.finalproject.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    public RedirectView create(@RequestParam String nickname , @RequestParam String profileImage, @RequestParam User user){
      //  clientService.create(nickname, profileImage, user);
        return  new RedirectView("/");
    }

    public RedirectView update(Client client, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/");
        clientService.update(client);
        attributes.addFlashAttribute("success","");
        return redirect;

    }

    public ModelAndView getById(Long id){
        ModelAndView mav = new ModelAndView("");
        mav.addObject("client", clientService.getById(id));
        return mav;
    }

    public RedirectView deleteById(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("");
        clientService.deleteById(id);
        return redirect;
    }
}
