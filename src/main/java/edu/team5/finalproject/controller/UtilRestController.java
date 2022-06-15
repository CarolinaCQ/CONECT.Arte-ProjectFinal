package edu.team5.finalproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.service.GroupService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/get-all")
@RequiredArgsConstructor
public class UtilRestController {
    
    private final GroupService groupService;

    @GetMapping
    public List<Group> getAll(){
        return groupService.getAll();
    }
}
