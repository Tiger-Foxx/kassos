/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.controleur;

import com.fox.kassos.dto.AuthentificationDTO;
import com.fox.kassos.entites.Article;
import com.fox.kassos.entites.Theme;
import com.fox.kassos.entites.Utilisateur;
import com.fox.kassos.service.ArticleService;
import com.fox.kassos.service.NotificationService;
import com.fox.kassos.service.StoryService;
import com.fox.kassos.service.ThemeService;
import com.fox.kassos.service.UtilisateurService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author FOX
 */
@RestController
public class IndexController {

    @Autowired
    private ThemeService themeService;

    public IndexController(ThemeService themeService, StoryService storyService, NotificationService notificationService, UtilisateurService utilisateurService, ArticleControler articleControler, ArticleService articleService) {
        this.themeService = themeService;
        this.storyService = storyService;
        this.notificationService = notificationService;
        this.utilisateurService = utilisateurService;
        this.articleControler = articleControler;
        this.articleService = articleService;
    }

    @Autowired
    private StoryService storyService;

    public IndexController(StoryService storyService, NotificationService notificationService, UtilisateurService utilisateurService, ArticleControler articleControler, ArticleService articleService) {
        this.storyService = storyService;
        this.notificationService = notificationService;
        this.utilisateurService = utilisateurService;
        this.articleControler = articleControler;
        this.articleService = articleService;
    }

    @Autowired
    private NotificationService notificationService;

    public IndexController(NotificationService notificationService, UtilisateurService utilisateurService, ArticleControler articleControler, ArticleService articleService) {
        this.notificationService = notificationService;
        this.utilisateurService = utilisateurService;
        this.articleControler = articleControler;
        this.articleService = articleService;
    }

    @Autowired
    private UtilisateurService utilisateurService;

    public IndexController(UtilisateurService utilisateurService, ArticleControler articleControler, ArticleService articleService) {
        this.utilisateurService = utilisateurService;
        this.articleControler = articleControler;
        this.articleService = articleService;
    }
    @Autowired
    private ArticleControler articleControler;

    public IndexController(ArticleControler articleControler) {
        this.articleControler = articleControler;
    }

    @Autowired
    private ArticleService articleService;

    public IndexController(ArticleControler articleControler, ArticleService articleService) {
        this.articleControler = articleControler;
        this.articleService = articleService;
    }

    public ArticleControler getArticleControler() {
        return articleControler;
    }

    public IndexController() {
    }

    public void setArticleControler(ArticleControler articleControler) {
        this.articleControler = articleControler;
    }

    @ModelAttribute("article")
    public Article Article() {
        return new Article();
    }

    @GetMapping(path = "/")
    public Object getIndexPage() {
        if (UtilisateurController.is_con) {
            System.out.println("on est dans le controlleur de index !");
            ModelAndView modelAndView = new ModelAndView("Index");
            modelAndView.setViewName("Index");

            //RestTemplate restTemplate = new RestTemplate();
            //recuperer les Articles Retournees par cette Url
            System.out.println("OH MERDE ON ENTRE ");
            Utilisateur OnlineUtilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            OnlineUtilisateur.describe();
            modelAndView.addObject("CurrentUser", OnlineUtilisateur);

            List<Article> Tous_Articles = articleControler.TousArticle();
            Article article1 = Tous_Articles.get(0);
            Article article2 = Tous_Articles.get(1);
            int nb = Tous_Articles.size() / 2 > 10 ? 7 : Tous_Articles.size() / 2;
            List<Theme> themes = this.themeService.getAllThemes();
            modelAndView.addObject("themes", themes);
            modelAndView.addObject("articles_propose", articleService.getRandomArticles(nb));
            modelAndView.addObject("utilisateurs_propose", utilisateurService.getRandomUtilisateurs(7));
            modelAndView.addObject("article1", article1);
            modelAndView.addObject("article2", article2);
            modelAndView.addObject("Tous_Les_Articles", Tous_Articles);
            modelAndView.addObject("TopNotifs", notificationService.TopNotifUtilisateur(OnlineUtilisateur));
            modelAndView.addObject("UtilisateursStory", utilisateurService.AllUtilisateursStory());
            modelAndView.addObject("stories", storyService.getStories());
            modelAndView.addObject("articleTop", articleService.getFirst());
            modelAndView.addObject("taille", Tous_Articles.size()-2 );
            //JE GARDE CE CODE POUR QUAND JE VAIS AJOUTER DES EVENTS
            //        List<Article> EventslList=this.articleService.getEvents();
            //        modelAndView.addObject("event1", EventslList.get(0));
            //        modelAndView.addObject("event2", EventslList.get(1));
            //        modelAndView.addObject("event3", EventslList.get(2));
            return modelAndView;
        } else {
            return new RedirectView("/Connection");
        }

    }

