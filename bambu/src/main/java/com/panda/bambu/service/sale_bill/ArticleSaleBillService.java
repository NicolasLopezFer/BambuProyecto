package com.panda.bambu.service.sale_bill;

import java.util.List;

import com.panda.bambu.model.sale_bill.ArticleSaleBill;
import com.panda.bambu.model.sale_bill.ArticleSaleBillRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleSaleBillService {
    

     @Autowired
     ArticleSaleBillRepository articleSaleBillRepository;

     public ArticleSaleBill findById(Long id){   
           return articleSaleBillRepository.findById(id).get();
     }

     public boolean existById(Long id){
           return articleSaleBillRepository.existsById(id);
     }

     public ArticleSaleBill findByCode(String code){   
        return articleSaleBillRepository.findByCode(code);
     }
     
     public List<ArticleSaleBill> findAll(){
         return articleSaleBillRepository.findAll();
     }
  
}