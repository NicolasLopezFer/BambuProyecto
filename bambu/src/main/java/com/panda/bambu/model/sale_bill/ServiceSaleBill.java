package com.panda.bambu.model.sale_bill;

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
public class ServiceSaleBill {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
    
    @NotNull(message="Code is compulsory")
	@Column(name = "code")
    private String code;

    @NotNull(message="Customer Name is compulsory")
    @Column(name = "customer_name")
    private String customer_name;

    @NotNull(message="nit is compulsory")
    @Column(name = "id_customer")
    private String idCustomer;
    
    @NotNull(message="Date is compulsory")
    @Column(name = "date")
    private LocalDate date;

    @NotNull(message="Expiration is compulsory")
    @Column(name = "expiration")
    private LocalDate expiration;
    
    @OneToMany()
    private Set<ServiceSale> services;

    @NotNull(message="Total is compulsory")
    @Column(name = "total")
    private LocalDate total;

}