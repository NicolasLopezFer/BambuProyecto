package com.panda.bambu.model.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureMethodRepository extends JpaRepository<MeasureMethod, Long> {
    

}