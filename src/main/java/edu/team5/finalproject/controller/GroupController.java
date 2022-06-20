package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.GroupSimpleDto;
import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Style;
import edu.team5.finalproject.entity.enums.Type;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.service.ContactService;
import edu.team5.finalproject.service.GroupService;
import edu.team5.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import java.util.List;
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
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final UserService userService;
    private final ContactService contactService;
    private final GenericModelMapper mapper;
    
    @PreAuthorize("hasRole('GROUP')")
    @GetMapping
    public ModelAndView getGroups(){
        ModelAndView mav = new ModelAndView("artists");

        List<GroupSimpleDto> groupListDto = mapper.mapAll(groupService.getAll(), GroupSimpleDto.class);

        mav.addObject("groups", groupListDto);

        return mav;
    } 
    
    @PreAuthorize("hasRole('GROUP')")
    @GetMapping("/dance")
    public ModelAndView getGroupsDance(@RequestParam(required = false) Style style,  @RequestParam(required = false) Locale locale){
        ModelAndView mav = new ModelAndView("");

        List<GroupSimpleDto> groupListDto = mapper.mapAll(groupService.getAllDance(), GroupSimpleDto.class);
        List<GroupSimpleDto> groupListDtoDance = mapper.mapAll(groupService.getByStyleAndLocaleDance(style, locale), GroupSimpleDto.class);

        if(style!=null && locale!=null){
            if(groupListDtoDance.isEmpty()){
                mav.addObject("isEmpty", "No se encuentran grupos con esos filtros");
            }else{
                mav.addObject("groups", groupListDtoDance);
            }
        } else{
            mav.addObject("groups", groupListDto);
        }

        mav.addObject("locales", Locale.values());
        mav.addObject("styles", Style.values());
        
        return mav;
    } 
    
    @PreAuthorize("hasRole('GROUP')")
    @GetMapping("/music")
    public ModelAndView getGroupsMusic( @RequestParam(required = false) Style style,  @RequestParam(required = false) Locale locale){
        ModelAndView mav = new ModelAndView("");
        
        List<GroupSimpleDto> groupListDto = mapper.mapAll(groupService.getAllMusic(), GroupSimpleDto.class);
        List<GroupSimpleDto> groupListDtoMusic = mapper.mapAll(groupService.getByStyleAndLocaleMusic(style, locale), GroupSimpleDto.class);

        
        if(style!=null && locale!=null){
            if(groupListDtoMusic.isEmpty()){
                mav.addObject("isEmpty", "No se encuentran grupos con esos filtros");
            }else{
                mav.addObject("groups", groupListDtoMusic);
            }
        } else{
            mav.addObject("groups", groupListDto);
        }

        mav.addObject("locales", Locale.values());
        mav.addObject("styles", Style.values());

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
    public ModelAndView getForm(@PathVariable Long id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("form-sign-up-group");
        
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        
        GroupUserContactDto groupUserContactDto = mapper.map(groupService.getById(id), GroupUserContactDto.class);   
        
        if(inputFlashMap !=null) {
            mav.addObject("exception", inputFlashMap.get("exception"));
            mav.addObject("group", inputFlashMap.get("group"));           
        }else {
            mav.addObject("client", groupUserContactDto);            
        }
        
        mav.addObject("types", Type.values());
        mav.addObject("locales", Locale.values());
        mav.addObject("styles", Style.values());
        mav.addObject("action", "update");
        
        return mav;
    }
    
    @PreAuthorize("hasRole('GROUP')")
    @PostMapping("/update")
    public RedirectView update(GroupUserContactDto dto, @RequestParam(required = false) MultipartFile image, @RequestParam(required = false) List<MultipartFile> imageList, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/groups/profile/" + dto.getId().toString());
        
        try {
            groupService.update(dto, image, imageList);    
        } catch (MyException e) {
            attributes.addFlashAttribute("group", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/groups/form" + dto.getId().toString());
        }
        
        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public RedirectView updateDeletedHigh(@PathVariable Long id){
        RedirectView redirect = new RedirectView("/users");
        userService.updateEnableById(groupService.getById(id).getUser().getId());
        contactService.updateEnableById(groupService.getById(id).getContact().getId());
        groupService.updateEnableById(id);               
        return redirect;
    }

    @PreAuthorize("anyRole('GROUP', 'ADMIN')")
    @PostMapping("/delete/{id}")
    public RedirectView deleteById(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/logout");
        userService.deleteById(groupService.getById(id).getUser().getId());
        contactService.deleteById(groupService.getById(id).getContact().getId());
        groupService.deleteById(id);
        return redirect;
    }
}
