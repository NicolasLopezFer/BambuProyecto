package com.panda.bambu.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.article.Article;

@Entity   
public class Entry {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
    
    @NotNull(message="Code is compulsory")
	@Column(name = "code")
    private String code;
    
    @NotNull(message="Quantity is compulsory")
	@Column(name = "quanity")
    private String quanity;
    
    @NotNull(message="Unit Cost is compulsory")
	@Column(name = "unit_cost")
    private double unitCost;

    @NotNull(message="Total Cost is compulsory")
	@Column(name = "total_cost")
    private double totalCost;
    
    @ManyToOne()
    private Article article;
     
}