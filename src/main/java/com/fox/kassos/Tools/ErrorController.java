/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.Tools;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author FOX
 */

public class ErrorController {
    
   
    public ModelAndView GetErrView( KassosMessage message){
        ModelAndView modelAndView=new ModelAndView("404");
        modelAndView.addObject("message", message);
        return modelAndView;
    }
}
