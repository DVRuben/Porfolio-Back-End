package com.languageinterplay.Spring.Boot.Dto;

import javax.validation.constraints.NotBlank;

public class dtoSkill {
    @NotBlank
    private String nombreSkill;
    private String imgSkill;

    public dtoSkill() {
    }

    public dtoSkill(String nombreSkill, String imgSkill) {
        this.nombreSkill = nombreSkill;
        this.imgSkill = imgSkill;
    }

    public String getNombreSkill() {
        return nombreSkill;
    }

    public void setNombreSkill(String nombreSkill) {
        this.nombreSkill = nombreSkill;
    }

    public String getImgSkill() {
        return imgSkill;
    }

    public void setImgSkill(String imgSkill) {
        this.imgSkill = imgSkill;
    }
}
