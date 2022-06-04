package edu.team5.finalproject.dto;

import java.io.Serializable;


import edu.team5.finalproject.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientUserDto implements Serializable{

    private Long id;
    private String nickname; 
    private String email;
    private String password;
    private Role role;
    private String profileImage;
    

    /*
     * aca van los atributos de la/s tablas 
     * este objeto debe ser plano, no debe tener lógica, solo atributos, contructores , getters y setters. 
     * que queremos transportar por las distintas capas 
     */
    
}
