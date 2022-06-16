package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Modifying
    @Query("UPDATE Client c SET c.deleted = false WHERE c.id = ?1")
    void enableById(Long id);

    boolean existsByNickname (String nickname);
}
