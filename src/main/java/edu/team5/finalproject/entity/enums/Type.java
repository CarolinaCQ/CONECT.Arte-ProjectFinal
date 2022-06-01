package edu.team5.finalproject.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Type {

    MUSIC("Solistas y bandas musicales"),
    DANCE("Solistas y grupos de danza");

    private String name;

}
