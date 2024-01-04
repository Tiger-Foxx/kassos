/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.entites;

import jakarta.persistence.CascadeType;
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
@Table(name = "Article")
public class Article {
    
    /*     AJOUTER UN TITRE A L'ARTICLE     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   public int id;
   public String texte;
   public String theme="aucun";
   public String image;
   public String titre;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Article(int id, String texte, String theme, String image, String titre, Utilisateur utilisateur) {
        this.id = id;
        this.texte = texte;
        this.theme = theme;
        this.image = image;
        this.titre = titre;
        this.utilisateur = utilisateur;
    }
   @CreatedDate
   public Timestamp date = Timestamp.valueOf(LocalDateTime.now());
   public int score = 0; //le miveau de popularite de l'article
    // PERSIST signifie sauvegarde le client, recupere sa clé primmaire et injecte la dans la clé etrangere du sentiment avant de le sauvegarder
    // MERGE signifie que si l client existe deja on va juste recuperer sa clé et injecter comme clé etrangere du sentiment
    // @ManyToOne(cascade = {PERSIST , MERGE})
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_utilisateur") // pas necessaire l'orsque tu nomme bien CLIENT_ID
   public Utilisateur utilisateur;

    public Article(String texte, Utilisateur utilisateur) {
        this.texte = texte;
        this.utilisateur = utilisateur;
    }

    public Article(String texte, String theme, Utilisateur utilisateur) {
        this.texte = texte;
        this.theme = theme;
        this.utilisateur = utilisateur;
    }

    public Article(String texte, String theme, String image, Utilisateur utilisateur) {
        this.texte = texte;
        this.theme = theme;
        this.image = image;
        this.utilisateur = utilisateur;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Article(String texte, String theme, String image, Timestamp date, Utilisateur utilisateur) {
        this.texte = texte;
        this.theme = theme;
        this.image = image;
        this.date = date;
        this.utilisateur = utilisateur;
    }
    
    //Dans la classe Article
public String getResume() {
  //On obtient la longueur du texte
  int len = this.texte.length();
  //Si le texte est supérieur à 100 caractères
  if (len > 100) {
    //On renvoie les 100 premiers caractères
    return this.texte.substring(0, 100) + "...(Cliquez sur le titre pour lire)";
  }
  //Sinon
  else {
    //On renvoie le texte entier
    return this.texte;
  }
}

}
