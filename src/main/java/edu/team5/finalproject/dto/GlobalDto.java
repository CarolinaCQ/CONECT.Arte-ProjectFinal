package edu.team5.finalproject.dto;

import java.util.List;

import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Role;
import edu.team5.finalproject.entity.enums.Style;
import edu.team5.finalproject.entity.enums.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GlobalDto {

    private Long id;
    
    //user
    private String userEmail;
    private String userPassword;
    private Role userRole;

    //group
    private String groupName;
    private String groupProfileImage;
    private String groupDescription;    
    private Boolean groupMobility;
    private List<String> groupService; 
    private List<String> groupImageList;
    private Style groupStyle; 
    private Type groupType;
    private Locale groupLocale;
    
    //contact
    private String contactFacebookUrl;  
    private String contactInstagramUrl;
    private Long contactWhatsAppNumber;

    //client
    private String clientNickname;    
    private String clientProfileImage;

}