    @GetMapping("/loadMore")
    public Object loadMore(@RequestParam("page") int page) throws InterruptedException {
        Thread.sleep(1500);
        System.out.println("on est dans le controlleur de loadMore !");
        ModelAndView modelAndView = new ModelAndView("index");
        
        List<Article> Tous_Articles = articleControler.TousArticle();
        var articlesrestants=Tous_Articles.subList(2, Tous_Articles.size()); // déclarer la variable articleReste
        List articleReste ;
        int taille = Math.min(1, articlesrestants.size()); // renvoie le plus petit entre 1 et le nombre d'articles restants
        try {
            articleReste = articlesrestants.subList(0 + page * taille, 0 + page * taille + taille); // utilise la taille comme paramètre
            modelAndView.addObject("articles_restants", articleReste);
            modelAndView.setViewName("index :: articleList");
        } catch (Exception e) {
            if (page * taille + taille > articlesrestants.size()) {
                System.out.println("c'est fini !!!!");
                modelAndView.addObject("articles_restants", null);
                return null;
            } else {

                modelAndView.addObject("articles_restants", null);
                return null;

            }

        }

        return modelAndView;
    }

    @RequestMapping(path = "parametres")
    @GetMapping(path = "/")
    public ModelAndView getParametersPage() {

        ModelAndView modelAndView = new ModelAndView("parametres");
        modelAndView.setViewName("parametres");
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("TopNotifs", this.notificationService.TopNotifUtilisateur(utilisateur));
        modelAndView.addObject("CurrentUser", utilisateur);
        return modelAndView;
    }

    @ModelAttribute("CurrentUser")
    public Utilisateur Utilisateur() {
        return new Utilisateur();
    }

    @GetMapping(path = "/Informations")
    public ModelAndView getInfoPage() {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView("account-information");
        modelAndView.setViewName("account-information");
        modelAndView.addObject("TopNotifs", this.notificationService.TopNotifUtilisateur(utilisateur));

        modelAndView.addObject("CurrentUser", utilisateur);
        return modelAndView;
    }

    @ModelAttribute("Utilisateur")
    public Utilisateur utilisateur() {
        return new Utilisateur();
    }

    @GetMapping(path = "/register")
    public ModelAndView getRegisterPage() {

        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @ModelAttribute("authentificationDTO")
    public AuthentificationDTO authentificationDTO() {
        return new AuthentificationDTO();
    }

    @GetMapping(path = "/Connection")
    public ModelAndView getLoginPage() {

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(path = "/Article/Modifier/{id}")
    public ModelAndView getModifPage(@PathVariable("id") int id) {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Article article = this.articleService.detailArticle(id);
        System.err.println("VOILA ARTICLE " + article.toString());
        ModelAndView modelAndView = new ModelAndView();
        List<Theme> themes = this.themeService.getAllThemes();
        modelAndView.addObject("themes", themes);
        modelAndView.setViewName("Modifier-Article");
        modelAndView.addObject("article", article);
        modelAndView.addObject("TopNotifs", this.notificationService.TopNotifUtilisateur(utilisateur));

        modelAndView.addObject("CurrentUser", utilisateur);

        return modelAndView;
    }

    @GetMapping(path = "/testPass")
    public String GetTest() {
        return "ok lka page n'est pas blanche ca renvoie bien vers ce controlleur";
    }

}
