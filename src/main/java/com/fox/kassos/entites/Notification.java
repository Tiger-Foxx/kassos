/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.entites;

/**
 *
 * @author FOX
 *
 * CREATE EVENT Supprimer_Notifications ON SCHEDULE EVERY 1 HOUR DO -> DELETE
 * FROM notification WHERE lu = TRUE AND date_lecture < NOW() - INTERVAL 24
 * HOUR;
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {

    public Notification(Utilisateur utilisateur, Article article, String message, Utilisateur acteur) {
        this.utilisateur = utilisateur;
        this.article = article;
        this.message = message;
        this.acteur = acteur;
    }

    public Notification() {
    }

    public Notification(int id, Utilisateur utilisateur, Article article, String message, Utilisateur acteur, Timestamp date_lec) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.article = article;
        this.message = message;
        this.acteur = acteur;
        this.date_lec = date_lec;
    }

    public Notification(Utilisateur utilisateur, Article article, String message, Utilisateur acteur, Timestamp date_lec) {
        this.utilisateur = utilisateur;
        this.article = article;
        this.message = message;
        this.acteur = acteur;
        this.date_lec = date_lec;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    public Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "article_id")
    public Article article;

    @Column(name = "message")
    public String message;

    @ManyToOne
    @JoinColumn(name = "acteur_id")
    public Utilisateur acteur;

    @Column(name = "date")
    public Timestamp date = Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "lu")
    public boolean lu = false;

    @Column(name = "date_lec", nullable = true)

    public Timestamp date_lec;

}
