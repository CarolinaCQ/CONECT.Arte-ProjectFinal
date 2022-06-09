package edu.team5.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientUserDto extends UserDto{

    //client atributes
    private String clientNickname;    
    private String clientProfileImage;

    /*
     * aca van los atributos de la/s tablas 
     * este objeto debe ser plano, no debe tener lógica, solo atributos, contructores , getters y setters. 
     * que queremos transportar por las distintas capas 
     */
    
}
