/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.controleur;

import com.fox.kassos.Tools.ErrorController;
import com.fox.kassos.Tools.KassosMessage;
import com.fox.kassos.Tools.TypeDeRole;
import com.fox.kassos.entites.Article;
import com.fox.kassos.entites.Commentaire;
import com.fox.kassos.entites.*;
import com.fox.kassos.service.ArticleService;
import com.fox.kassos.service.CommentaireService;
import com.fox.kassos.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author FOX
 */

@RestController
public class CommentController {
    @Autowired
    private NotificationService notificationService;

    public CommentController(NotificationService notificationService, ArticleService articleService, CommentaireService commentaireService) {
        this.notificationService = notificationService;
        this.articleService = articleService;
        this.commentaireService = commentaireService;
    }
    
    @Autowired
    private ArticleService articleService ;

    public CommentController(ArticleService articleService) {
        this.articleService = articleService;
    }
    @Autowired
    private CommentaireService commentaireService ;

    public CommentController() {
    }

    public CommentController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    public CommentController(ArticleService articleService, CommentaireService commentaireService) {
        this.articleService = articleService;
        this.commentaireService = commentaireService;
    }
    
    
    
    
    @PostMapping(path="/Commenter")
    public RedirectView Commenter(@RequestParam("contenu") String contenu , @RequestParam("articleId") int ArticleId){
        System.out.println("LE CONTENU : "+contenu);
        System.out.println("L'ID DE L'ARTICLE : "+ArticleId);
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        Article article = this.articleService.detailArticle(ArticleId);
        
        Commentaire commentaire = new Commentaire(contenu, utilisateur, article);
        this.commentaireService.Commenter(commentaire);
        this.articleService.IncrementScore(ArticleId);
        RedirectView redirectView=new RedirectView("/Article/detail/{id}");
        redirectView.addStaticAttribute("id", article.getId());
        String message=" A COMMENTE VOTRE PUBLICATION ";
        Notification notification= new Notification(article.utilisateur, article, message, utilisateur);
        this.notificationService.Notifier(notification);
        return redirectView;
    }
    
    @GetMapping(path = "/Comment/Delete/{id}")
    public Object SupprimerCommentaire(@PathVariable("id") int id){
        Commentaire commentaire = this.commentaireService.getOneCommentaire(id);
        Utilisateur utilisateur= (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (commentaire.utilisateur.getId() != utilisateur.getId() && utilisateur.role.libelle!=TypeDeRole.ADMINISTRATEUR) {
            KassosMessage message=new KassosMessage("ACTION NON PERMISE", "ACTION NON PERMISE", "CE COMMENTAIRE N'EST PAS A VOUS ");
            return new ErrorController().GetErrView(message);
        } else {
             this.commentaireService.supprimer(id);
             RedirectView redirectView=new RedirectView("/Article/detail/{id}");
                redirectView.addStaticAttribute("id", commentaire.article.getId());
                return redirectView;
        }
    }
}
