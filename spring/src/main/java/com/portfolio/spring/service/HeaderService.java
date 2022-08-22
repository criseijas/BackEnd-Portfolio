package com.portfolio.spring.service;

import com.portfolio.spring.modelo.Header;
import com.portfolio.spring.repository.HeaderRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HeaderService {

    @Autowired
    HeaderRepository headerRepo;

    public List<Header> list() {
        return headerRepo.findAll();
    }

    public void save(Header header) {
        headerRepo.save(header);
    }

    public void delete(int id) {
        headerRepo.deleteById(id);
    }

    public boolean existsById(int id) {
        return headerRepo.existsById(id);
    }

    public Optional<Header> getOne(int id) {
        return headerRepo.findById(id);
    }

}
