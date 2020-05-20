package com.panda.bambu.controller;

public class auxServiReport {

    private String code;

    private String nombre;

    private double cantSolicitudes;

    private double total;

    public auxServiReport( ) {

    }
    
    public auxServiReport(String code, String nombre, double cantSolicitudes, double total) {
        this.code = code;
        this.nombre = nombre;
        this.cantSolicitudes = cantSolicitudes;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantSolicitudes() {
        return cantSolicitudes;
    }

    public void setCantSolicitudes(double cantSolicitudes) {
        this.cantSolicitudes = cantSolicitudes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }
     
}