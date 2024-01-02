/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.controleur;

import com.fox.kassos.Tools.ErrorController;
import com.fox.kassos.Tools.KassosMessage;
import com.fox.kassos.Tools.TypeDeRole;
import com.fox.kassos.dto.AuthentificationDTO;
import com.fox.kassos.entites.Article;
import com.fox.kassos.entites.Utilisateur;
import com.fox.kassos.securite.JwtService;
import com.fox.kassos.service.ArticleService;
import com.fox.kassos.service.NotificationService;
import com.fox.kassos.service.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author FOX
 */
@RestController
public class UtilisateurController {

    @Autowired
    private NotificationService notificationService;

    public UtilisateurController(NotificationService notificationService, ArticleService articleService, JwtService jwtService, AuthenticationManager authenticationManager, UtilisateurService utilisateurService) {
        this.notificationService = notificationService;
        this.articleService = articleService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.utilisateurService = utilisateurService;
    }

    public static String jeton;
    public static boolean is_con = false;
    @Autowired
    private ArticleService articleService;

    public UtilisateurController(ArticleService articleService, JwtService jwtService, AuthenticationManager authenticationManager, UtilisateurService utilisateurService) {
        this.articleService = articleService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.utilisateurService = utilisateurService;
    }
    @Autowired
    private JwtService jwtService;

    public UtilisateurController(JwtService jwtService, AuthenticationManager authenticationManager, UtilisateurService utilisateurService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.utilisateurService = utilisateurService;
    }

    public JwtService getJwtService() {
        return jwtService;
    }

    public void setJwtService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public UtilisateurController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public UtilisateurService getUtilisateurService() {
        return utilisateurService;
    }

    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // le dossier où les images seront stockées
    private static final String UPLOAD_DIR = "C:\\Users\\SALAKA\\Desktop\\Kasos\\src\\main\\resources\\images_User\\";
    @Autowired
    private AuthenticationManager authenticationManager;
    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public Utilisateur UnUtilisateur(int id) {
        return utilisateurService.UnUtilisateur(id);
    }

