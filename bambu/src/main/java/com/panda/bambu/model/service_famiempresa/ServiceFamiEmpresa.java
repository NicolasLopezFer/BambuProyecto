package com.panda.bambu.model.service_famiempresa;

import java.util.ArrayList;
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

    @OneToMany
    private List<ServiceArticle> articles;

    public ServiceFamiEmpresa()
    {
        
    }

    public ServiceFamiEmpresa (String code, String name, double price)
    {
        this.code = code;
        this.name = name;
        this.price = price;
        articles = new ArrayList<ServiceArticle> ();
    }

    public ServiceFamiEmpresa (String code, String name, double price, List<ServiceArticle> articles)
    {
        this.code = code;
        this.name = name;
        this.price = price;
        this.articles = articles;
    }

    public void removeArticle(ServiceArticle article)
    {
        articles.remove(article);
    }

    public void addArticle(ServiceArticle new_article)
    {
        articles.add(new_article);
    }

    public void addArticle(Article article, double quantity)
    {
        articles.add(new ServiceArticle(article,quantity));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<ServiceArticle> articles) {
        this.articles = articles;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((articles == null) ? 0 : articles.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(price);
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
        ServiceFamiEmpresa other = (ServiceFamiEmpresa) obj;
        if (articles == null) {
            if (other.articles != null)
                return false;
        } else if (!articles.equals(other.articles))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ServiceFamiEmpresa [code=" + code + ", articles="+articles+", id=" + id + ", name=" + name + ", price=" + price + "]";
    }


}