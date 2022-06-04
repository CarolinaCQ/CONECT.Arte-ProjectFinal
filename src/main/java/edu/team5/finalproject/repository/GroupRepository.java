package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
