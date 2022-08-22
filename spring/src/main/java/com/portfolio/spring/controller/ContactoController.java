package com.portfolio.spring.controller;

import com.portfolio.spring.dto.ContactoDto;
import com.portfolio.spring.dto.Mensaje;
import com.portfolio.spring.modelo.Contacto;
import com.portfolio.spring.service.ContactoService;
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
@RequestMapping("/contacto/")
public class ContactoController {

    @Autowired
    ContactoService contactoServ;

    @GetMapping("/lista")
    public ResponseEntity<List<Contacto>> list() {
        List<Contacto> list = contactoServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Contacto> getById(@PathVariable("id") Integer id) {
        if (!contactoServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese id"), HttpStatus.NOT_FOUND);
        }

        Contacto cont = contactoServ.getOne(id).get();
        return new ResponseEntity(cont, HttpStatus.OK);
    }

    @GetMapping("/detailName/{nombre}")
    public ResponseEntity<Contacto> getByNombre(@PathVariable("nombre") String nombreC) {
        if (!contactoServ.existsByNombreC(nombreC)) {
            return new ResponseEntity(new Mensaje("El contacto no existe"), HttpStatus.NOT_FOUND);
        }

        Contacto cont = contactoServ.getByNombreC(nombreC).get();
        return new ResponseEntity(cont, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody Contacto cont) {
        contactoServ.save(cont);
        return new ResponseEntity(new Mensaje("Contacto creado con éxito"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!contactoServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe ese id"), HttpStatus.NOT_FOUND);

        }
        contactoServ.delete(id);
        return new ResponseEntity(new Mensaje("Contacto eliminado"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ContactoDto dtoconta
    ) {

        if (!contactoServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (contactoServ.existsByNombreC(dtoconta.getNombreC()) && contactoServ.getByNombreC(dtoconta.getNombreC()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese contacto ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoconta.getNombreC())) {
            return new ResponseEntity(new Mensaje("El nombre del contacto es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Contacto contacto = contactoServ.getOne(id).get();
        contacto.setNombreC(dtoconta.getNombreC());
        contacto.setApellidoC(dtoconta.getApellidoC());
        contacto.setEmailC(dtoconta.getEmailC());
        contacto.setTel(dtoconta.getTel());

        contactoServ.save(contacto);
        return new ResponseEntity(new Mensaje("Contacto actualizado"), HttpStatus.OK);
    }
}
