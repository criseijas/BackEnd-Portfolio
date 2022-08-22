package com.portfolio.spring.service;

import com.portfolio.spring.modelo.Contacto;
import com.portfolio.spring.repository.ContactoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContactoService {

    @Autowired
    ContactoRepository contactoRepo;

    //para traer una lista de contactos
    public List<Contacto> list() {
        return contactoRepo.findAll();
    }

    //para obtener un contacto x su id
    public Optional<Contacto> getOne(int id) {
        return contactoRepo.findById(id);
    }

    //para buscar un contacto x su nombre
    public Optional<Contacto> getByNombreC(String nombreC) {
        return contactoRepo.findByNombreC(nombreC);
    }

    //para crear un contacto
    public void save(Contacto contacto) {
        contactoRepo.save(contacto);
    }

    //para eliminar un contacto x su id
    public void delete(int id) {
        contactoRepo.deleteById(id);
    }

    //para ver si existe un contacto x su id, si existe devuelve true
    public boolean existsById(int id) {
        return contactoRepo.existsById(id);
    }

    //para ver si existe un contacto x su nombre, si existe devuelve true
    public boolean existsByNombreC(String nombreC) {
        return contactoRepo.existsByNombreC(nombreC);
    }
}
