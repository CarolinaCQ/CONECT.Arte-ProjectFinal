package edu.team5.finalproject.service;

import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.entity.Contact;
import edu.team5.finalproject.exception.MyException;
import edu.team5.finalproject.mapper.GenericModelMapper;
import edu.team5.finalproject.repository.ContactRepository;
import edu.team5.finalproject.utility.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactService {

   private final GenericModelMapper mapper;
   private final ContactRepository contactRepository;

   @Transactional
   public void create(GroupUserContactDto dto) throws MyException {
      Contact contact = mapper.mapToContact(dto);      
      validateContact(contact);  
      contactRepository.save(contact);
   }

   @Transactional
   public void update(GroupUserContactDto dto) throws MyException {
      Contact contact = mapper.mapToContact(dto);      
      validateContact(contact);  
      contactRepository.save(contact);
   }

   @Transactional
   public Contact getById(Long id) {
      return contactRepository.findById(id).get();
   }

   @Transactional
   public void deleteById(Long id) {
      contactRepository.deleteById(id);
   }

   private void validateContact(Contact newContact) throws MyException {
      if (newContact == null) throw new MyException("Exception message here.");
      Utility.validate(Utility.ONLY_NUMBERS, newContact.getWhatsAppNumber().toString()); // consultar como hacer esto
   }

}
