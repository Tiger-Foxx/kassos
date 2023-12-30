/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fox.kassos.Tools;

/**
 *
 * @author FOX
 */
public class KassosMessage {
    public String Title;
    public String content1="";
    public String content2="";

    public KassosMessage(String Title, String content1) {
        this.Title = Title;
        this.content1 = content1;
    }

    public KassosMessage(String Title, String content1, String content2) {
        this.Title = Title;
        this.content1 = content1;
        this.content2 = content2;
    }

    public KassosMessage(String Title) {
        this.Title = Title;
    }

    public KassosMessage() {
    }
}
