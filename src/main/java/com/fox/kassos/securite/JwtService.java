/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.securite;

import com.fox.kassos.dto.AuthentificationDTO;
import com.fox.kassos.entites.Utilisateur;
import com.fox.kassos.service.UtilisateurService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FOX
 */

@Service
public class JwtService {
    //BIENSUR INCLURE DANS LA PRESENTATION UNE DEMO AVEC LE SITE JWT.IO
    private final String ENCRYPTION_KEY="608f36e92dc66d97d5933f0e6371493cb4fc05b1aa8f8de64014732472303a7c";
    @Autowired
    private UtilisateurService utilisateurService;

    public JwtService() {
    }

    public JwtService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public UtilisateurService getUtilisateurService() {
        return utilisateurService;
    }

    public void setUtilisateurService(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    public Map<String,String>  generate(String username){
        Utilisateur utilisateur = (Utilisateur) this.utilisateurService.loadUserByUsername(username);
        Map<String, String> generatedJwt=this.generateJwt(utilisateur);
        System.out.println("le jeton est : "+generatedJwt);
        return generatedJwt;
    }

    private Map<String, String> generateJwt(Utilisateur utilisateur) {
        long currentTime = System.currentTimeMillis();//date du systeme en millisecondes
        long expirationTime = currentTime + 30 * 60 *1000; //j'ajoute 30min a la date de debut
        Map<String, Object> claims = Map.of(
                "nom",utilisateur.getNom(),
                "email",utilisateur.getEmail(),
                "role",utilisateur.getAuthorities(),
                Claims.EXPIRATION, new Date(expirationTime),
                Claims.SUBJECT,utilisateur.getEmail()
        
        );
        
        String bearer = Jwts.builder()
                .issuedAt( new Date(currentTime))
                .expiration(new Date(expirationTime))
                .subject(utilisateur.getEmail())
                .claims(claims)
                .signWith(getKey())
                .compact();
                 
        
        return Map.of("bearer",bearer);
    }

    private Key getKey() {
        byte[] decoder = Decoders.BASE64.decode(ENCRYPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    public String extractUsername(String token) {
        return this.getClaim(token,Claims::getSubject);
    }

    boolean  isTokenExpired(String token) {
        Date expirationDate= getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return this.getClaim(token,Claims::getExpiration);
    } 
    
    private <T> T getClaim(String token,Function<Claims,T> function){
         Claims claims=getAllClaims(token);
          
         return function.apply(claims);
    }

    private Claims getAllClaims(String token) {
        
        return Jwts.parser()
                    .setSigningKey(this.getKey())
                    .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
