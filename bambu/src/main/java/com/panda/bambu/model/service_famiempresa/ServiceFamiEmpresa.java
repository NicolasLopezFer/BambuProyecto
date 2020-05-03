package com.panda.bambu.model.service_famiempresa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.article.Article;


@Entity 
public class ServiceFamiEmpresa {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
    
    @NotNull(message="Code is compulsory")
	@Column(name = "code", unique=true)
    private String code;
    
    @NotNull(message="Name is compulsory")
	@Column(name = "name")
    private String name;
    
    @NotNull(message="Price is compulsory")
	@Column(name = "price")
    private double price;

    @NotNull(message="Cost is compulsory")
	@Column(name = "cost")
    private double cost;

    @OneToMany()
    private List<Article> articles;

    public Long getId()
    {
        return id;
    }

    public String getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public double getCost()
    {
        return cost;
    }

    public List<Article> getArticles()
    {
        return articles;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void getPrice(double price)
    {
        this.price = price;
    }

    public void getCost(double cost)
    {
        this.cost = cost;
    }

    public void setArticles(List<Article> articles)
    {
        this.articles = articles;
    }


}