package com.portfolio.spring.dto;

import javax.validation.constraints.NotBlank;

public class ExperienciaDto {

    @NotBlank
    private String empresaE;
    @NotBlank
    private String puestoE;
    @NotBlank
    private String anioE;
    @NotBlank
    private String tareasE;

    public ExperienciaDto() {
    }

    public ExperienciaDto(String empresaE, String puestoE, String anioE, String tareasE) {
        this.empresaE = empresaE;
        this.puestoE = puestoE;
        this.anioE = anioE;
        this.tareasE = tareasE;
    }

    public String getEmpresaE() {
        return empresaE;
    }

    public void setEmpresaE(String empresaE) {
        this.empresaE = empresaE;
    }

    public String getPuestoE() {
        return puestoE;
    }

    public void setPuestoE(String puestoE) {
        this.puestoE = puestoE;
    }

    public String getAnioE() {
        return anioE;
    }

    public void setAnioE(String anioE) {
        this.anioE = anioE;
    }

    public String getTareasE() {
        return tareasE;
    }

    public void setTareasE(String tareasE) {
        this.tareasE = tareasE;
    }

}
