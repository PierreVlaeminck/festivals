package fr.simplon.festivals.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "festivals")
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String url;
    private String ville;
    private int cp;

    @Column (columnDefinition = "TEXT")
    private String texte;

    private Date dateDebut;
    private Date dateFin;
    private Double latitude;
    private Double longitude;
}
