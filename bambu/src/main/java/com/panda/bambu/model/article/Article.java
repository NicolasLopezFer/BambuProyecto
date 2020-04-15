package com.panda.bambu.model.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity   
public class Article {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
    
    @Id
    @NotNull(message="Code is compulsory")
	@Column(name = "code")
    private String code;
    
    @NotNull(message="Name is compulsory")
	@Column(name = "name")
    private String name;
    
    @NotNull(message="Quantity is compulsory")
	@Column(name = "quantity")
    private int quantity;
    
    @NotNull(message="Unit Cost is compulsory")
	@Column(name = "unit_cost")
    private double unitCost;

    @NotNull(message="Total Cost is compulsory")
	@Column(name = "total_cost")
    private double totalCost;

    @NotNull(message="Sale Price is compulsory")
	@Column(name = "sale_price")
    private double salePrice;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + quantity;
        long temp;
        temp = Double.doubleToLongBits(salePrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Article other = (Article) obj;
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
        if (quantity != other.quantity)
            return false;
        if (Double.doubleToLongBits(salePrice) != Double.doubleToLongBits(other.salePrice))
            return false;
        if (Double.doubleToLongBits(totalCost) != Double.doubleToLongBits(other.totalCost))
            return false;
        if (Double.doubleToLongBits(unitCost) != Double.doubleToLongBits(other.unitCost))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Article [code=" + code + ", id=" + id + ", name=" + name + ", quantity=" + quantity + ", salePrice="
                + salePrice + ", totalCost=" + totalCost + ", unitCost=" + unitCost + "]";
    }

    public Article(@NotNull(message = "Code is compulsory") String code,
            @NotNull(message = "Name is compulsory") String name,
            @NotNull(message = "Sale Price is compulsory") double salePrice) {
        this.code = code;
        this.name = name;
        this.salePrice = salePrice;
        this.quantity=0;
        this.unitCost=0.0;
        this.totalCost=0.0;
    }

    public Article() {
    }
    
    
    

}