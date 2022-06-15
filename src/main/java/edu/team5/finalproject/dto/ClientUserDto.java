package edu.team5.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientUserDto extends UserDto{

    private String clientNickname;    
    private String clientProfileImage;
    private Boolean clientDeleted;
    
}
