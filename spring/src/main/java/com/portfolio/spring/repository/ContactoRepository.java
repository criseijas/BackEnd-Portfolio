
package com.portfolio.spring.repository;

import com.portfolio.spring.modelo.Contacto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
    
    Optional<Contacto> findByNombreC(String nombreC);
    
    boolean existsByNombreC(String nombreC);
    
}
