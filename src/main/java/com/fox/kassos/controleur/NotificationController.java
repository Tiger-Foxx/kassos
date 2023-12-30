/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.controleur;

import com.fox.kassos.entites.Notification;
import com.fox.kassos.entites.Utilisateur;
import com.fox.kassos.service.NotificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author FOX
 */

@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public NotificationController() {
    }
    
    @GetMapping(path = "/notifications")
    
    public ModelAndView getNotificationsPage(){
        ModelAndView modelAndView=new ModelAndView("notifications");
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Notification> notifications = this.notificationService.NotifUtilisateur(utilisateur);
        modelAndView.addObject("CurrentUser", utilisateur);
        modelAndView.addObject("notifications", notifications);
        return modelAndView;
    }
    
    
    @GetMapping(path = "/notifications/lu")
    
    public RedirectView MakeLu(){
        RedirectView redirectView=new RedirectView("/notifications");
        Utilisateur utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         this.notificationService.MakeLu(utilisateur.id);
     
        return redirectView;
    }
    
    
}
