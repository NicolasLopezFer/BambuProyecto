package com.panda.bambu.model.sale_bill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.article.Article;

@Entity
public class ArticleSale {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;

    @ManyToOne()
    private Article article;
            
    @NotNull(message="Quantity is compulsory")
	@Column(name = "quanity")
    private String quanity;

    @NotNull(message="Discount is compulsory")
	@Column(name = "discount")
    private float discount;

    @NotNull(message="Tax is compulsory")
	@Column(name = "tax")
    private float tax;

    @NotNull(message="Total is compulsory")
	@Column(name = "total")
    private float total;
 
}