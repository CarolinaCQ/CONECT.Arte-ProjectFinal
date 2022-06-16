package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.Contact;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Modifying
    @Query("UPDATE Contact c SET c.deleted = false WHERE c.id = ?1")
    void enableById(Long id);

    Optional<Contact> findByWhatsAppNumber(Long whatsAppNumber);

    boolean existsByWhatsAppNumber (Long whatsAppNumber);
}
