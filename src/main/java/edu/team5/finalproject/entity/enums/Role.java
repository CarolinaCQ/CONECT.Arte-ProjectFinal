package edu.team5.finalproject.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {

    CLIENT("Client"),
    GROUP("Group"),
    ADMIN("Admin");

    private String name;

}