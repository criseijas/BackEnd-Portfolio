
package com.portfolio.spring.repository;

import com.portfolio.spring.modelo.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository extends JpaRepository<Header, Integer> {
    
}
