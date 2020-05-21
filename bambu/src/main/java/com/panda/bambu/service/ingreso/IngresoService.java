package com.panda.bambu.service.ingreso;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.panda.bambu.model.ingreso.Ingreso;
import com.panda.bambu.model.ingreso.IngresoRepository;
import com.panda.bambu.model.metodo_pago.MetodoPago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngresoService{
      
    @Autowired
    private IngresoRepository ingresoRepository;  
    
    
    public Ingreso findById(Long id){
        return ingresoRepository.findById(id).get();
    }

    public Ingreso findByNumeroComprobante(int numero_comprobante){
        return ingresoRepository.findByNumeroComprobante(numero_comprobante);
    }
    
    public List<Ingreso> findAll(){
        return ingresoRepository.findAll();
    }

    public Boolean create(Ingreso ingreso) {
        
        Ingreso ingreso1 = ingresoRepository.findByNumeroComprobante(ingreso.getNumeroComprobante());
        if(ingreso1==null)
        {
            ingresoRepository.save(ingreso);
            return true;
        }
        return false;    
	}

    public Boolean create(int numero_comprobante,String nombrePagado, long identificacion, double suma,String concepto,String descripcion, String elaborador, Boolean aprobado,LocalDate fecha,MetodoPago metodoPago) {
        Ingreso ingreso=ingresoRepository.findByNumeroComprobante(numero_comprobante);
        if(ingreso==null){
            Ingreso newIngreso = new Ingreso(numero_comprobante,nombrePagado,identificacion, suma, concepto, descripcion, elaborador,aprobado,fecha,metodoPago);
            ingresoRepository.save(newIngreso);
            return true;
        }
        return false;    
    }
    
	public boolean isIngresoAlreadyPresent(Ingreso ingreso) {
		Ingreso ingreso_found =ingresoRepository.findByNumeroComprobante(ingreso.getNumeroComprobante());
		if (ingreso_found==null) 
			return false;
		return true;
    }

    public Boolean modify(Ingreso ingreso_new) {
        Ingreso ingresoFound = ingresoRepository.findByNumeroComprobante(ingreso_new.getNumeroComprobante());
        if (ingresoFound!=null){
            ingresoFound.setNombrePagado(ingreso_new.getNombrePagado());
            ingresoFound.setConcepto(ingreso_new.getConcepto());
            ingresoFound.setFecha(ingreso_new.getFecha() );
            ingresoFound.setIdentificacion(ingreso_new.getIdentificacion());
            ingresoFound.setAprobado(ingreso_new.getAprobado());
            ingresoFound.setDescripcion(ingreso_new.getDescripcion());
            ingresoFound.setElaborador(ingreso_new.getElaborador());
            ingresoFound.setSuma(ingreso_new.getSuma());
            ingresoFound.setMetodoPago(ingreso_new.getMetodoPago());
            
            ingresoRepository.save(ingresoFound);
            return true;
        }   
        return false;
    }

    public Boolean delete(Ingreso ingreso) {
        if(ingresoRepository.existsById(ingreso.getId())){
            ingresoRepository.delete(ingreso);
            return true;
        }
        return false;
    }
    
    public void deleteAllReciboCaja(){
        ingresoRepository.deleteAll();
    }



}