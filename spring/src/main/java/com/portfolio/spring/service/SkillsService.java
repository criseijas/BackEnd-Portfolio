package com.portfolio.spring.service;

import com.portfolio.spring.modelo.Skills;
import com.portfolio.spring.repository.SkillsRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SkillsService {

    @Autowired
    SkillsRepository skillRepo;

    public List<Skills> list() {
        return skillRepo.findAll();
    }

    public Optional<Skills> getOne(int id) {
        return skillRepo.findById(id);
    }

    public Optional<Skills> getByNombreS(String nombreS) {
        return skillRepo.findByNombreS(nombreS);
    }

    public void save(Skills skills) {
        skillRepo.save(skills);
    }

    public void delete(int id) {
        skillRepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return skillRepo.existsById(id);
    }

    public boolean existsByNombreS(String nombreS) {
        return skillRepo.existsByNombreS(nombreS);
    }

}
