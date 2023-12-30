/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fox.kassos.repository;
import com.fox.kassos.entites.Article;
import com.fox.kassos.entites.Utilisateur;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
/**
 *
 * @author FOX
 */
@Repository
public interface ArticleRepository extends  JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article>  {
    public List<Article> findByUtilisateur(Utilisateur utilisateur);
    
    
    @Query(value = "SELECT * FROM article ORDER BY RAND()", nativeQuery = true)
    List<Article> findAllRandom();
    
    @Query(value = "SELECT * FROM article ORDER BY date DESC", nativeQuery = true)
    List<Article> findAllOrder();
    
    @Query(value = "SELECT * FROM article WHERE theme='Evenement' LIMIT=3", nativeQuery = true)
    List<Article> findAllEvent();
    
    // Créer la fonction incrementScore
@Modifying
@Transactional
@Query("UPDATE Article a SET a.score = a.score + 1 WHERE a.id = :id")
public void incrementScore(@Param("id") int id);

// Créer la méthode findFirstByOrderByScoreDesc
public Article findFirstByOrderByScoreDesc();


}



