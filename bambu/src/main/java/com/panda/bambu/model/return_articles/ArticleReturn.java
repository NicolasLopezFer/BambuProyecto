package com.panda.bambu.model.return_articles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.article.Article;

@Entity
public class ArticleReturn {
     
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
            
    @NotNull(message="Quantity is compulsory")
	@Column(name = "quantity")
    private int quantity;

    @NotNull(message="Motive is compulsory")
    @Column(name = "motive")
    private String motive;

    @NotNull(message="Value is compulsory")
    @Column(name = "value")
    private double value;

    @NotNull(message="Number Sale Bill is compulsory")
	@Column(name = "n_sale")
    private String nSale;

    @ManyToOne()
    private Article article;

    public ArticleReturn(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getnSale() {
        return nSale;
    }

    public void setnSale(String nSale) {
        this.nSale = nSale;
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
        result = prime * result + ((motive == null) ? 0 : motive.hashCode());
        result = prime * result + ((nSale == null) ? 0 : nSale.hashCode());
        result = prime * result + quantity;
        long temp;
        temp = Double.doubleToLongBits(value);
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
        ArticleReturn other = (ArticleReturn) obj;
        if (article == null) {
            if (other.article != null)
                return false;
        } else if (!article.equals(other.article))
            return false;
        if (id != other.id)
            return false;
        if (motive == null) {
            if (other.motive != null)
                return false;
        } else if (!motive.equals(other.motive))
            return false;
        if (nSale == null) {
            if (other.nSale != null)
                return false;
        } else if (!nSale.equals(other.nSale))
            return false;
        if (quantity != other.quantity)
            return false;
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ArticleReturn [article=" + article + ", id=" + id + ", motive=" + motive + ", nSale=" + nSale
                + ", quantity=" + quantity + ", value=" + value + "]";
    }
 
          
    
       
}