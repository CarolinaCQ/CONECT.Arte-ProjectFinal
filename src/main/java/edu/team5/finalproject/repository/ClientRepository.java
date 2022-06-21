package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Modifying
    @Query("UPDATE Client c SET c.deleted = false WHERE c.id = ?1")
    void enableById(Long id);

    @Query("SELECT c FROM Client c WHERE c.user.id=:id")
    Optional<Client> getByIdUser(@Param("id") Long id);
    
    @Query("SELECT c FROM Client c WHERE c.deleted=:boolean")
    List<Client> getByBoolean(@Param("boolean") Boolean delete);
    
    boolean existsByNickname (String nickname);
}
