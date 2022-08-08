
package com.portfolio.spring.repository;

import com.portfolio.spring.modelo.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Integer> {
    
    public Optional<Educacion> findByTituloEdu (String tituloEdu);
    
    public boolean existsByTituloEdu(String tituloEdu);
    
}
