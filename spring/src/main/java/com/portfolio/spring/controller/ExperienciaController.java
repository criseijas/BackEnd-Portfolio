package com.portfolio.spring.controller;

import com.portfolio.spring.dto.ExperienciaDto;
import com.portfolio.spring.dto.Mensaje;
import com.portfolio.spring.modelo.Experiencia;
import com.portfolio.spring.service.ExperienciaService;
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
@RequestMapping("/explab")
@CrossOrigin(origins = "*")
public class ExperienciaController {

    @Autowired
    ExperienciaService expeServ;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = expeServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id) {
        if (!expeServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id especificado"), HttpStatus.NOT_FOUND);
        }
        Experiencia experiencia = expeServ.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!expeServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        expeServ.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienciaDto dtoexp
    ) {
        if (StringUtils.isBlank(dtoexp.getEmpresaE())) {
            return new ResponseEntity(new Mensaje("El campo de la empresa es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (expeServ.existByEmpresaE(dtoexp.getEmpresaE())) {
            return new ResponseEntity(new Mensaje("Esa empresa existe"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = new Experiencia(dtoexp.getPuestoE(), dtoexp.getAnioE(), dtoexp.getEmpresaE(), dtoexp.getTareasE());
        expeServ.save(experiencia);

        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienciaDto dtoXp
    ) {

        if (!expeServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (expeServ.existByEmpresaE(dtoXp.getEmpresaE()) && expeServ.getByEmpresaE(dtoXp.getEmpresaE()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoXp.getEmpresaE())) {
            return new ResponseEntity(new Mensaje("El nombre de la empresa es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = expeServ.getOne(id).get();
        experiencia.setPuestoE(dtoXp.getPuestoE());
        experiencia.setAnioE(dtoXp.getAnioE());
        experiencia.setEmpresaE(dtoXp.getEmpresaE());
        experiencia.setTareasE(dtoXp.getTareasE());

        expeServ.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }

}
