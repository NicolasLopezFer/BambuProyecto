package com.panda.bambu.model.egreso;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.metodo_pago.MetodoPago;

import org.springframework.format.annotation.DateTimeFormat;


@Entity 
public class Egreso {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
    
    @NotNull(message="Numero de comprobante is compulsory")
	@Column(name = "numeroComprobante", unique=true)
    private int numeroComprobante;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message="Fecha is compulsory")
	@Column(name = "fecha")
    private LocalDate fecha;
    
    @NotNull(message="Nombre Pagado is compulsory")
	@Column(name = "nombrePagado")
    private String nombrePagado ;
    
    @NotNull(message="Identificacion is compulsory")
	@Column(name = "identificacion")
    private long identificacion;

    @NotNull(message="Suma is compulsory")
	@Column(name = "suma")
    private double suma;

    @NotNull(message="Concepto is compulsory")
	@Column(name = "concepto")
    private String concepto;

    @NotNull(message="Descripcion is compulsory")
	@Column(name = "descripcion")
    private String descripcion;

    @NotNull(message="Elaborador is compulsory")
	@Column(name = "elaborador")
    private String elaborador;

    @NotNull(message="Aprobado is compulsory")
	@Column(name = "aprobado")
    private Boolean aprobado;

    @NotNull(message="Metodo Pago is compulsory")
	@Column(name = "metodoPago")
    private MetodoPago metodoPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(int numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombrePagado() {
        return nombrePagado;
    }

    public void setNombrePagado(String nombrePagado) {
        this.nombrePagado = nombrePagado;
    }

    public long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(long identificacion) {
        this.identificacion = identificacion;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getElaborador() {
        return elaborador;
    }

    public void setElaborador(String elaborador) {
        this.elaborador = elaborador;
    }

    public Egreso() {
    }

	public Egreso(int numeroComprobante, String nombrePagado, long identificacion, double suma,
			String concepto, String descripcion, String elaborador, Boolean aprobado,LocalDate fecha, MetodoPago metodoPago) {
        this.numeroComprobante = numeroComprobante;
		this.fecha = fecha;
		this.nombrePagado = nombrePagado;
		this.identificacion = identificacion;
		this.suma = suma;
		this.concepto = concepto;
		this.descripcion = descripcion;
		this.elaborador = elaborador;
        this.aprobado = aprobado;
        this.metodoPago = metodoPago;

	}

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    

    
}