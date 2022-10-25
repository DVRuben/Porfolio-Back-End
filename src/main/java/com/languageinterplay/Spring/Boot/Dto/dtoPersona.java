package com.languageinterplay.Spring.Boot.Dto;

import javax.validation.constraints.NotBlank;

public class dtoPersona {
    @NotBlank
    private String nombre;
    @NotBlank
    private String position;
    private String img;
    private String presentation;

    public dtoPersona() {
    }

    public dtoPersona(String nombre, String position, String img, String presentation) {
        this.nombre = nombre;
        this.position = position;
        this.img = img;
        this.presentation = presentation;
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

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
}
