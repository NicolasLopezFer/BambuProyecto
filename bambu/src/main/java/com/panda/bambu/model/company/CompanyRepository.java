package com.panda.bambu.model.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
         
      public Company findByNit(Long nit);
      public Company deleteByNit(Long nit);
}
