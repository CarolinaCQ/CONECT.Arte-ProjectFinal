package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.ClientUserDto;
import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.dto.UserDto;
import edu.team5.finalproject.exception.ExceptionMessages;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.service.ClientService;
import edu.team5.finalproject.service.GroupService;
import edu.team5.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
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

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ClientService clientService;
    private final GroupService groupService;
    private final GenericModelMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ModelAndView getUsers(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("table-user");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        List<UserDto> userAdmin= mapper.mapAll(userService.getAllAdmin(), UserDto.class);
        List<ClientUserDto> clientUserDto = mapper.mapAll(clientService.getAll(), ClientUserDto.class);
        List<GroupUserContactDto> groupUserContactDto = mapper.mapAll(groupService.getAll(), GroupUserContactDto.class);

        if (inputFlashMap != null)
            mav.addObject("success", inputFlashMap.get("success"));
            mav.addObject("users", userAdmin);
            mav.addObject("clients", clientUserDto);
            mav.addObject("groups", groupUserContactDto);

        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("form-sign-up-user-admin"); // que form va acá?
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) {
            mav.addObject("user", inputFlashMap.get("user"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        } else {
            mav.addObject("user", new UserDto());
        }

        mav.addObject("action", "create");
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public RedirectView create(UserDto dto, RedirectAttributes attributes) {
        RedirectView redirect = new RedirectView("/users");

        try {
            userService.createAdmin(dto); 
            attributes.addFlashAttribute("success", ExceptionMessages.SUCCESS_STATUS_CODE_200.get());
        } catch (IllegalArgumentException | MyException e) {
            attributes.addFlashAttribute("user", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/users");
        }
        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update/{id}")
    public RedirectView updateDeletedHigh(@PathVariable Long id) throws MyException {
        RedirectView redirect = new RedirectView("/users");
        userService.updateEnableById(id);
        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Long id) {
        RedirectView redirect = new RedirectView("/users");
        userService.deleteById(id);
        return redirect;
    }
}
