package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.ClientUserDto;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService; 
    private final GenericModelMapper mapper;   
 
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("form-sign-up-client"); 
        
        ClientUserDto clientUserDto = mapper.map(clientService.getById(id), ClientUserDto.class);

        mav.addObject("client", clientUserDto);
        mav.addObject("action", "update");
        return mav;
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/update")
    public RedirectView update(ClientUserDto dto, RedirectAttributes attributes) throws MyException{
        RedirectView redirect = new RedirectView("/"); //MODIFICAR RE-DIRECCION
        clientService.update(dto);                
        attributes.addFlashAttribute("success","mensaje de exito"); // MODIFICAR MENSAJE 
        return redirect;
    }    

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public RedirectView updateDeletedHigh(@PathVariable Long id) throws MyException{
        RedirectView redirect = new RedirectView("/"); 
        clientService.updateDeletedHigh(id);               
        return redirect;
    }

    @PreAuthorize("anyRole('CLIENT, ADMIN')")
    @PostMapping("/delete/{id}")
    public RedirectView deleteById(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("");
        clientService.deleteById(id);
        return redirect;
    }
}