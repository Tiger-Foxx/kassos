/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.entites;

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
@Table(name = "theme")
public class Theme {

    public Theme(int id) {
        this.id = id;
    }

    public Theme() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // je dis comment les clés primaires sont générées
    private int id;
    
    private String libelle="Aucun";

    public Theme(String libelle) {
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Theme(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
}
