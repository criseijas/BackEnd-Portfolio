package com.portfolio.spring.controller;

import com.portfolio.spring.dto.EducacionDto;
import com.portfolio.spring.dto.Mensaje;
import com.portfolio.spring.modelo.Educacion;
import com.portfolio.spring.service.EducacionService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "*")
public class EducacionController {

    @Autowired
    EducacionService educaServ;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = educaServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
        if (!educaServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = educaServ.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!educaServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        }
        educaServ.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EducacionDto dtoeducacion) {
        if (StringUtils.isBlank(dtoeducacion.getTituloEdu())) {
            return new ResponseEntity(new Mensaje("El título es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoeducacion.getInstitucionEdu())) {
            return new ResponseEntity(new Mensaje("La institución es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoeducacion.getInfoEdu())) {
            return new ResponseEntity(new Mensaje("Esta información es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (educaServ.existsByTituloEdu(dtoeducacion.getTituloEdu())) {
            return new ResponseEntity(new Mensaje("Ese título ya existe"), HttpStatus.BAD_REQUEST);
        }
        Educacion educacion = new Educacion(
                dtoeducacion.getTituloEdu(),
                dtoeducacion.getInstitucionEdu(),
                dtoeducacion.getInfoEdu()
        );

        educaServ.save(educacion);
        return new ResponseEntity(new Mensaje("La educación se creó correctamente"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EducacionDto dtoEducacion) {
        if (!educaServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        }
        if (educaServ.existsByTituloEdu(dtoEducacion.getTituloEdu()) && educaServ.getByTituloEdu(dtoEducacion.getTituloEdu())
                .get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese título ya existe"), HttpStatus.BAD_REQUEST);
        }
        //agregar los if que faltan de institucion lugar y año
        if (StringUtils.isBlank(dtoEducacion.getTituloEdu())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoEducacion.getInstitucionEdu())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoEducacion.getInfoEdu())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacío"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = educaServ.getOne(id).get();

        educacion.setInstitucionEdu(dtoEducacion.getInstitucionEdu());
        educacion.setTituloEdu(dtoEducacion.getTituloEdu());
        educacion.setInfoEdu(dtoEducacion.getInfoEdu());

        educaServ.save(educacion);

        return new ResponseEntity(new Mensaje("La educación se actualizó correctamente"), HttpStatus.OK);
    }

}
