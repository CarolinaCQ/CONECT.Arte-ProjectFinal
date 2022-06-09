package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.ClientUserDto;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.service.ClientService;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;    

    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request, Principal principal){
        ModelAndView mav = new ModelAndView("form-sign-up-client");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if(principal!=null) mav.setViewName("/"); // modificar
        
        if(inputFlashMap!=null){
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("client", inputFlashMap.get("client"));
        } else{
            mav.addObject("client", new ClientUserDto());
        }
        return mav;
    }
 

    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("form-sign-up-client");        
        mav.addObject("client");
        mav.addObject("action", "update");
        return mav;
    }

    @PostMapping("/create")
    public RedirectView create(@RequestParam ClientUserDto dto, RedirectAttributes attributes) throws MyException{
        clientService.create(dto);    
        return new RedirectView("/"); //MODIFICAR RE-DIRECCION
    }


    @PostMapping("/update")
    public RedirectView update(ClientUserDto dto, RedirectAttributes attributes) throws MyException{
        RedirectView redirect = new RedirectView("/"); //MODIFICAR RE-DIRECCION
        clientService.update(dto);                
        attributes.addFlashAttribute("success","mensaje de exito"); // MODIFICAR MENSAJE 
        return redirect;
    }

/* 
    @GetMapping
    public ModelAndView getById(Long id){
        ModelAndView mav = new ModelAndView("");
        mav.addObject("client", clientService.getById(id));
        return mav;
    }

    @PostMapping
    public RedirectView deleteById(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("");
        clientService.deleteById(id);
        return redirect;
    }
*/
    
}
