package com.portfolio.spring.dto;

import javax.validation.constraints.NotBlank;

public class HeaderDto {

    @NotBlank
    private String urlP;
    private String urlLG;
    private String urlLI;
    private String urlLF;
    private String urlB;

    public HeaderDto() {
    }

    public HeaderDto(String urlP, String urlLG, String urlLI, String urlLF, String urlB) {
        this.urlP = urlP;
        this.urlLG = urlLG;
        this.urlLI = urlLI;
        this.urlLF = urlLF;
        this.urlB = urlB;
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
