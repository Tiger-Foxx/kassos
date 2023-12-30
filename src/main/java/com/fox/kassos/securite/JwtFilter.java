/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.securite;
import com.fox.kassos.Tools.ErrorController;
import com.fox.kassos.Tools.KassosMessage;
import com.fox.kassos.controleur.UtilisateurController;
import com.fox.kassos.entites.Utilisateur;
import com.fox.kassos.service.UtilisateurService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 *
 * @author FOX
 */
@Service
public class JwtFilter extends OncePerRequestFilter{
    @Autowired
   private  UtilisateurService utilisateurService;
    @Autowired
    private JwtService jwtService;

    public JwtFilter() {
    }

    public JwtFilter(UtilisateurService utilisateurService, JwtService jwtService) {
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;
    }
    public JwtFilter(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }
    void pass(){
        
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
         String token=null;
         String username=null;
         boolean isTokenExpired=true;
         //voila a quoi ressemble l'en-tete de la requete; le soucis est que si on la prends comme ca les carasctere ' Bearer ' seront comptes or on ne veux pas ca 
         // Bearer eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MDI5NzI0NjUsImV4cCI6MTcwMjk3NDI2NSwic3ViIjoiZG9uZmFja2FydGh1cjU1MEBnbWFpbC5jb20iLCJyb2xlIjpbeyJhdXRob3JpdHkiOiJST0xFX1VUSUxJU0FURVVSIn1dLCJlbWFpbCI6ImRvbmZhY2thcnRodXI1NTBAZ21haWwuY29tIiwibm9tIjoiRE9ORkFDSzIifQ.Jo4MARdzJG3dxI3DONGUz6Ldm2xSYoGf4c5jeGNF1WM
        String authorization = request.getHeader("Authorization");
        if ( ( authorization != null ) && (authorization.startsWith("Bearer "))) {
            token=authorization.substring(7);
            isTokenExpired=jwtService.isTokenExpired(token);
           username= jwtService.extractUsername(token);
            utilisateurService.loadUserByUsername(username);
        }
        else{
            
            try {
                 
                 if (UtilisateurController.is_con) {
                token=UtilisateurController.jeton;
                //System.out.println(" je suis dans le filtre et le token est : "+token);
                isTokenExpired=jwtService.isTokenExpired(token);
                username= jwtService.extractUsername(token);
                utilisateurService.loadUserByUsername(username);
            }
                
            } catch (Exception e) {
                e.printStackTrace();
                pass();
            }
           
        }
        
        if (!isTokenExpired && username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            Utilisateur utilisateur = (Utilisateur) utilisateurService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(utilisateur, null,utilisateur.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            
        }
        filterChain.doFilter(request, response);
      
    }
    
}
