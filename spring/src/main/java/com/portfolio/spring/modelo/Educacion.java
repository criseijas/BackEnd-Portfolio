package com.portfolio.spring.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String institucionEdu;
    @NotNull
    private String tituloEdu;
    private String infoEdu;

    public Educacion() {
    }

    public Educacion(String institucionEdu, String tituloEdu, String infoEdu) {
        this.institucionEdu = institucionEdu;
        this.tituloEdu = tituloEdu;
        this.infoEdu = infoEdu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
