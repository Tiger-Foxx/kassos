/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.Tools;

import com.fox.kassos.entites.Article;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author FOX
 */


//Dans la classe ArticleSpecification
public class ArticleSpecification implements Specification<Article> {

  private String sujet;

  public ArticleSpecification(String sujet) {
    this.sujet = sujet;
  }

  @Override
  public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    //On crée un prédicat qui vérifie si le titre contient le sujet
    Predicate titrePredicate = criteriaBuilder.like(root.get("titre"), "%" + sujet + "%");
    Predicate themePredicate = criteriaBuilder.like(root.get("theme"), "%" + sujet + "%");
    //On crée un prédicat qui vérifie si le nom de l'utilisateur contient le sujet
    Predicate utilisateurPredicate = criteriaBuilder.like(root.get("utilisateur").get("nom"), "%" + sujet + "%");
    
    //On combine les deux prédicats avec un opérateur OR
    return criteriaBuilder.or(titrePredicate, utilisateurPredicate,themePredicate);
  }
}