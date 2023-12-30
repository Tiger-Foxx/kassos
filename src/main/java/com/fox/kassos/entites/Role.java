/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.entites;

import com.fox.kassos.Tools.TypeDeRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author FOX
 */
@Entity
@Table(name = "role")
public class Role {
    @Id // je spécifie la clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // je dis comment les clés primaires sont générées
   public int id;

    public Role(TypeDeRole libelle) {
        this.libelle = libelle;
    }

    public Role() {
    }

    public Role(int id, TypeDeRole libelle) {
        this.id = id;
        this.libelle = libelle;
    }
   public TypeDeRole libelle=TypeDeRole.UTILISATEUR;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeDeRole getLibelle() {
        return libelle;
    }

    public void setLibelle(TypeDeRole libelle) {
        this.libelle = libelle;
    }
}
