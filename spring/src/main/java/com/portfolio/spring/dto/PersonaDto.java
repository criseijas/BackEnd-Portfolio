/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.spring.dto;

import javax.validation.constraints.NotBlank;

public class PersonaDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    

    public PersonaDto() {
    }

    public PersonaDto(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        
    }

}
