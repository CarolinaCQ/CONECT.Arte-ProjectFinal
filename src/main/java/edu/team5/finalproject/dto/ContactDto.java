package edu.team5.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto {
    
    private String contactFacebookUrl;  
    private String contactInstagramUrl;
    private Long contactWhatsAppNumber;


}
