package com.panda.bambu.service.tax;

import java.util.List;

import com.panda.bambu.model.tax.Tax;
import com.panda.bambu.model.tax.TaxRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    @Autowired
    private TaxRepository taxRepository;

    public Tax findById(Long id){
        return taxRepository.findById(id).get();
    }

    public boolean existById(Long id){
        return taxRepository.existsById(id);
    }

    public Tax findByName(String name){
        return taxRepository.findByName(name);
    }

    public List<Tax> findAll(){
        return taxRepository.findAll();
    }

    public boolean delete(Tax tax){
         if(findByName(tax.getName()) != null){
            taxRepository.delete(tax);
            return true;
         }
       return false;
    }

}