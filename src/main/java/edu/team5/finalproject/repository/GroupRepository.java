package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Style;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g WHERE g.deleted = FALSE")
    List<Group> getGroupDeletedFalse();

    @Query("SELECT g FROM group g WHERE g.locale LIKE:locale")
    List<Group> searchLocale(Locale locale);

    @Query("SELECT g FROM group g WHERE g.style LIKE:style")
    List<Group> searchStyle(Style style);

    boolean existsByName (String name);

}
