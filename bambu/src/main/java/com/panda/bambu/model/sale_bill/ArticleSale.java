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
	@Column(name = "quantity")
    private int quantity;

    @NotNull(message="Discount is compulsory")
	@Column(name = "discount")
    private float discount;

    @NotNull(message="Tax is compulsory")
	@Column(name = "tax")
    private float tax;

    @NotNull(message="Total is compulsory")
	@Column(name = "total")
    private float total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((article == null) ? 0 : article.hashCode());
        result = prime * result + Float.floatToIntBits(discount);
        result = prime * result + id;
        result = prime * result + quantity;
        result = prime * result + Float.floatToIntBits(tax);
        result = prime * result + Float.floatToIntBits(total);
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
        ArticleSale other = (ArticleSale) obj;
        if (article == null) {
            if (other.article != null)
                return false;
        } else if (!article.equals(other.article))
            return false;
        if (Float.floatToIntBits(discount) != Float.floatToIntBits(other.discount))
            return false;
        if (id != other.id)
            return false;
        if (quantity != other.quantity)
            return false;
        if (Float.floatToIntBits(tax) != Float.floatToIntBits(other.tax))
            return false;
        if (Float.floatToIntBits(total) != Float.floatToIntBits(other.total))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ArticleSale [article=" + article + ", discount=" + discount + ", id=" + id + ", quantity=" + quantity
                + ", tax=" + tax + ", total=" + total + "]";
    }
   
 
}