package com.portfolio.spring.controller;

import com.portfolio.spring.dto.Mensaje;
import com.portfolio.spring.dto.PersonaDto;
import com.portfolio.spring.modelo.Persona;
import com.portfolio.spring.service.PersonaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona/")
public class PersonaController {

    @Autowired
    PersonaService persoServ;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = persoServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") Long id) {
        if (!persoServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese id"), HttpStatus.NOT_FOUND);

        }

        Persona per = persoServ.getOne(id).get();
        return new ResponseEntity(per, HttpStatus.OK);
    }

    @GetMapping("/detailName/{nombre}")
    public ResponseEntity<Persona> getByNombre(@PathVariable("nombre") String nombre) {
        if (!persoServ.existsByNombre(nombre)) {
            return new ResponseEntity(new Mensaje("El nombre no existe"), HttpStatus.NOT_FOUND);
        }

        Persona per = persoServ.getByNombre(nombre).get();
        return new ResponseEntity(per, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody Persona per) {
        persoServ.save(per);
        return new ResponseEntity(new Mensaje("Persona creada con éxito"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!persoServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese id"), HttpStatus.NOT_FOUND);

        }
        persoServ.delete(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> upDate(@PathVariable("id") long id, @RequestBody PersonaDto Dtoper) {
        if (!persoServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no Existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(Dtoper.getNombre())) {
            return new ResponseEntity(new Mensaje("El Nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(Dtoper.getApellido())) {
            return new ResponseEntity(new Mensaje("El Apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = persoServ.getOne(id).get();
        persona.setNombre(Dtoper.getNombre());
        persona.setTitulo(Dtoper.getTitulo());
        persona.setApellido(Dtoper.getApellido());
        persona.setDescripcion(Dtoper.getDescripcion());

        persoServ.save(persona);
        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }

    @GetMapping("/traer/perfil")
    public Persona findPersona() {
        return persoServ.getOne((long) 1).orElse(null);
    }

}
