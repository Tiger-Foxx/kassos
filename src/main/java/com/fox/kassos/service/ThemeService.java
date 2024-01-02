/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.service;

import com.fox.kassos.entites.Theme;
import com.fox.kassos.repository.ThemeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FOX
 */

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public ThemeService() {
    }
    
    public List<Theme> getAllThemes(){
        return this.themeRepository.findAll();
    }
}
