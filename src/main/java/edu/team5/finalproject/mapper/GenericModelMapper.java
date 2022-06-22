package edu.team5.finalproject.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.entity.Group;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class GenericModelMapper implements Serializable{

   private final ModelMapper mapper;

   public <S, D> D map(S sourceClass, Class<D> destinationClass) {
      return mapper.map(sourceClass, destinationClass);      
   } 

   public <S, D> List<D> mapAll(List<S> sourceList, Class<D> destinationClass) {
      return sourceList.stream()
                       .map(e -> map(e, destinationClass))
                       .collect(Collectors.toList());      
   }

   public Group mapSkipping(GroupUserContactDto dto) {
      return mapper.typeMap(GroupUserContactDto.class, Group.class)
                   .addMappings(m -> m.skip(Group::setProfileImage))
                   .map(dto);    
   }
    
}



