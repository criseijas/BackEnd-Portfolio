package com.portfolio.spring.dto;

import javax.validation.constraints.NotBlank;

public class ContactoDto {

    @NotBlank
    private String nombreC;
    @NotBlank
    private String apellidoC;
    @NotBlank
    private String emailC;
    @NotBlank
    private String tel;

    public ContactoDto() {
    }

    public ContactoDto(String nombreC, String apellidoC, String emailC, String tel) {
        this.nombreC = nombreC;
        this.apellidoC = apellidoC;
        this.emailC = emailC;
        this.tel = tel;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getApellidoC() {
        return apellidoC;
    }

    public void setApellidoC(String apellidoC) {
        this.apellidoC = apellidoC;
    }

    public String getEmailC() {
        return emailC;
    }

    public void setEmailC(String emailC) {
        this.emailC = emailC;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
