package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // borré el método que estaba creado acá, ese método ya lo estamos heredando de JpaRepository. (findbyid)
}
