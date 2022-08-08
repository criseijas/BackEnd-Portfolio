package com.portfolio.spring.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @NotNull
    @Column(name="empresaE")
    private String empresaE;
    @NotNull
    @Column(name="puestoE")
    private String puestoE;
    @Column(name="anioE")
    private String anioE;
    @Column(name="tareasE")
    private String tareasE;

    public Experiencia() {
    }

    public Experiencia(String empresaE, String puestoE, String anioE, String tareasE) {
        this.empresaE = empresaE;
        this.puestoE = puestoE;
        this.anioE = anioE;
        this.tareasE = tareasE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
