package edu.team5.finalproject.dto;

import java.io.Serializable;

import edu.team5.finalproject.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable{
    
    private Long id;
    
    private String userEmail;
    private String userPassword;
    private Boolean deleted; 
    private Role userRole;

}
