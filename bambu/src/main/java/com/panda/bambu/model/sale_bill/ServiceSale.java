package com.panda.bambu.model.sale_bill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresa;


@Entity
public class ServiceSale {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
    
    @ManyToOne
    private ServiceFamiEmpresa serviceFamiEmpresa;
    
    @NotNull(message="Quantity is compulsory")
	@Column(name = "quantity")
    private double quantity;

    @Column(name = "discount")
    private float discount;
    
    @NotNull(message="Total Value is compulsory")
	@Column(name = "total_value")
    private double total_value;

    public ServiceSale()
    {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getDiscount()
    {
        return discount;
    }

    public void setDiscount(float discount)
    {
        this.discount = discount;
    }

    public double getTotalValue(double total_value) {
        return total_value;
    }

    public void setTotalValue(double total_value) {
        this.total_value = total_value;
    }

    public ServiceFamiEmpresa getServiceFamiEmpresa() {
        return serviceFamiEmpresa;
    }

    public void setServiceFamiEmpresa(ServiceFamiEmpresa serviceFamiEmpresa) {
        this.serviceFamiEmpresa = serviceFamiEmpresa;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        result = prime * result + ((serviceFamiEmpresa == null) ? 0 : serviceFamiEmpresa.hashCode());
        temp = Double.doubleToLongBits(quantity);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(total_value);
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
        ServiceSale other = (ServiceSale) obj;
        if (serviceFamiEmpresa == null) {
            if (other.serviceFamiEmpresa != null)
                return false;
        } else if (!serviceFamiEmpresa.equals(other.serviceFamiEmpresa))
            return false;
        if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
            return false;
        if (Double.doubleToLongBits(total_value) != Double.doubleToLongBits(other.total_value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ServiceSale [ id=" + id + ", serviceFamiEmpresa="+ serviceFamiEmpresa +" quantity=" + quantity + ", total_value=" + total_value + "]";
    }

        
}