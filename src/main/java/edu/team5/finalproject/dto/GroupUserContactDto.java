package edu.team5.finalproject.dto;

import java.util.List;

import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupUserContactDto extends UserDto{

    //atributes Group
    private String groupName;
    private String groupProfileImage;
    private String groupDescription;
    private String groupStyle;
    private Boolean groupMobility;
    private List<String> groupService; // quitar
    private List<String> groupImageList; // quitar para mostrar en otra viste (otro formulario)
    private Type groupType;
    private Locale groupLocale;
    
    //atributes Contact
    private String contactFacebookUrl;  
    private String contactInstagramUrl;
    private Long contactWhatsAppNumber;

}
