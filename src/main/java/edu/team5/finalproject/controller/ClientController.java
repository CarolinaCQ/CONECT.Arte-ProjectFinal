package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.ClientUserDto;
import edu.team5.finalproject.entity.Client;
import edu.team5.finalproject.entity.enums.Role;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.service.ClientService;
import edu.team5.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService; 
    private final UserService userService;
    private final GenericModelMapper mapper;  
 
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("form-sign-up-client"); 
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        
        ClientUserDto clientUserDto = mapper.map(clientService.getById(id), ClientUserDto.class);

        if(inputFlashMap!= null) mav.addObject("exception", inputFlashMap.get("exception"));
       
        
        mav.addObject("client", clientUserDto);
        mav.addObject("action", "update");
        
        return mav;
    }

    @GetMapping("/profile")
    public ModelAndView getProfile(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("profile-client");
        ClientUserDto clientUserDto = mapper.map(clientService.getByIdUser(id), ClientUserDto.class);

        mav.addObject("client", clientUserDto);
        
        return mav;
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/update")
    public RedirectView update(ClientUserDto dto,@RequestParam(required = false) MultipartFile image, RedirectAttributes attributes) throws MyException{
        RedirectView redirect = new RedirectView("/clients/profile/" + dto.getId().toString());

        try{
            clientService.update(dto, image);            
        }catch(MyException e){
            attributes.addFlashAttribute("exception", e.getMessage());
            attributes.addFlashAttribute("client", dto);
            redirect.setUrl("/clients/form/" + dto.getId().toString());
        }
        return redirect;
    }    

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public RedirectView updateDeletedHigh(@PathVariable Long id) throws MyException{
        RedirectView redirect = new RedirectView("/users");
        userService.updateEnableById(clientService.getById(id).getUser().getId()); 
        clientService.updateEnableById(id);                
        return redirect;
    }    

    @PreAuthorize("anyRole('CLIENT, ADMIN')")
    @PostMapping("/delete/{id}")
    public RedirectView deleteById(@PathVariable Long id, @RequestParam(value="role") Role role) {
        String url = (role==Role.ADMIN) ? "/users" : "/logout";
    
        RedirectView redirect = new RedirectView(url);
        
        userService.deleteById(clientService.getById(id).getUser().getId());
        clientService.deleteById(id);
        return redirect;
    }
}