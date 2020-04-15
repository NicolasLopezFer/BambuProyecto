package com.panda.bambu.model.inventory;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.article.Article;

@Entity
public class Output {
     
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
    
    @NotNull(message="Code is compulsory")
	@Column(name = "code", unique=true)
    private String code;
    
    @NotNull(message="Date is compulsory")
    @Column(name = "date")
    private LocalDate date;

    @NotNull(message="Detail is compulsory")
    @Column(name = "detail")
    private String detail;
    
    @NotNull(message="Quantity is compulsory")
	@Column(name = "quantity")
    private int quantity;
    
    @NotNull(message="Unit Cost is compulsory")
	@Column(name = "unit_cost")
    private double unitCost;

    @NotNull(message="Total Cost is compulsory")
	@Column(name = "total_cost")
    private double totalCost;
    
    @ManyToOne()
    private Article article;

    public Output(String code, String detail, int quantity, double unitCost, double totalCost){
        this.code = code;
        date = LocalDate.now();
        this.detail = detail;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.totalCost = totalCost;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
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
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((detail == null) ? 0 : detail.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + quantity;
        long temp;
        temp = Double.doubleToLongBits(totalCost);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(unitCost);
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
        Output other = (Output) obj;
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
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (detail == null) {
            if (other.detail != null)
                return false;
        } else if (!detail.equals(other.detail))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (quantity != other.quantity)
            return false;
        if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
            return false;
        if (Double.doubleToLongBits(unitCost) != Double.doubleToLongBits(other.unitCost))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Output [article=" + article + ", code=" + code + ", date=" + date + ", detail=" + detail + ", id=" + id
                + ", quantity=" + quantity + ", totalCost=" + totalCost + ", unitCost=" + unitCost + "]";
    }       
}