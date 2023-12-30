/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.fox.kassos.dto;

/**
 *
 * @author FOX
 */
public class AuthentificationDTO {

    private  String username;
    private  String password;

    public AuthentificationDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  
    public AuthentificationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
