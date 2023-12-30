/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author FOX
 */
@Entity
@Table(name = "commentaire")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // je dis comment les clés primaires sont générées
    private int id;
    @CreatedDate
    public Timestamp date =Timestamp.valueOf(LocalDateTime.now()) ;
    public String texte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Commentaire() {
    }
    

    public Commentaire(Timestamp date, String texte, Utilisateur utilisateur, Article article) {
        this.date = date;
        this.texte = texte;
        this.utilisateur = utilisateur;
        this.article = article;
    }

    public Commentaire(String texte, Utilisateur utilisateur, Article article) {
        this.texte = texte;
        this.utilisateur = utilisateur;
        this.article = article;
    }

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    public Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "id_article")
    public Article article;

}
