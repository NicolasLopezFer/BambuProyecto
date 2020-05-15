package com.panda.bambu.service.egreso;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.panda.bambu.model.egreso.Egreso;
import com.panda.bambu.model.egreso.EgresoRepository;
import com.panda.bambu.model.metodo_pago.MetodoPago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EgresoService{
      
    @Autowired
    private EgresoRepository egresoRepository;  
    
    
    public Egreso findById(Long id){
        return egresoRepository.findById(id).get();
    }

    public Egreso findByNumeroComprobante(int numero_comprobante){
        return egresoRepository.findByNumeroComprobante(numero_comprobante);
    }
    
    public List<Egreso> findAll(){
        return egresoRepository.findAll();
    }

    public Boolean create(Egreso egreso) {
        
        Egreso egreso1 = egresoRepository.findByNumeroComprobante(egreso.getNumeroComprobante());
        if(egreso1==null)
        {
            egresoRepository.save(egreso);
            return true;
        }
        return false;    
	}

    public Boolean create(int numero_comprobante,String nombrePagado, long identificacion, double suma,String concepto,String descripcion, String elaborador, Boolean aprobado,LocalDate fecha,MetodoPago metodoPago) {
        Egreso egreso=egresoRepository.findByNumeroComprobante(numero_comprobante);
        if(egreso==null){
            Egreso newEgreso = new Egreso(numero_comprobante,nombrePagado,identificacion, suma, concepto, descripcion, elaborador,aprobado,fecha,metodoPago);
            egresoRepository.save(newEgreso);
            return true;
        }
        return false;    
    }
    
	public boolean isEgresoAlreadyPresent(Egreso egreso) {
		Egreso egreso_found =egresoRepository.findByNumeroComprobante(egreso.getNumeroComprobante());
		if (egreso_found==null) 
			return false;
		return true;
    }

    public Boolean modify(Egreso egreso_new) {
        Egreso egresoFound = egresoRepository.findByNumeroComprobante(egreso_new.getNumeroComprobante());
        if (egresoFound!=null){
            egresoFound.setNombrePagado(egreso_new.getNombrePagado());
            egresoFound.setConcepto(egreso_new.getConcepto());
            egresoFound.setFecha(egreso_new.getFecha() );
            egresoFound.setIdentificacion(egreso_new.getIdentificacion());
            egresoFound.setAprobado(egreso_new.getAprobado());
            egresoFound.setDescripcion(egreso_new.getDescripcion());
            egresoFound.setElaborador(egreso_new.getElaborador());
            egresoFound.setSuma(egreso_new.getSuma());
            egresoFound.setMetodoPago(egreso_new.getMetodoPago());
            
            egresoRepository.save(egresoFound);
            return true;
        }   
        return false;
    }

    public Boolean delete(Egreso egreso) {
        if(egresoRepository.existsById(egreso.getId())){
            egresoRepository.delete(egreso);
            return true;
        }
        return false;
    }
    
    public void deleteAllReciboCaja(){
        egresoRepository.deleteAll();
    }



}