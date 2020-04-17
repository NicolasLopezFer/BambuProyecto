package com.panda.bambu.service.sale_bill;

import java.util.List;

import com.panda.bambu.model.inventory.Output;
import com.panda.bambu.model.sale_bill.ArticleSale;
import com.panda.bambu.model.sale_bill.ArticleSaleBill;
import com.panda.bambu.model.sale_bill.ArticleSaleBillRepository;
import com.panda.bambu.service.inventory.ArticleInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleSaleBillService {
    
     @Autowired
     private ArticleSaleBillRepository articleSaleBillRepository;
     
     @Autowired
     private ArticleSaleService articleSaleService;

     @Autowired
     private ArticleInventoryService articleInventoryService;


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
     
     public boolean isRepeatCode(ArticleSaleBill articleBill, ArticleSale articleSale){
         
             for(ArticleSale article: articleBill.getArticles()){
                 if(article.equals(articleSale)){
                    return false;
                 }
              }
           return true;
     }

     public boolean create(ArticleSaleBill articleBill){
           
           if(articleSaleBillRepository.findByCode(articleBill.getCode()) == null){
              if(!articleBill.getArticles().isEmpty()){
                  for(ArticleSale article: articleBill.getArticles()){
                      if(article == null || isRepeatCode(articleBill, article)){
                         return false;   
                      }

                      if(articleSaleService.create(article) == false){
                         return false;
                      }

                      articleSaleService.create(article);
                      Output output = new Output();
                      output.setCode(articleBill.getCode());
                      output.setDetail("Venta");
                      output.setQuantity(article.getQuantity());
                      articleInventoryService.addOuput(articleInventoryService.findByArticle(article.getArticle()), output);
                  }

                   articleSaleBillRepository.save(articleBill);
               }   
               
           }
        
          return false;
     }
   
}