package com.panda.bambu.service.recibo_caja;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.panda.bambu.model.recibo_caja.ReciboCaja;
import com.panda.bambu.model.recibo_caja.ReciboCajaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReciboCajaService{
      
    @Autowired
    private ReciboCajaRepository reciboCajaRepository;  
    
    
    public ReciboCaja findById(Long id){
        return reciboCajaRepository.findById(id).get();
    }

    public ReciboCaja findByNumeroComprobante(int numero_comprobante){
        return reciboCajaRepository.findByNumeroComprobante(numero_comprobante);
    }
    
    public List<ReciboCaja> findAll(){
        return reciboCajaRepository.findAll();
    }

    public Boolean create(ReciboCaja reciboCaja) {
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        //String dateString = "14/07/2018";
        //string to date
        LocalDate localDate = LocalDate.parse(reciboCaja.getFecha().toString(),dateTimeFormatter);
        //LocalDate fecha=reciboCaja.getFecha();
        ReciboCaja reciboCaja1 = reciboCajaRepository.findByNumeroComprobante(reciboCaja.getNumeroComprobante());
        if(reciboCaja1==null)
        {
            //reciboCaja.setFecha(localDate);
            reciboCajaRepository.save(reciboCaja);
            return true;
        }
        return false;    
	}

    public Boolean create(int numero_comprobante,String nombreCliente, long identificacion, double suma,String concepto,String descripcion, String elaborador, Boolean aprobado,LocalDate fecha) {
        ReciboCaja article=reciboCajaRepository.findByNumeroComprobante(numero_comprobante);
        if(article==null){
            ReciboCaja newReciboCaja = new ReciboCaja(numero_comprobante,nombreCliente,identificacion, suma, concepto, descripcion, elaborador,aprobado,fecha);
            reciboCajaRepository.save(newReciboCaja);
            return true;
        }
        return false;    
    }
    
	public boolean isArticleAlreadyPresent(ReciboCaja recibo_caja) {
		ReciboCaja recibo_caja_found =reciboCajaRepository.findByNumeroComprobante(recibo_caja.getNumeroComprobante());
		if (recibo_caja_found==null) 
			return false;
		return true;
    }

}