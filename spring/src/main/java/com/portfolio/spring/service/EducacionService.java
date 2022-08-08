package com.portfolio.spring.service;

import com.portfolio.spring.modelo.Educacion;
import com.portfolio.spring.repository.EducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EducacionService {

    @Autowired
    EducacionRepository educaRepo;

    public List<Educacion> list() {
        return educaRepo.findAll();
    }

    public Optional<Educacion> getOne(int id) {
        return educaRepo.findById(id);
    }

    public Optional<Educacion> getByTituloEdu(String tituloEdu) {
        return educaRepo.findByTituloEdu(tituloEdu);
    }

    public void save(Educacion educacion) {
        educaRepo.save(educacion);
    }

    public void delete(int id) {
        educaRepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return educaRepo.existsById(id);
    }

    public boolean existsByTituloEdu(String tituloEdu) {
        return educaRepo.existsByTituloEdu(tituloEdu);
    }

}
