package com.portfolio.spring.dto;

import javax.validation.constraints.NotBlank;

public class ProyectoDto {

    @NotBlank
    private String nombreP;
    @NotBlank
    private String descripcionP;
    @NotBlank
    private String img;
    @NotBlank
    private String repo;

    public ProyectoDto() {
    }

    public ProyectoDto(String nombreP, String descripcionP, String img, String repo) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.img = img;
        this.repo = repo;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

}
