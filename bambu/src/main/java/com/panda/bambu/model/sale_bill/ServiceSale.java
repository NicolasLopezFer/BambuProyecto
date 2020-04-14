package com.panda.bambu.model.sale_bill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class ServiceSale {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
    
    @NotNull(message="Code is compulsory")
	@Column(name = "code")
    private String code;
    
    @NotNull(message="Name is compulsory")
	@Column(name = "name")
    private String name;
    
    @NotNull(message="Unit Cost is compulsory")
	@Column(name = "unit_cost")
    private double unitCost;

    @NotNull(message="Price is compulsory")
	@Column(name = "price")
    private double price;
     
}