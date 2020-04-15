package com.panda.bambu.model.inventory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;



@Entity
public class Inventory {
     
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
    
    @NotNull(message="Date is compulsory")
    @Column(name = "date")
    private LocalDate date;

    @NotNull(message="Measure Method is compulsory")
    @Column(name = "measure_method")
    private MeasureMethod measureMethod;

    @OneToMany()
    private Set<Entry> entries;
    
    @OneToMany()
    private Set<Output> outputs;
    
    @OneToMany()
    private Set<Balance> balances;
    
    public Inventory(){
        
        date = LocalDate.now();
        outputs = new HashSet<Output>();
        entries = new HashSet<Entry>();
        balances = new HashSet<Balance>(); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public MeasureMethod getMeasureMethod() {
        return measureMethod;
    }

    public void setMeasureMethod(MeasureMethod measureMethod) {
        this.measureMethod = measureMethod;
    }

    public Set<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Set<Entry> entries) {
        this.entries = entries;
    }

    public Set<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(Set<Output> outputs) {
        this.outputs = outputs;
    }

    public Set<Balance> getBalances() {
        return balances;
    }

    public void setBalances(Set<Balance> balances) {
        this.balances = balances;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((balances == null) ? 0 : balances.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((entries == null) ? 0 : entries.hashCode());
        result = prime * result + id;
        result = prime * result + ((measureMethod == null) ? 0 : measureMethod.hashCode());
        result = prime * result + ((outputs == null) ? 0 : outputs.hashCode());
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
        Inventory other = (Inventory) obj;
        if (balances == null) {
            if (other.balances != null)
                return false;
        } else if (!balances.equals(other.balances))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (entries == null) {
            if (other.entries != null)
                return false;
        } else if (!entries.equals(other.entries))
            return false;
        if (id != other.id)
            return false;
        if (measureMethod != other.measureMethod)
            return false;
        if (outputs == null) {
            if (other.outputs != null)
                return false;
        } else if (!outputs.equals(other.outputs))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Inventory [balances=" + balances + ", date=" + date + ", entries=" + entries + ", id=" + id
                + ", measureMethod=" + measureMethod + ", outputs=" + outputs + "]";
    }

    

}