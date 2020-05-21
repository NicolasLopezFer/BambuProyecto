package com.panda.bambu.model.inventory;

import java.time.LocalDate;

public class Kardex 
{

    private Long id;
    
    private String code;
    
    private LocalDate date;

    private String detail;
    
    private int quantity;

    private double unitCost;

    private double totalCost;

    private String type;

    public Kardex(String code, String detail, int quantity, double unitCost, double totalCost, String type){
        this.code = code;
        this.date = LocalDate.now();
        this.detail = detail;
        this.quantity = quantity;
        this.unitCost = unitCost;
        this.totalCost = totalCost;
        this.type = type;
    }

    public Kardex(){
        date = LocalDate.now();

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
    
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }

}