/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.controleur;

import com.fox.kassos.Tools.ErrorController;
import com.fox.kassos.Tools.KassosMessage;
import com.fox.kassos.entites.Article;
import com.fox.kassos.entites.Story;
import com.fox.kassos.entites.Utilisateur;
import com.fox.kassos.service.StoryService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author FOX
 */

@RestController
public class StoryController {
    // le dossier où les images seront stockées
    private static final String UPLOAD_DIR = "C:\\Users\\SALAKA\\Desktop\\Kasos\\src\\main\\resources\\images_stories\\";
    @Autowired
    private StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    
    public StoryController() {
    }
    
    
    @PostMapping("/Ajout/Story")
    
    public Object AjouterStory(  @RequestParam("image_story") MultipartFile file){
        KassosMessage message;
        Story story= new Story();
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
        story.setImage(fileName);
        //article.setUtilisateur(this.utilisateurController.UnUtilisateur(id)); // recherche celui qui a l'id correspondant
        // icic c'est plus securise car maintenant au lieu de prendre utilisateur i dans le formulaire je vais directement prendre celui qui est Connecte comme ca quelqu'un ne pourra pas poster pour un autre
        Utilisateur OnlineUtilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        OnlineUtilisateur.describe();

        story.setUtilisateur(OnlineUtilisateur);

        this.storyService.AjoutStory(story);
        RedirectView redirectView = new RedirectView("/");
        return redirectView;
    }
    
    
    //methode qui va permettre de recuperer le chemin de l'image avec th:src="@{/image/{fileName}(fileName=${article.image})}"
    @GetMapping("/image_story/{fileName}")
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
    
}
