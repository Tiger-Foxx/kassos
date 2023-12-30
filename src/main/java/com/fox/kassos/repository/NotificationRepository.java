/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fox.kassos.repository;

import com.fox.kassos.entites.Commentaire;
import com.fox.kassos.entites.Notification;
import com.fox.kassos.entites.Utilisateur;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FOX
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    public List<Notification> findByUtilisateur(Utilisateur utilisateur);

// Ajouter l'annotation @Transactional 
    //notons que a partir du moment ou quelquun a marque une notif comme lu , alors elle fera au plus 24h en bd
    @Transactional
    @Modifying
    @Query("UPDATE Notification n SET n.lu = TRUE, n.date_lec = CURRENT_TIMESTAMP WHERE n.utilisateur.id = :id")
    public void MakeLu(@Param("id") int id);

// Créer la fonction findTop5ByUtilisateurOrderByDateLecDesc
    public List<Notification> findTop5ByUtilisateurOrderByDateDesc(Utilisateur utilisateur);

}
