package edu.team5.finalproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.service.GroupService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PrincipalController {

    private final GroupService groupService;
    private final GenericModelMapper mapper;

    @GetMapping
    public ModelAndView getIndex() {
        ModelAndView mav = new ModelAndView ("index");
        return mav;
    }
}


 