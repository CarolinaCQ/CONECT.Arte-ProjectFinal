package edu.team5.finalproject.service;

import edu.team5.finalproject.dto.ContactDto;
import edu.team5.finalproject.dto.GroupUserContactDto;
import edu.team5.finalproject.entity.Contact;
import edu.team5.finalproject.exception.ExceptionMessages;
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
   public void create(ContactDto dto) throws MyException {
      Contact contact = mapper.map(dto, Contact.class);    

      validateContact(contact); 
      
      contact.setDeleted(false);
      contactRepository.save(contact);
   }

   @Transactional
   public void update(ContactDto dto) throws MyException {
      Contact contact = mapper.map(dto, Contact.class);      
      validateContact(contact);  
      contactRepository.save(contact);
   }

   @Transactional
   public Contact getById(Long id) {
      return contactRepository.findById(id).get();
   }

   @Transactional
   public void updateEnableById(Long id) {
       contactRepository.enableById(id);
   }

   @Transactional
   public void deleteById(Long id) {
      contactRepository.deleteById(id);
   }

   private void validateContact(Contact contact) throws MyException {
      if(!Utility.validate(Utility.ONLY_NUMBERS_PATTERN, contact.getWhatsAppNumber().toString())) 
         throw new MyException(ExceptionMessages.INVALID_NUMBER.get()); 

      if (contactRepository.existsByWhatsAppNumber(contact.getWhatsAppNumber())) 
         throw new MyException(ExceptionMessages.ALREADY_EXISTS_WHATSAPP_NUMBER.get());
   }

}
