/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.entites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author FOX
 */
@Entity // ceci est la pour dire que le User est une entité qui est dans la bd
@Table(name = "Utilisateur")
public class Utilisateur implements UserDetails{

    @Id // je spécifie la clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // je dis comment les clés primaires sont générées
   public int id;
   public String email;  
   public String nom;
   public String pseudo;
   public boolean Actif=true;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    @Enumerated(EnumType.ORDINAL)
    public  Role role=new Role();

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActif() {
        return Actif;
    }

    public void setActif(boolean Actif) {
        this.Actif = Actif;
    }
    public Utilisateur(String email, String nom, String password, String photo) {
        this.email = email;
        this.nom = nom;
        this.password = password;
        this.photo = photo;
    }
   public String password;

    public Utilisateur(int id, String email, String nom, String password, String photo) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.password = password;
        this.photo = photo;
    }

    public Utilisateur(int id, String email, String nom, String pseudo, String password, String photo, String description, String telephone, String ville) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.pseudo = pseudo;
        this.password = password;
        this.photo = photo;
        this.description = description;
        this.telephone = telephone;
        this.ville = ville;
    }

     @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
   @CreatedDate
   public Date date=Date.valueOf(LocalDate.now());

    public Utilisateur(String email, String nom, String pseudo, Date date_inscription, String photo, String description, String telephone, String ville) {
        this.email = email;
        this.nom = nom;
        this.pseudo = pseudo;
        this.date = date_inscription;
        this.photo = photo;
        this.description = description;
        this.telephone = telephone;
        this.ville = ville;
    }
    public String getIdString(){
        return String.valueOf(id);
    }

    public Utilisateur() {
    }

    public Utilisateur(String email, String nom) {
        this.email = email;
        this.nom = nom;
    }

    public Utilisateur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Date getDate_inscription() {
        return date;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date = date_inscription;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
   public String photo;
   public String description;
   public String telephone;
   public String ville;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+this.role.getLibelle()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.Actif;
    }

    @Override
    public boolean isAccountNonLocked() {
return this.Actif;    }

    @Override
    public boolean isCredentialsNonExpired() {
return this.Actif;    }

    @Override
    public boolean isEnabled() {
return this.Actif;    }

    public void describe(){
        System.out.println("je suis l'utilisateur : "+this.nom+" mon email est : "+this.getUsername());
    }
    
   @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "utilisateur")
private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    
}
