
package com.portfolio.spring.controller;

import com.portfolio.spring.dto.ExperienciaDto;
import com.portfolio.spring.dto.Mensaje;
import com.portfolio.spring.dto.ProyectoDto;
import com.portfolio.spring.modelo.Experiencia;
import com.portfolio.spring.modelo.Proyecto;
import com.portfolio.spring.service.ProyectoService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyecto")
public class ProyectoController {
    
    @Autowired
    ProyectoService proServ;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list() {
        List<Proyecto> list = proServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id) {
        if (!proServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id especificado"), HttpStatus.NOT_FOUND);
        }
        Proyecto proyecto = proServ.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!proServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        proServ.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProyectoDto dtopro
    ) {
        if (StringUtils.isBlank(dtopro.getNombreP())) {
            return new ResponseEntity(new Mensaje("El campo de la empresa es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (proServ.existByNombreP(dtopro.getNombreP())) {
            return new ResponseEntity(new Mensaje("Esa empresa existe"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = new Proyecto(dtopro.getNombreP(), dtopro.getDescripcionP(), dtopro.getImg(), dtopro.getRepo());
        proServ.save(proyecto);

        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProyectoDto dtopro
    ) {

        if (!proServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (proServ.existByNombreP(dtopro.getNombreP()) && proServ.getByNombreP(dtopro.getNombreP()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopro.getNombreP())) {
            return new ResponseEntity(new Mensaje("El nombre de la empresa es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Proyecto proyecto = proServ.getOne(id).get();
        proyecto.setNombreP(dtopro.getNombreP());
        proyecto.setDescripcionP(dtopro.getDescripcionP());
        proyecto.setImg(dtopro.getImg());
        proyecto.setRepo(dtopro.getRepo());

        proServ.save(proyecto);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }
    
}
