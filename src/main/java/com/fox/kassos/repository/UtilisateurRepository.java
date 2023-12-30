/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fox.kassos.repository;

import com.fox.kassos.entites.Article;
import com.fox.kassos.entites.Utilisateur;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author FOX
 */
public interface UtilisateurRepository extends  JpaRepository<Utilisateur, Integer> {

    public Optional<Utilisateur> findByEmail(String username);
    
  @Query(value = "SELECT * FROM utilisateur ORDER BY RAND()", nativeQuery = true)
    List<Utilisateur> findAllRandom();
    
    // Créer la fonction findByStory
    @Query(value = "SELECT DISTINCT u FROM Utilisateur u JOIN Story s ON u.id = s.utilisateur.id")
    public List<Utilisateur> findByStory();
}
