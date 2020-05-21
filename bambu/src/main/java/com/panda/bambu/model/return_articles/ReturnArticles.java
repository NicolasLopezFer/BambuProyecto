package com.panda.bambu.model.return_articles;

import java.time.LocalDate;
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
public class ReturnArticles {
   
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id; 
    

	@Column(name = "code", unique=true)
    private String code;


    @Column(name = "date")
    private LocalDate date;


    @Column(name = "customer_name")
    private String customer_name;


    @Column(name = "id_customer")
    private String idCustomer;
   

    @Column(name = "sale_return")
    private Boolean saleReturn;


    @Column(name = "buy_return")
    private Boolean buyReturn;

    @OneToMany()
    private List<ArticleReturn> articles;

    public ReturnArticles(){
        articles = new ArrayList<ArticleReturn>();

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Boolean getSaleReturn() {
        return saleReturn;
    }

    public void setSaleReturn(Boolean saleReturn) {
        this.saleReturn = saleReturn;
    }

    public Boolean getBuyReturn() {
        return buyReturn;
    }

    public void setBuyReturn(Boolean buyReturn) {
        this.buyReturn = buyReturn;
    }

    public List<ArticleReturn> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleReturn> articles) {
        this.articles = articles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((articles == null) ? 0 : articles.hashCode());
        result = prime * result + ((buyReturn == null) ? 0 : buyReturn.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((customer_name == null) ? 0 : customer_name.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((idCustomer == null) ? 0 : idCustomer.hashCode());
        result = prime * result + ((saleReturn == null) ? 0 : saleReturn.hashCode());
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
        ReturnArticles other = (ReturnArticles) obj;
        if (articles == null) {
            if (other.articles != null)
                return false;
        } else if (!articles.equals(other.articles))
            return false;
        if (buyReturn == null) {
            if (other.buyReturn != null)
                return false;
        } else if (!buyReturn.equals(other.buyReturn))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (customer_name == null) {
            if (other.customer_name != null)
                return false;
        } else if (!customer_name.equals(other.customer_name))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (id != other.id)
            return false;
        if (idCustomer == null) {
            if (other.idCustomer != null)
                return false;
        } else if (!idCustomer.equals(other.idCustomer))
            return false;
        if (saleReturn == null) {
            if (other.saleReturn != null)
                return false;
        } else if (!saleReturn.equals(other.saleReturn))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ReturnArticles [articles=" + articles + ", buyReturn=" + buyReturn + ", code=" + code
                + ", customer_name=" + customer_name + ", date=" + date + ", id=" + id + ", idCustomer=" + idCustomer
                + ", saleReturn=" + saleReturn + "]";
    }
  

}