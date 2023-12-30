/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.service;

import com.fox.kassos.entites.Notification;
import com.fox.kassos.entites.Utilisateur;
import com.fox.kassos.repository.NotificationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FOX
 */

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    
    public NotificationService() {
    }

    public List<Notification> NotifUtilisateur(Utilisateur utilisateur){
        return this.notificationRepository.findByUtilisateur(utilisateur) ;
    }
    
    public List<Notification> TopNotifUtilisateur(Utilisateur utilisateur){
        return this.notificationRepository.findTop5ByUtilisateurOrderByDateDesc(utilisateur) ;
    }

    public void MakeLu(int id) {
        this.notificationRepository.MakeLu(id);
    }
    
    public void Notifier(Notification notification){
        this.notificationRepository.save(notification);
    }
    
}
