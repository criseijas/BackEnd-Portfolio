
package com.portfolio.spring.repository;

import com.portfolio.spring.modelo.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Integer> {
    
    public Optional<Experiencia> findByEmpresaE(String empresaE);
    
    public boolean existsByEmpresaE(String empresaE);
    
}
