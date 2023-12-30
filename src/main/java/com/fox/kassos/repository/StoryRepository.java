/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fox.kassos.repository;

import com.fox.kassos.entites.Story;
import com.fox.kassos.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FOX
 */

@Repository
public interface StoryRepository extends  JpaRepository<Story, Integer> {
    
}
