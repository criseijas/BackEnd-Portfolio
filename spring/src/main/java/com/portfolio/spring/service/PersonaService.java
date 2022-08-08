
package com.portfolio.spring.service;

import com.portfolio.spring.modelo.Persona;
import com.portfolio.spring.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService {
    
    @Autowired
    
    PersonaRepository persoRepo;
    //para traer una lista de personas
    public List<Persona> list(){
        return persoRepo.findAll();
    }
    //para obtener una persona x su id
    public Optional<Persona> getOne(Long id){
        return persoRepo.findById(id);
    }
    //para buscar una persona x su nombre
    public Optional<Persona> getByNombre(String nombre){
        return persoRepo.findByNombre(nombre);
    }
    //para crear una persona
    public void save(Persona persona){
        persoRepo.save(persona);
    }
    //para borrar una persona x su id
    public void delete(Long id){
        persoRepo.deleteById(id);
    }
    //para ver si existe una persona x su id, si existe devuelve true
    public boolean existsById(Long id){
        return persoRepo.existsById(id);
    }
    //para ver si existe una persona x su nombre, si existe devuelve true
    public boolean existsByNombre(String nombre){
        return persoRepo.existsByNombre(nombre);
    }
    
}
