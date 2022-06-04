package edu.team5.finalproject.dto;

import java.io.Serializable;
import java.util.List;

import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Role;
import edu.team5.finalproject.entity.enums.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupUserContactDto implements Serializable{

    //atributes Group
    private String name;
    private String profileImage;
    private String description;
    private String style;
    private Boolean mobility;
    private List<String> service; // quitar
    private List<String> imageList; // quitar para mostrar en otra viste (otro formulario)
    private Type type;
    private Locale locale;
    
    //atributes User
    private String email;
    private String password;
    private Role role;

    //atributes Contact
    private String facebookUrl;  
    private String instagramUrl;
    private Long whatsAppNumber;

}
