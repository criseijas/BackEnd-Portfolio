package com.portfolio.spring.controller;

import com.portfolio.spring.dto.HeaderDto;
import com.portfolio.spring.dto.Mensaje;
import com.portfolio.spring.modelo.Header;
import com.portfolio.spring.service.HeaderService;
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
@RequestMapping("/header")
public class HeaderController {

    @Autowired
    HeaderService headerServ;

    @GetMapping("/lista")
    public ResponseEntity<List<Header>> list() {
        List<Header> list = headerServ.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!headerServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        headerServ.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HeaderDto dtoheader
    ) {
        if (StringUtils.isBlank(dtoheader.getUrlP())) {
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Header header = new Header(dtoheader.getUrlP(), dtoheader.getUrlLF(), dtoheader.getUrlLG(), dtoheader.getUrlLI(), dtoheader.getUrlB());
        headerServ.save(header);

        return new ResponseEntity(new Mensaje("campo agregado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody HeaderDto dtoheader
    ) {

        if (!headerServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoheader.getUrlP())) {
            return new ResponseEntity(new Mensaje("La imagen de perfil es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Header header = headerServ.getOne(id).get();
        header.setUrlP(dtoheader.getUrlP());
        header.setUrlLF(dtoheader.getUrlLF());
        header.setUrlLG(dtoheader.getUrlLG());
        header.setUrlLI(dtoheader.getUrlLI());
        header.setUrlB(dtoheader.getUrlB());

        headerServ.save(header);
        return new ResponseEntity(new Mensaje("Se realizo correctamente la actualización"), HttpStatus.OK);
    }

    @GetMapping("/foto/perfil")
    public Header findPerfil() {
        return headerServ.getOne((Integer) 1).orElse(null);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Header> getById(@PathVariable("id") int id) {
        if (!headerServ.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id especificado"), HttpStatus.NOT_FOUND);
        }
        Header header = headerServ.getOne(id).get();
        return new ResponseEntity(header, HttpStatus.OK);
    }

}
