package com.portfolio.spring.service;

import com.portfolio.spring.modelo.Proyecto;
import com.portfolio.spring.repository.ProyectoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    ProyectoRepository proRepo;

    public List<Proyecto> list() {
        return proRepo.findAll();
    }

    public void save(Proyecto proyecto) {
        proRepo.save(proyecto);
    }

    public void delete(int id) {
        proRepo.deleteById(id);
    }

    public Optional<Proyecto> getByNombreP(String nombreP) {
        return proRepo.findByNombreP(nombreP);
    }

    public boolean existsById(int id) {
        return proRepo.existsById(id);
    }

    public boolean existByNombreP(String nombreP) {
        return proRepo.existsByNombreP(nombreP);
    }

    public Optional<Proyecto> getOne(int id) {
        return proRepo.findById(id);
    }

}
