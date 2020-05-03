package com.panda.bambu.model.recibo_caja;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity 
public class ReciboCaja {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
    
    @NotNull(message="Numero de comprobante is compulsory")
	@Column(name = "numeroComprobante", unique=true)
    private int numeroComprobante;
    
    @NotNull(message="Fecha is compulsory")
	@Column(name = "fecha")
    private LocalDate fecha;
    
    @NotNull(message="Nombre is compulsory")
	@Column(name = "nombre")
    private String nombreCliente ;
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setnumeroComprobante(int numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
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

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    @Override
    public String toString() {
        return "ReciboCaja [aprobado=" + aprobado + ", concepto=" + concepto + ", descripcion=" + descripcion
                + ", elaborador=" + elaborador + ", fecha=" + fecha + ", id=" + id + ", identificacion="
                + identificacion + ", nombreCliente=" + nombreCliente + ", numeroComprobante=" + numeroComprobante
                + ", suma=" + suma + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((aprobado == null) ? 0 : aprobado.hashCode());
        result = prime * result + ((concepto == null) ? 0 : concepto.hashCode());
        result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
        result = prime * result + ((elaborador == null) ? 0 : elaborador.hashCode());
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (int) (identificacion ^ (identificacion >>> 32));
        result = prime * result + ((nombreCliente == null) ? 0 : nombreCliente.hashCode());
        result = prime * result + numeroComprobante;
        long temp;
        temp = Double.doubleToLongBits(suma);
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
        ReciboCaja other = (ReciboCaja) obj;
        if (aprobado == null) {
            if (other.aprobado != null)
                return false;
        } else if (!aprobado.equals(other.aprobado))
            return false;
        if (concepto == null) {
            if (other.concepto != null)
                return false;
        } else if (!concepto.equals(other.concepto))
            return false;
        if (descripcion == null) {
            if (other.descripcion != null)
                return false;
        } else if (!descripcion.equals(other.descripcion))
            return false;
        if (elaborador == null) {
            if (other.elaborador != null)
                return false;
        } else if (!elaborador.equals(other.elaborador))
            return false;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (identificacion != other.identificacion)
            return false;
        if (nombreCliente == null) {
            if (other.nombreCliente != null)
                return false;
        } else if (!nombreCliente.equals(other.nombreCliente))
            return false;
        if (numeroComprobante != other.numeroComprobante)
            return false;
        if (Double.doubleToLongBits(suma) != Double.doubleToLongBits(other.suma))
            return false;
        return true;
    }

    public ReciboCaja() {
    }

	public ReciboCaja(int numeroComprobante, String nombreCliente, long identificacion, double suma,
			String concepto, String descripcion, String elaborador, Boolean aprobado,LocalDate fecha) {
                this.numeroComprobante = numeroComprobante;
		this.fecha = fecha;
		this.nombreCliente = nombreCliente;
		this.identificacion = identificacion;
		this.suma = suma;
		this.concepto = concepto;
		this.descripcion = descripcion;
		this.elaborador = elaborador;
		this.aprobado = aprobado;

	}

    

    
}