package com.panda.bambu.model.egreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EgresoRepository extends JpaRepository<Egreso, Long> {
         
      public Egreso findByNumeroComprobante(int numero_comprobante);
}