/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.service;

import com.fox.kassos.entites.Utilisateur;
import com.fox.kassos.repository.UtilisateurRepository;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author FOX
 */
@Service
public class UtilisateurService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, BCryptPasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UtilisateurRepository getUtilisateurRepository() {
        return utilisateurRepository;
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public UtilisateurService() {
    }

    public Utilisateur UnUtilisateur(int id) {
        return this.utilisateurRepository.findById(id).orElse(new Utilisateur());
    }

    public void inscription(Utilisateur utilisateur) throws SQLIntegrityConstraintViolationException { // IL FAUT SURTOUT EVOQUER CECI POUR FLEX UN PEU : icic je remonte cela au cas ou il ya un utilisateur avec le meme email deja ducoup je peux traiter l'erreur dans le controleur
        //MAIS AVANT JE ME SOUVIENT QU'ON NE STOCKE PAS LES MOTS DE PASSE EN CLAIR
        String mdpCrypt = this.passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(mdpCrypt);
        try {
            this.utilisateurRepository.save(utilisateur);
        } catch (Exception e) {
            System.err.println("L'utilisateur existe déjà dans la base de données");
            // propager l'exception vers la méthode appelante
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.utilisateurRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("ah merde il n'est pas ici"));

    }

    public void supprimer(int id) {
        this.utilisateurRepository.deleteById(id);
    }

    public void modifier(Utilisateur utilisateur) {
        this.utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> AllUtilisateurs() {
        return (List<Utilisateur>) this.utilisateurRepository.findAll();
    }

    public List<Utilisateur> getRandomUtilisateurs(int n) {
        List<Utilisateur> allUtilisateurs = utilisateurRepository.findAllRandom();
        return allUtilisateurs.subList(0, n);
    }
    
    public List<Utilisateur> AllUtilisateursStory() {
        return (List<Utilisateur>) this.utilisateurRepository.findByStory();
    }
}
