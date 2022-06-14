package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.GroupSimpleDto;
import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Style;
import edu.team5.finalproject.entity.enums.Type;
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
        ModelAndView mav = new ModelAndView("artists");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        List<GroupSimpleDto> groupListDto = mapper.mapAll(groupService.getAll(), GroupSimpleDto.class);

        if(inputFlashMap!= null) mav.addObject("success", inputFlashMap.get("success"));
         mav.addObject("groups", groupListDto);

         return mav;
    }

    @GetMapping("/profile/{id}")
    public ModelAndView getProfile(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("profile-artist");
        GroupUserContactDto groupUserContactDto = mapper.map(groupService.getById(id), GroupUserContactDto.class);

        mav.addObject("group", groupUserContactDto);
        
        return mav;
    }

    @PreAuthorize("hasRole('GROUP')")
    @GetMapping("/form/{id}")
    public ModelAndView getForm(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("form-sign-up-group");  
        GroupUserContactDto groupUserContactDto = mapper.map(groupService.getById(id), GroupUserContactDto.class);      
        mav.addObject("group", groupUserContactDto);
        mav.addObject("types", Type.values());
        mav.addObject("locales", Locale.values());
        mav.addObject("styles", Style.values());
        mav.addObject("action", "update");
        return mav;
    }

    @PreAuthorize("hasRole('GROUP')")
    @PostMapping("/update")
    public RedirectView update(GroupUserContactDto dto, RedirectAttributes attributes) throws MyException{
        RedirectView redirect = new RedirectView("/groups/profile/{id}");
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

    @PreAuthorize("anyRole('GROUP')")
    @PostMapping("/delete/{id}")
    public RedirectView deleteById(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/logout");
        groupService.deleteById(id);
        return redirect;
    }
}
