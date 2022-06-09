package edu.team5.finalproject.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Style {
        
    FOLKLORE_ARGENTINO("Folklore Argentino"),
    ROCK("Rock"),
    ESPAÑOL("Español"),
    ELECTRONICA("Electrónica"),
    CLASICO("Clásico"),
    POP("Pop"),
    INDEPENDIENTE("Independiente"),
    RITMOS_LATINOS("Ritmos Latinos"),
    COUNTRY("Country"),
    JAZZ("Jazz"),
    BLUES("Blues"),
    SOUL("Soul"),
    CUMBIA("Cumbia"),
    MELODICO("Melódico");

    private String name;

}
