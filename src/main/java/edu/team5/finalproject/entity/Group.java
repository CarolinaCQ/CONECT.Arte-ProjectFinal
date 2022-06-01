package edu.team5.finalproject.entity;

import java.io.Serializable;
import java.util.List;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.EnumType.STRING;
import javax.persistence.*;
import edu.team5.finalproject.entity.enums.Locale;
import edu.team5.finalproject.entity.enums.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "group_bands")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_name", nullable = false, unique = true)
    private String name;

    @Column(name = "group_profile_image")
    private String profileImage;

    @Column(name = "group_description")
    private String description;

    @Column(name = "group_style", nullable = false)
    private String style;

    @Column(name = "group_mobility", nullable = false)
    private Boolean mobility;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "group_service_list",
            joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "group_service", nullable = false)
    private List<String> service;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "group_image_list",
            joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "group_image")
    private List<String> imageList;

    @Enumerated(STRING)
    @Column(name = "group_type", nullable = false)
    private Type type;

    @Enumerated(STRING)
    @Column(name = "group_locale", nullable = false)
    private Locale locale;

    @JoinColumn(name = "group_contact", referencedColumnName = "contact_id")
    @OneToOne(fetch = EAGER)
    private Contact contact;

    @JoinColumn(name = "group_user", referencedColumnName = "user_id")
    @OneToOne(fetch = EAGER)
    private User user;


}