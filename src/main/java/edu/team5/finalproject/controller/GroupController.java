package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.service.GroupService;
import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GenericModelMapper mapper;

    @PreAuthorize("hasRole('GROUP')")
    @GetMapping
    public ModelAndView getGroups(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        List<GroupUserContactDto> groupListDto = mapper.mapList(groupService.getAll(), GroupUserContactDto.class);

        if(inputFlashMap!= null) mav.addObject("success", inputFlashMap.get("success"));
         mav.addObject("groups", groupListDto);

         return mav;
    }

    @PreAuthorize("hasRole('GROUP')")
    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request, Principal principal){
        ModelAndView mav = new ModelAndView("form-sign-up-group");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if(principal!=null) mav.setViewName("/"); // modificar
        
        if(inputFlashMap!=null){
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("group", inputFlashMap.get("group"));
        } else{
            mav.addObject("group", new GroupUserContactDto());
        }
        return mav;
    }

    @GetMapping("/profile")
    public ModelAndView getProfile(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("profile-artist");
        GroupUserContactDto groupDto = mapper.map(groupService.getById(id), GroupUserContactDto.class);

        mav.addObject("group", groupDto);
        mav.addObject("action", "profile");

        return mav;
    }

    @PostMapping("/profile/{id}")
    public RedirectView profile(Long id, RedirectAttributes attributes) throws MyException{
        RedirectView redirect = new RedirectView("/profile-artist");                
        attributes.addFlashAttribute("success","mensaje de exito"); // MODIFICAR MENSAJE 
        return redirect;
    }

    @PreAuthorize("hasRole('GROUP')")
    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("form-sign-up-group");        
        mav.addObject("group");
        mav.addObject("action", "update");
        return mav;
    }

    @PreAuthorize("hasRole('GROUP')")
    @PostMapping("/update")
    public RedirectView update(GroupUserContactDto dto, RedirectAttributes attributes) throws MyException{
        RedirectView redirect = new RedirectView("/"); //MODIFICAR RE-DIRECCION
        groupService.update(dto);                
        attributes.addFlashAttribute("success","mensaje de exito"); // MODIFICAR MENSAJE 
        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public RedirectView updateDeletedHigh(@PathVariable Long id) throws MyException{
        RedirectView redirect = new RedirectView("/"); 
        groupService.updateDeletedHigh(id);               
        return redirect;
    }

    @PreAuthorize("anyRole('GROUP, ADMIN')")
    @PostMapping("/delete/{id}")
    public RedirectView deleteById(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("");
        groupService.deleteById(id);
        return redirect;
    }
}
