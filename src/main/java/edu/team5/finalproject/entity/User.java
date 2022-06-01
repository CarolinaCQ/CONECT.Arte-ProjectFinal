package edu.team5.finalproject.entity;

import java.io.Serializable;
import javax.persistence.*;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import edu.team5.finalproject.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_email", nullable = false, unique = true)
    private String email;

    @Column(name="user_password", nullable = false, unique = true)
    private String password;

    @Enumerated(STRING)
    @Column(name="roles")
    private Role role;


}
