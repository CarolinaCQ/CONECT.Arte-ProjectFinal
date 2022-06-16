package edu.team5.finalproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupSimpleDto {
    
    private Long groupId;
	private String groupName;
	private String groupDescription;
	private String groupProfileImage;
	private Boolean groupDeleted;  
}