    @PostMapping(path = "/register")
    public Object inscription(@Valid @ModelAttribute Utilisateur utilisateur, @RequestParam("photoUser") MultipartFile file) {
        // vérifier si le fichier est vide
        KassosMessage message;
        if (file.isEmpty()) {
            message = new KassosMessage("FORMULAIRE INCOMPLET", "Il semblerait que tu n'aies pas bien rempli le formulaire", "il n'y a pas d'image");
            return new ErrorController().GetErrView(message);
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
        utilisateur.setPhoto(fileName);

        // je recupere l'exeption due a la contrainte d'integrite
        try {
            this.utilisateurService.inscription(utilisateur);
        } catch (SQLIntegrityConstraintViolationException ex) {

            System.out.println("JE SUIS LAAAAAAAA L'EXCEPTION DE TA MEEEEEEEEEERE");
            message = new KassosMessage("UNE ERREUR FATALE", "Il semblerait que tu aies Essaye le Compte de quelqu'un", "Petit coquin, cet email existe deja !");
            ex.printStackTrace();
            return new ErrorController().GetErrView(message);
        }
        // Définir le nom de la vue à afficher
        RedirectView redirectView = new RedirectView("/Connection");
        return redirectView;
    }

    //methode qui va permettre de recuperer le chemin de l'image avec th:src="@{/image/{fileName}(fileName=${article.image})}"
    @GetMapping("/image_user/{fileName}")
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

    @PostMapping(path = "/Connection")

    public Object Connection(@ModelAttribute AuthentificationDTO authentificationDTO) {
        System.out.println("je suis ici dans en haut connexion! !");
        try {
            final Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authentificationDTO.getUsername(), authentificationDTO.getPassword())
            );
            
            if (authenticate.isAuthenticated()) {
                jeton = this.jwtService.generate(authentificationDTO.getUsername()).get("bearer");
            }
            is_con = authenticate.isAuthenticated();
            System.out.println("je suis ici dans connexion! !");
            
            System.out.println("Resultat : " + authenticate.isAuthenticated());
            return new RedirectView("/");
        } catch (AuthenticationException authenticationException) {
            return new ModelAndView("BadPassword");
        }

    }

    public UtilisateurController(AuthenticationManager authenticationManager, UtilisateurService utilisateurService) {
        this.authenticationManager = authenticationManager;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/deconnection")
    public RedirectView deconnection(HttpServletRequest request, HttpServletResponse response) {
        // invalider la session de l'utilisateur
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        // supprimer le jeton JWT de l'en-tête d'autorisation
        response.setHeader("Authorization", null);
        // mettre la variable statique is_con à false
        is_con = false;
        // rediriger vers la vue /Connection
        return new RedirectView("/Connection");
    }

    @GetMapping(path = "/Membre/Delete/{id}")
    public Object SupprimerUtilisateur(@PathVariable("id") int id) {
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // on va verifier si c'est un Admin
        if (utilisateur.role.libelle == TypeDeRole.ADMINISTRATEUR) {
            this.utilisateurService.supprimer(id);
            return new RedirectView("/Membres");
        } else {
            KassosMessage message = new KassosMessage("ACTION NON PERMISE", "ACTION NON PERMISE", "Vous n'etes pas admin ");
            return new ErrorController().GetErrView(message);
        }
    }

    @PostMapping("/Utilisateur/Modifier")
    public Object ModifierArticle(@Valid @ModelAttribute Utilisateur utilisateur, @RequestParam("photoUser") MultipartFile file) {
        // vérifier si le fichier est vide
        Utilisateur utilisateurCourant = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        KassosMessage message;
        if (file.isEmpty()) {
            utilisateur.setPhoto(utilisateurCourant.getPhoto());
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

            utilisateur.setPhoto(fileName);

        }
        // ici c'est plus securise car maintenant au lieu de prendre utilisateur i dans le formulaire je vais directement prendre celui qui est Connecte comme ca quelqu'un ne pourra pas poster pour un autre
        utilisateur.setId(utilisateurCourant.getId());
        utilisateur.setPassword(utilisateurCourant.getPassword());
        this.utilisateurService.modifier(utilisateur);

        RedirectView redirectView = new RedirectView("/Utilisateur/detail/{id}");
        redirectView.addStaticAttribute("id", utilisateur.getId());
        return redirectView;
    }

    @GetMapping(path = "/Utilisateur/detail/{id}")
    public ModelAndView detail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-page");
        Utilisateur utilisateur = this.utilisateurService.UnUtilisateur(id);
        ArrayList<Article> articles = (ArrayList<Article>) this.articleService.ArticleUtilisateur(utilisateur);
        Utilisateur utilisateurCourant = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("utilisateur", utilisateur);
        modelAndView.addObject("articles", articles);
        modelAndView.addObject("CurrentUser", utilisateurCourant);
        modelAndView.addObject("TopNotifs", this.notificationService.TopNotifUtilisateur(utilisateurCourant));

        modelAndView.addObject("compte", 3);
        KassosMessage message = new KassosMessage("PAS DE CORRESPONDANCE", "OUPS IL N'Y A RIEN ICI ", "IL SEMBLERAIT QUE CE QUE TU CHERCHES N'EXISTE PAS ");
        if (utilisateur.equals(null)) {
            return new ErrorController().GetErrView(message);
        } else {
            return modelAndView;
        }

    }

    @GetMapping(path = "/Me")
    public RedirectView MView() {
        Utilisateur utilisateurCourant = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = utilisateurCourant.getId();
        return new RedirectView("/Utilisateur/detail/{" + id + "}");
    }

    @GetMapping(path = "/Membres")
    public ModelAndView getMembersPage() {

        ModelAndView modelAndView = new ModelAndView("Membres");
        modelAndView.setViewName("Membres");
        modelAndView.addObject("utilisateurs", this.utilisateurService.AllUtilisateurs());
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("CurrentUser", utilisateur);

        return modelAndView;
    }

}
