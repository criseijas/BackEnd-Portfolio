package com.portfolio.spring.service;

import com.portfolio.spring.modelo.Experiencia;
import com.portfolio.spring.repository.ExperienciaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExperienciaService {

    @Autowired
    ExperienciaRepository expeRepo;

    public List<Experiencia> list() {
        return expeRepo.findAll();
    }

    public Optional<Experiencia> getOne(int id) {
        return expeRepo.findById(id);
    }

    public Optional<Experiencia> getByEmpresaE(String empresaE) {
        return expeRepo.findByEmpresaE(empresaE);
    }

    public void save(Experiencia experiencia) {
        expeRepo.save(experiencia);
    }

    public void delete(int id) {
        expeRepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return expeRepo.existsById(id);
    }

    public boolean existByEmpresaE(String empresaE) {
        return expeRepo.existsByEmpresaE(empresaE);
    }
}
