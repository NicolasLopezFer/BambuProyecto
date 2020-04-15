package com.panda.bambu.model.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.article.Article;

@Entity
@Table(
        uniqueConstraints=
            @UniqueConstraint(columnNames={"code"})
    )  
public class Balance {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
    
    @NotNull(message="Code is compulsory")
	@Column(name = "code", unique=true)
    private String code;
    
    @NotNull(message="Quantity is compulsory")
	@Column(name = "quantity")
    private int quantity;

    @NotNull(message="Total Cost is compulsory")
	@Column(name = "total_cost")
    private double totalCost;
    
    @ManyToOne()
    private Article article;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((article == null) ? 0 : article.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + id;
        result = prime * result + quantity;
        long temp;
        temp = Double.doubleToLongBits(totalCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Balance other = (Balance) obj;
        if (article == null) {
            if (other.article != null)
                return false;
        } else if (!article.equals(other.article))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (id != other.id)
            return false;
        if (quantity != other.quantity)
            return false;
        if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Balance [article=" + article + ", code=" + code + ", id=" + id + ", quantity=" + quantity
                + ", totalCost=" + totalCost + "]";
    }
        
}