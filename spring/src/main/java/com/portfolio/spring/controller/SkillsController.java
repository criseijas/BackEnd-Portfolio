package com.portfolio.spring.controller;

import com.portfolio.spring.dto.Mensaje;
import com.portfolio.spring.dto.SkillsDto;
import com.portfolio.spring.modelo.Skills;
import com.portfolio.spring.service.SkillsService;
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
@RequestMapping("/skills")
public class SkillsController {

    @Autowired
    SkillsService skillServ;

    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = skillServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!skillServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);

        }

        Skills skills = skillServ.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SkillsDto dtoskill) {
        if (StringUtils.isBlank(dtoskill.getNombreS())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (skillServ.existsByNombreS(dtoskill.getNombreS())) {
            return new ResponseEntity(new Mensaje("El Skills ya existe"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = new Skills(dtoskill.getNombreS(), dtoskill.getPorcentajeS());
        skillServ.save(skills);
        return new ResponseEntity(new Mensaje("Skill agregado correctamente"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody SkillsDto dtoskill) {
        
        if (!skillServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        
        if (skillServ.existsByNombreS(dtoskill.getNombreS()) && skillServ.getByNombreS(dtoskill.getNombreS()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("El Skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtoskill.getNombreS())) {
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = skillServ.getOne(id).get();
        skills.setNombreS(dtoskill.getNombreS());
        skills.setPorcentajeS(dtoskill.getPorcentajeS());

        skillServ.save(skills);
        return new ResponseEntity(new Mensaje("Skill actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!skillServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }

        skillServ.delete(id);

        return new ResponseEntity(new Mensaje("Skill eliminado"), HttpStatus.OK);
    }

}
