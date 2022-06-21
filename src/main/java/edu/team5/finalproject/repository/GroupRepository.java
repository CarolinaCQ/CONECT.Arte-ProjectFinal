package edu.team5.finalproject.repository;

import edu.team5.finalproject.entity.Group;
import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Style;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("SELECT g FROM Group g WHERE g.deleted = FALSE")
    List<Group> getGroupDeletedFalse();

    @Query("SELECT g FROM Group g WHERE g.locale LIKE '?1'")
    List<Group> getByLocale(Locale locale);

    @Query("SELECT g FROM Group g WHERE g.style=:style AND g.locale=:locale AND g.type='DANCE'")
    List<Group> getByStyleAndLocaleDance(@Param("style") Style style,@Param("locale") Locale locale);
    
    @Query("SELECT g FROM Group g WHERE g.style=:style AND g.locale=:locale AND g.type='MUSIC'")
    List<Group> getByStyleAndLocaleMusic(@Param("style") Style style,@Param("locale") Locale locale);

    @Query("SELECT g FROM Group g WHERE g.type='DANCE'")
    List<Group> getByTypeDance();

    @Query("SELECT g FROM Group g WHERE g.type='MUSIC'")
    List<Group> getByTypeMusic();
    
    @Query("SELECT g FROM Group g WHERE g.user.id=:id")
    Optional<Group> getByIdUser(@Param("id") Long id);
    
    @Query("SELECT g FROM Group g WHERE g.deleted=:boolean")
    List<Group> getByBoolean(@Param("boolean") Boolean delete);

    @Modifying
    @Query("UPDATE Group g SET g.deleted = false WHERE g.id = ?1")
    void enableById(Long id);

    boolean existsByName (String name);

}
