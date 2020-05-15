package com.panda.bambu.model.recibo_caja;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReciboCajaRepository extends JpaRepository<ReciboCaja, Long> {
         
      public ReciboCaja findByNumeroComprobante(int numero_comprobante);
}