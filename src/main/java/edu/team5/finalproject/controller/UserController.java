package edu.team5.finalproject.controller;

import edu.team5.finalproject.dto.UserDto;
import edu.team5.finalproject.entity.Client;
import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.entity.User;
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
import org.springframework.web.bind.annotation.RequestParam;

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
    public ModelAndView getUsers(@RequestParam(value = "boolean", required = false) Boolean delete) {
        ModelAndView mav = new ModelAndView("table-user");
        
        if(delete==null) delete = false;
        

        List<User> users = userService.getAllAdmin(delete);
        List<Client> clients = clientService.getByBoolean(delete);
        List<Group> groups = groupService.getByBoolean(delete);
    
        mav.addObject("users", users);
        mav.addObject("clients", clients);
        mav.addObject("groups", groups);

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
