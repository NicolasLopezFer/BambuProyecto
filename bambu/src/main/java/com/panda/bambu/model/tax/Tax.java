package com.panda.bambu.model.tax;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Tax {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;

    @NotNull(message="Name is compulsory")
    @Column(name = "name")
    private String name;

    @NotNull(message="Value is compulsory")
    @Column(name = "value")
    private float value;

}