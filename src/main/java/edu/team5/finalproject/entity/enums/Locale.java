package edu.team5.finalproject.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Locale {

    APOSTOLES("Apóstoles"),
    CAINGUAS("Cainguás"),
    CANDELARIA("Candelaria"),
    CAPITAL("Capital"),
    CONCEPCION("Concepción"),
    ELDORADO("Eldorado"),
    GRAL_MANUEL_BELGRANO("Gral. Manuel Belgrano"),
    GUARANI("Guaraní"),
    IGUAZU("Iguazú"),
    LEANDRO_N_ALEM("Leandro N. Alem"),
    LIBERTADOR_GRAL_SAN_MARTIN("Libertador Gral. Sant Martín"),
    MONTECARLO("Montecarlo"),
    OBERA("Oberá"),
    SAN_IGNACIO("San Ignacio"),
    SAN_JAVIER("San Javier"),
    SAN_PEDRO("San Pedro"),
    VEINTICINCO_DE_MAYO("25 de Mayo");

    private String name;

}
