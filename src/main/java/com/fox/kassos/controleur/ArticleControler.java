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
import com.fox.kassos.entites.Notification;
import com.fox.kassos.entites.Utilisateur;
import com.fox.kassos.repository.UtilisateurRepository;
import com.fox.kassos.service.ArticleService;
import com.fox.kassos.service.CommentaireService;
import com.fox.kassos.service.NotificationService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.aspectj.bridge.ISourceLocation;
import org.aspectj.bridge.Message;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author FOX
 */
@RestController

public class ArticleControler {

    @Autowired
    private NotificationService notificationService;

    public ArticleControler(NotificationService notificationService, CommentaireService commentaireService, ArticleService articleService, UtilisateurController utilisateurController) {
        this.notificationService = notificationService;
        this.commentaireService = commentaireService;
        this.articleService = articleService;
        this.utilisateurController = utilisateurController;
    }

    @Autowired
    private CommentaireService commentaireService;

    public ArticleControler(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    public ArticleControler() {
    }
    @Autowired
    private ArticleService articleService;

    public ArticleControler(CommentaireService commentaireService, ArticleService articleService, UtilisateurController utilisateurController) {
        this.commentaireService = commentaireService;
        this.articleService = articleService;
        this.utilisateurController = utilisateurController;
    }

    // le dossier où les images seront stockées
    private static final String UPLOAD_DIR = "C:\\Users\\SALAKA\\Desktop\\Kasos\\src\\main\\resources\\images\\";

    @Autowired
    private UtilisateurController utilisateurController;

    public ArticleControler(ArticleService articleService) {
        this.articleService = articleService;
    }

    // Créer un modèle ModelAndView
    ModelAndView modelAndView = new ModelAndView();

    @PostMapping(path = "/Article/poster") // pour que cette methode se declanche l'orsque la requete a le verbe POST
    public Object poster(@Valid @ModelAttribute Article article, @RequestParam("file") MultipartFile file, @RequestParam("utilisateurId") int id) {
        // vérifier si le fichier est vide
        KassosMessage message;
        if (file.isEmpty()) {
            Utilisateur OnlineUtilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            OnlineUtilisateur.describe();

            article.setUtilisateur(OnlineUtilisateur);

            this.articleService.poster(article);

            // Définir le nom de la vue à afficher
            modelAndView.setViewName("index");
            RedirectView redirectView = new RedirectView("/");
            return redirectView;
        }
        // générer un nom unique pour le fichier
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        // créer le chemin du fichier
        Path path = Paths.get(UPLOAD_DIR + fileName);
        // copier le fichier dans le dossier
        try {
            file.transferTo(path);
        } catch (IOException e) {
            e.printStackTrace();
            message = new KassosMessage("UNE ERREUR FATALE", "Il semblerait que tu aies Enclanche une erreur Fatale", "Verifie que tu as correctement rempli le formulaire");
            return new ErrorController().GetErrView(message);

        }
        // définir le chemin de l'image dans l'article
        article.setImage(fileName);
        //article.setUtilisateur(this.utilisateurController.UnUtilisateur(id)); // recherche celui qui a l'id correspondant
        // icic c'est plus securise car maintenant au lieu de prendre utilisateur i dans le formulaire je vais directement prendre celui qui est Connecte comme ca quelqu'un ne pourra pas poster pour un autre
        Utilisateur OnlineUtilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        OnlineUtilisateur.describe();

        article.setUtilisateur(OnlineUtilisateur);

        this.articleService.poster(article);

        // Définir le nom de la vue à afficher
        modelAndView.setViewName("index");
        RedirectView redirectView = new RedirectView("/");
        return redirectView;
    }

    //methode qui va permettre de recuperer le chemin de l'image avec th:src="@{/image/{fileName}(fileName=${article.image})}"
    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> showImage(@PathVariable String fileName) {
        // créer le chemin du fichier
        Path path = Paths.get(UPLOAD_DIR + fileName);
        // créer la ressource à partir du chemin
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // retourner la ressource avec le type MIME approprié

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    //methode qui va permettre de recuperer touts les Articles du site 
    @GetMapping(path = "/Article/All", produces = APPLICATION_JSON_VALUE)
    public List<Article> AllArticle() {
        return this.articleService.AllArticles();
    }

    public List<Article> TousArticle() {
        return this.articleService.AllArticles();
    }

    @PostMapping(path = "/Articles/Chercher")
    public ModelAndView RechercherArticle(@RequestParam("sujet") String sujet) {
        this.modelAndView.addObject("sujet", sujet);
        List<Article> Resultats = this.articleService.rechercheArticles(sujet);
        for (Article Resultat : Resultats) {
            System.out.println(Resultat.titre + " , score :  " + Resultat.score + " , contenu :  " + Resultat.texte);
        }

        this.modelAndView.addObject("articles_trouves", Resultats);
        this.modelAndView.setViewName("Result-search");

        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("TopNotifs", this.notificationService.TopNotifUtilisateur(utilisateur));

        this.modelAndView.addObject("CurrentUser", utilisateur);
        return this.modelAndView;
    }

    @GetMapping(path = "/Article/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id) {
        this.modelAndView.setViewName("detail-POST");
        Article article = this.articleService.detailArticle(id);
        this.modelAndView.addObject("article", article);
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("TopNotifs", this.notificationService.TopNotifUtilisateur(utilisateur));
        this.articleService.IncrementScore(id);
        List<Commentaire> Commentaires = this.commentaireService.getCommentaires(article);
        this.modelAndView.addObject("CurrentUser", utilisateur);
        this.modelAndView.addObject("commentaires", Commentaires);
        KassosMessage message = new KassosMessage("PAS DE CORRESPONDANCE", "OUPS IL N'Y A RIEN ICI ", "IL SEMBLERAIT QUE CE QUE TU CHERCHES N'EXISTE PAS ");
        if (article.equals(null)) {
            return new ErrorController().GetErrView(message);
        } else {
            return this.modelAndView;
        }

    }

    @GetMapping(path = "/Article/Delete/{id}")
    public Object SupprimerArticle(@PathVariable("id") int id) {
        Article article = this.articleService.detailArticle(id);
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (article.utilisateur.getId() != utilisateur.getId() && utilisateur.role.libelle != TypeDeRole.ADMINISTRATEUR) {
            KassosMessage message = new KassosMessage("ACTION NON PERMISE", "ACTION NON PERMISE", "CET ARTICLE N'EST PAS A VOUS ");
            return new ErrorController().GetErrView(message);
        } else {
            String message = " A SUPPRIME VOTRE PUBLICATION ";
            Notification notification = new Notification(article.utilisateur, article, message, utilisateur);
            this.notificationService.Notifier(notification);
            this.articleService.supprimer(id);
            return new RedirectView("/");
        }
    }

    @PostMapping("/Article/Modifier")
    public Object ModifierArticle(@Valid @ModelAttribute Article article, @RequestParam("file") MultipartFile file, @RequestParam("utilisateurId") int id) {
        // vérifier si le fichier est vide
        KassosMessage message;
        if (file.isEmpty()) {
            article.setImage(this.articleService.detailArticle(article.getId()).getImage());
        } else {
            // générer un nom unique pour le fichier
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            // créer le chemin du fichier
            Path path = Paths.get(UPLOAD_DIR + fileName);
            // copier le fichier dans le dossier
            try {
                file.transferTo(path);
            } catch (IOException e) {
                e.printStackTrace();
                message = new KassosMessage("UNE ERREUR FATALE", "Il semblerait que tu aies Enclanche une erreur Fatale", "Verifie que tu as correctement rempli le formulaire");
                return new ErrorController().GetErrView(message);
            }
            // définir le chemin de l'image dans l'article
            article.setImage(fileName);
        }
        // ici c'est plus securise car maintenant au lieu de prendre utilisateur i dans le formulaire je vais directement prendre celui qui est Connecte comme ca quelqu'un ne pourra pas poster pour un autre
        Utilisateur OnlineUtilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        OnlineUtilisateur.describe();

        article.setUtilisateur(OnlineUtilisateur);

        this.articleService.modifier(article);

        RedirectView redirectView = new RedirectView("/Article/detail/{id}");
        redirectView.addStaticAttribute("id", article.getId());
        return redirectView;

    }

}
