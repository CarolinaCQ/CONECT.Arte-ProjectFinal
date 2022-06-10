package edu.team5.finalproject.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.SQLDelete;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@SQLDelete(sql = "UPDATE clients SET client_deleted = true WHERE id = ?")
@Getter
@Setter
@NoArgsConstructor
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_id")
    private Long id;

    @Column(name="client_nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name="client_profile_image")
    private String profileImage;

    @Column(name="client_deleted")
    private Boolean deleted;

    @JoinColumn(name="client_user", referencedColumnName = "user_id")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;


}