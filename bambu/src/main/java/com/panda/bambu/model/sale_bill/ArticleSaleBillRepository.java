package com.panda.bambu.model.sale_bill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleSaleBillRepository extends JpaRepository<ArticleSaleBill, Long> {
    
}
