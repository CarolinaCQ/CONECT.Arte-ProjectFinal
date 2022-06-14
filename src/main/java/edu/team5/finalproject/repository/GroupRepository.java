package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Role;
import edu.team5.finalproject.entity.enums.Style;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g WHERE g.deleted = FALSE")
    List<Group> getGroupDeletedFalse();

    @Query("SELECT g FROM Group g WHERE g.locale LIKE '?1'")
    List<Group> getByLocale(Locale locale);

    @Query("SELECT g FROM Group g WHERE g.style LIKE '?1'")
    List<Group> getByStyle(Style style);

    @Query("SELECT g FROM Group g WHERE g.user.role LIKE '?1'")
    List<Group> getByRole(Role role);

    @Modifying
    @Query("UPDATE Group g SET g.deleted = false WHERE g.id = ?1")
    void enableById(Long id);

    boolean existsByName (String name);

}
