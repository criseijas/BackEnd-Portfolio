package com.portfolio.spring.dto;

import javax.validation.constraints.NotBlank;

public class SkillsDto {

    @NotBlank
    private String nombreS;
    @NotBlank
    private String porcentajeS;

    public SkillsDto() {
    }

    public SkillsDto(String nombreS, String porcentajeS) {
        this.nombreS = nombreS;
        this.porcentajeS = porcentajeS;
    }

    public String getNombreS() {
        return nombreS;
    }

    public void setNombreS(String nombreS) {
        this.nombreS = nombreS;
    }

    public String getPorcentajeS() {
        return porcentajeS;
    }

    public void setPorcentajeS(String porcentajeS) {
        this.porcentajeS = porcentajeS;
    }

}
