/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.Tools;

import com.fox.kassos.controleur.UtilisateurController;
import com.fox.kassos.entites.Utilisateur;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author FOX
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // récupérer l'utilisateur connecté
        Utilisateur utilisateur = null;
        try {
            utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return false;
        }
        // si l'utilisateur est authentifié
        if (utilisateur != null) {
           
            // générer le jeton JWT
            String jwt = UtilisateurController.jeton;
            // ajouter le jeton à l'en-tête d'autorisation
            response.setHeader("Authorization", "Bearer " + jwt);
            // continuer le traitement de la requête
        
        }
        else{
             System.out.println("MERDE IL N'Y A PAS DE USER MERDE");
             
            
        }
        return true;
        
    }
}

