/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fox.kassos.repository;
import com.fox.kassos.entites.Article;
import com.fox.kassos.entites.Commentaire;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author FOX
 */
@Repository
public interface CommentaireRepository  extends  JpaRepository<Commentaire, Integer>{
    public List<Commentaire> findByArticle(Article article);
}
