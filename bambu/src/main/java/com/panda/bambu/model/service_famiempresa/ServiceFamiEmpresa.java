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



@Entity 
public class ServiceFamiEmpresa {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
    
    @NotNull(message="Code is compulsory")
	@Column(name = "code")
    private String code;
    
    @NotNull(message="Name is compulsory")
	@Column(name = "name")
    private String name;

    @NotNull(message="Price is compulsory")
	@Column(name = "price")
    private double price;

    @NotNull(message="Active is compulsory")
	@Column(name = "active")
    private boolean active;

    @OneToMany
    private List<ServiceArticle> service_articles;

    public ServiceFamiEmpresa()
    {
        this.active = true;
    }

    public ServiceFamiEmpresa (String code, String name, double price)
    {
        this.code = code;
        this.name = name;
        this.price = price;
        service_articles = new ArrayList<ServiceArticle> ();
        this.active = true;
    }

    public ServiceFamiEmpresa (String code, String name, double price, List<ServiceArticle> service_articles)
    {
        this.code = code;
        this.name = name;
        this.price = price;
        this.service_articles = service_articles;
        this.active = true;
    }

    public void removeArticle(ServiceArticle service_article)
    {
        service_articles.remove(service_article);
    }

    public void addArticle(ServiceArticle new_article)
    {
        service_articles.add(new_article);
    }

    public void setInactive()
    {
        this.active = false;
    }

    public void setActive()
    {
        this.active = true;
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

    public boolean isActive()
    {
        return active;
    }

    public List<ServiceArticle> getArticles() {
        return service_articles;
    }

    public void setArticles(List<ServiceArticle> service_articles) {
        this.service_articles = service_articles;
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
        result = prime * result + ((service_articles == null) ? 0 : service_articles.hashCode());
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
        if (service_articles == null) {
            if (other.service_articles != null)
                return false;
        } else if (!service_articles.equals(other.service_articles))
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
        return "ServiceFamiEmpresa [code=" + code + ", articles="+service_articles+", id=" + id + ", name=" + name + ", price=" + price + "]";
    }


}