package edu.team5.finalproject.mapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class GenericModelMapper implements Serializable{

   private final ModelMapper mapper;

   public <S, D> D map(S sourceClass, Class<D> destinationClass) {
      return mapper.map(sourceClass, destinationClass);      
   } 

   public <S, D> List<D> mapList(List<S> sourceList, Class<D> destinationClass) {
      return sourceList.stream()
                       .map(e -> map(e, destinationClass))
                       .collect(Collectors.toList());      
   }
    
}


