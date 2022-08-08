package com.portfolio.spring.dto;

import javax.validation.constraints.NotBlank;

public class EducacionDto {

    @NotBlank
    private String institucionEdu;
    @NotBlank
    private String tituloEdu;
    @NotBlank
    private String infoEdu;

    public EducacionDto() {
    }

    public EducacionDto(String institucionEdu, String tituloEdu, String infoEdu) {
        this.institucionEdu = institucionEdu;
        this.tituloEdu = tituloEdu;
        this.infoEdu = infoEdu;
    }

    public String getInstitucionEdu() {
        return institucionEdu;
    }

    public void setInstitucionEdu(String institucionEdu) {
        this.institucionEdu = institucionEdu;
    }

    public String getTituloEdu() {
        return tituloEdu;
    }

    public void setTituloEdu(String tituloEdu) {
        this.tituloEdu = tituloEdu;
    }

    public String getInfoEdu() {
        return infoEdu;
    }

    public void setInfoEdu(String infoEdu) {
        this.infoEdu = infoEdu;
    }

}
