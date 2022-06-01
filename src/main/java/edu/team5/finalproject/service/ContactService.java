package edu.team5.finalproject.service;

import edu.team5.finalproject.entity.Contact;
import edu.team5.finalproject.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactService {

private final ContactRepository contactRepository;

@Transactional
 public void create(Contact contact1){

     Contact contact = new Contact();
     contact.setFacebookUrl(contact1.getFacebookUrl());
     contact.setInstagramUrl(contact1.getInstagramUrl());
     contact.setWhatsAppNumber(contact1.getWhatsAppNumber());

     contactRepository.save(contact);
}
@Transactional
 public void update(Contact contact1){
 Contact contact = ContactRepository.finById(contact1.getId());
     contact.setFacebookUrl(contact1.getFacebookUrl());
     contact.setInstagramUrl(contact1.getInstagramUrl());
     contact.setWhatsAppNumber(contact1.getWhatsAppNumber());

     contactRepository.save(contact);
 }
  @Transactional
 public Contact getById(Long id){
    return contactRepository.findById(id).get();
 }

 @Transactional
 public void deleteById(Long id){
    contactRepository.deleteById(id);
 }
}
