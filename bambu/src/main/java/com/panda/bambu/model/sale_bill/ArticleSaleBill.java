package com.panda.bambu.model.sale_bill;

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
public class ArticleSaleBill{
     
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
    
    @NotNull(message="Code is compulsory")
	@Column(name = "code", unique=true)
    private String code;

    @NotNull(message="Customer Name is compulsory")
    @Column(name = "customer_name")
    private String customer_name;

    @NotNull(message="Id Customer is compulsory")
    @Column(name = "id_customer")
    private String idCustomer;
    
    @NotNull(message="Date is compulsory")
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "expiration")
    private LocalDate expiration;
    
    @OneToMany()
    private List<ArticleSale> articles;

    @NotNull(message="Total is compulsory")
    @Column(name = "total")
    private double total;
    
    public ArticleSaleBill(){
         date = LocalDate.now();
         articles = new ArrayList<ArticleSale>();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public List<ArticleSale> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleSale> articles) {
        this.articles = articles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((articles == null) ? 0 : articles.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((customer_name == null) ? 0 : customer_name.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((expiration == null) ? 0 : expiration.hashCode());
        result = prime * result + ((idCustomer == null) ? 0 : idCustomer.hashCode());
        long temp;
        temp = Double.doubleToLongBits(total);
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
        ArticleSaleBill other = (ArticleSaleBill) obj;
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
        if (expiration == null) {
            if (other.expiration != null)
                return false;
        } else if (!expiration.equals(other.expiration))
            return false;
        if (id != other.id)
            return false;
        if (idCustomer == null) {
            if (other.idCustomer != null)
                return false;
        } else if (!idCustomer.equals(other.idCustomer))
            return false;
        if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ArticleSaleBill [articles=" + articles + ", code=" + code + ", customer_name=" + customer_name
                + ", date=" + date + ", expiration=" + expiration + ", id=" + id + ", idCustomer=" + idCustomer
                + ", total=" + total + "]";
    }    
    
}