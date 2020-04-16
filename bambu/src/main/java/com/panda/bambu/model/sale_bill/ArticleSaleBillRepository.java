package com.panda.bambu.model.sale_bill;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import antlr.collections.List;

@Repository
public interface ArticleSaleBillRepository extends JpaRepository<ArticleSaleBill, Long> {
    ArticleSaleBill findByCode(String code);
    ArticleSaleBill findById(int id);
}
