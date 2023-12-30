/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.service;

import com.fox.kassos.entites.Story;
import com.fox.kassos.repository.StoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author FOX
 */

@Service
public class StoryService {
    @Autowired
    private StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public StoryService() {
    }
    public List<Story> getStories(){
        return this.storyRepository.findAll();
    }
    
    public void AjoutStory(Story story){
        this.storyRepository.save(story);
    }
}
