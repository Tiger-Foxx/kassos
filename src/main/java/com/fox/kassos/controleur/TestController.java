/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.controleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author FOX
 */

@Controller
@RequestMapping(path = "fox")
public class TestController {

    @GetMapping(path = "test")
    public ModelAndView getIndexPage() {

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
