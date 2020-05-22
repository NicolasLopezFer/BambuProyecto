package com.panda.bambu.model.service_famiempresa;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.article.Article;


@Entity 
public class ServiceArticle {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;

    @NotNull(message="Quantity is compulsory")
	@Column(name = "quantity")
    private double quantity;

    @ManyToOne
    private Article article;

    public ServiceArticle()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceArticle(Article article, double quantity)
    {
        this.article = article;
        this.quantity = quantity;
    }

    public double getQuantity()
    {
        return quantity;
    }

    public void setQuantity(double quantity)
    {
        this.quantity = quantity;
    }

    public Article getArticle()
    {
        return article;
    }

    public void setArticle(Article article)
    {
        this.article = article;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServiceArticle other = (ServiceArticle) obj;
        if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
            return false;
        if (id != other.id)
            return false;
        if (!this.article.equals(other.article))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ServiceFamiEmpresa [article="+article+", id=" + id + ", quantity=" + quantity + "]";
    }

}