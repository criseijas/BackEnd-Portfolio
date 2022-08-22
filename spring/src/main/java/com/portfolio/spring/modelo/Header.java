package com.portfolio.spring.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Header {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String urlP;
    private String urlLG;
    private String urlLI;
    private String urlLF;
    private String urlB;

    public Header() {
    }

    public Header(String urlP, String urlLG, String urlLI, String urlLF, String urlB) {
        this.urlP = urlP;
        this.urlLG = urlLG;
        this.urlLI = urlLI;
        this.urlLF = urlLF;
        this.urlB = urlB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlP() {
        return urlP;
    }

    public void setUrlP(String urlP) {
        this.urlP = urlP;
    }

    public String getUrlLG() {
        return urlLG;
    }

    public void setUrlLG(String urlLG) {
        this.urlLG = urlLG;
    }

    public String getUrlLI() {
        return urlLI;
    }

    public void setUrlLI(String urlLI) {
        this.urlLI = urlLI;
    }

    public String getUrlLF() {
        return urlLF;
    }

    public void setUrlLF(String urlLF) {
        this.urlLF = urlLF;
    }

    public String getUrlB() {
        return urlB;
    }

    public void setUrlB(String urlB) {
        this.urlB = urlB;
    }

}
