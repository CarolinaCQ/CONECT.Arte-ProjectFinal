package edu.team5.finalproject.mapper;

import org.modelmapper.ModelMapper;

import edu.team5.finalproject.dto.ClientUserDto;
import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.entity.Client;
import edu.team5.finalproject.entity.Contact;
import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.entity.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GenericModelMapper {

   private final ModelMapper mapper;

   /* al map se le pasa el origen y destino. (como la entidad Client contiene un obj User y nuestro dto contiene los atributos de User, 
                                              la librería ModelMaper se encarga de buscar los nombres de atributos que coincidan entre
                                              ambas entidades, como estamos usando el mismo nombre de atributo en el dto y entidad se genera
                                              automáticamente la relación, en caso de que los nombres de atributos difieran, hay que realizar
                                              un mapeo extra dentro del método para relacionar los atributos de distinto nombre.)
                                              explicar (la característica "Reflection")
   */

   public ClientUserDto mapToClientUserDto(Client client){       
        return mapper.map(client, ClientUserDto.class);
   }

   public GroupUserContactDto mapToGroupUserContactDto(Group group){
      return mapper.map(group, GroupUserContactDto.class);
   }

   //REVERSES

   public Client mapToClient(ClientUserDto dto){
      return mapper.map(dto, Client.class);
   }

   public User mapToUser(ClientUserDto dto){
      return mapper.map(dto, User.class);
   }

   public Group mapToGroup(GroupUserContactDto dto){
      return mapper.map(dto, Group.class);
   }

   public Contact mapToContact(GroupUserContactDto dto){
      return mapper.map(dto, Contact.class);
   }

   public User mapToUserFromGroup(GroupUserContactDto dto){
      return mapper.map(dto, User.class);
   }
    
}
