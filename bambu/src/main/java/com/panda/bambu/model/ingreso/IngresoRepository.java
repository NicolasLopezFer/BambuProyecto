package com.panda.bambu.model.ingreso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
         
      public Ingreso findByNumeroComprobante(int numero_comprobante);
}