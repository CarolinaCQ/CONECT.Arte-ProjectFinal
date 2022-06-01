
package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    
}
