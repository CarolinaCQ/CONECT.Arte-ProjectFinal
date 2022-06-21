
package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.User;
import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("UPDATE User u SET u.deleted = false WHERE u.id = ?1")
    void enableById(Long id);
    
    @Query("SELECT u FROM User u WHERE u.role='ADMIN' AND u.deleted=:boolean")
    List<User> findAllAdmin(@Param("boolean") Boolean delete);

    Optional<User> findByEmail(String email);

    boolean existsByEmail (String email);
  
}
