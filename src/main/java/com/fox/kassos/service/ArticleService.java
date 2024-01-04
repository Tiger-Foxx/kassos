/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.service;

import com.fox.kassos.Tools.ArticleSpecification;
import com.fox.kassos.entites.Article;
import com.fox.kassos.entites.Utilisateur;
import com.fox.kassos.repository.ArticleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author FOX
 */
@Service
public class ArticleService {

    //une facon de faire mais pas la meilleure
    /*@Autowired // je dois relier le repository au service
   ClientRepository clientRepository;*/
    // voila la bonne facon en passant par le constructeur
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void poster(Article article) {
        this.articleRepository.save(article);
    }

    public List<Article> AllArticles() {
        return this.articleRepository.findAllOrder();
    }

    public List<Article> rechercheArticles(String sujet) {
        //On crée une spécification avec le sujet
        ArticleSpecification spec = new ArticleSpecification(sujet);
        //On appelle la méthode findAll() du repository avec la spécification
        return articleRepository.findAll(spec);
    }

    public Article detailArticle(int id) {

        return this.articleRepository.findById(id).orElse(null);
    }

    public void supprimer(int id) {
        this.articleRepository.deleteById(id);

    }

    public void modifier(Article article) {
        articleRepository.save(article); // articleRepository est une interface qui étend CrudRepository<Article, Long>
    }

    public List<Article> ArticleUtilisateur(Utilisateur utilisateur) {

        return this.articleRepository.findByUtilisateur(utilisateur);
    }

    public List<Article> getRandomArticles(int n) {
        List<Article> allArticles = articleRepository.findAllRandom();
        return allArticles.subList(0, n);
    }

    public List<Article> getEvents() {
        List<Article> allArticles = articleRepository.findAllEvent();
        return allArticles;
    }
    public void IncrementScore(int id){
        this.articleRepository.incrementScore(id);
    }
    
    public Article getFirst(){
        return this.articleRepository.findFirstByOrderByScoreDesc();
    }
}
