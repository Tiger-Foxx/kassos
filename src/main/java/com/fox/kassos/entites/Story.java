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
import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author FOX
 */

@Entity
@Table(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // je dis comment les clés primaires sont générées
    public int id;

    public Story() {
    }
    
    @CreatedDate
    public Timestamp date =Timestamp.valueOf(LocalDateTime.now()) ;
    
   public String image;
   
   @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    public Utilisateur utilisateur;

    public Story(String image, Utilisateur utilisateur) {
        this.image = image;
        this.utilisateur = utilisateur;
    }

    public Story(int id, String image, Utilisateur utilisateur) {
        this.id = id;
        this.image = image;
        this.utilisateur = utilisateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
   
}
