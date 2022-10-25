package com.languageinterplay.Spring.Boot.controller.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String position;
    private String img;
    private String presentation;

    public Persona() {
    }

    public Persona(String nombre, String position, String img, String presentation) {
        this.nombre = nombre;
        this.position = position;
        this.img = img;
        this.presentation = presentation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    public String getPresentation() {
        return presentation;
    }
    
    public void setPresentation(String presentation){
        this.presentation = presentation;
    }
}
