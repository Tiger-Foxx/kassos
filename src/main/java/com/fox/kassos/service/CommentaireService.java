/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.service;

import com.fox.kassos.entites.Article;
import com.fox.kassos.entites.Commentaire;
import com.fox.kassos.repository.CommentaireRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FOX
 */

@Service
public class CommentaireService {
    @Autowired
    private CommentaireRepository commentaireRepository;
     public CommentaireService(CommentaireRepository commentaireRepository) {
        this.commentaireRepository = commentaireRepository;
    }
    public CommentaireService() {
    }

    public void Commenter(Commentaire commentaire){
        this.commentaireRepository.save(commentaire);
    }
   
    public List<Commentaire> getCommentaires(Article article){
        return this.commentaireRepository.findByArticle(article);
    }
    
    public Commentaire getOneCommentaire(int id){
        return this.commentaireRepository.findById(id).orElse(null);
    }
    
    public void supprimer(int id){
        this.commentaireRepository.deleteById(id);
    }
    
    
}
