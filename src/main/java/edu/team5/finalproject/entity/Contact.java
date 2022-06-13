package edu.team5.finalproject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="contacts", indexes = {@Index(name = "idx_whatsAppNumber", columnList = "contact_whatsAppNumber")})
public class Contact {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="contact_id")
    private Long id;

    @Column(name="contact_facebookUrl")
    private String facebookUrl;

    @Column(name="contact_instagramUrl")
    private String instagramUrl;

    @Column(name="contact_whatsAppNumber", nullable = false)
    private Long whatsAppNumber;

    @Column(name="contact_deleted")
    private Boolean deleted;



}