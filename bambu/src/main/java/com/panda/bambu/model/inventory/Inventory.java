package com.panda.bambu.model.inventory;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;



@Entity
public class Inventory {
     
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
    
    @NotNull(message="Date is compulsory")
    @Column(name = "date")
    private LocalDate date;

    @NotNull(message="Measure Method is compulsory")
    @Column(name = "measure_method")
    private MeasureMethod measureMethod;

    @OneToMany()
    private Set<Entry> entries;
    
    @OneToMany()
    private Set<Output> outputs;
    
    @OneToMany()
    private Set<Balance> balances;

}