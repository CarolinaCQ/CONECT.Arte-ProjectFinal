package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.Contact;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByWhatsAppNumber(Long whatsAppNumber);
}
