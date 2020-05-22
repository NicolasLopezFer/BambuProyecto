package com.panda.bambu.service.return_articles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.Entry;
import com.panda.bambu.model.inventory.Output;
import com.panda.bambu.model.return_articles.ArticleReturn;
import com.panda.bambu.model.return_articles.ReturnArticles;
import com.panda.bambu.model.return_articles.ReturnArticlesRepository;
import com.panda.bambu.service.inventory.ArticleInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReturnArticlesService {

       
    @Autowired
    ReturnArticlesRepository returnArticlesRepository;
    
    @Autowired
    ArticleReturnService articleReturnService;

    @Autowired
    ArticleInventoryService articleInventoryService;
    

    public List<ReturnArticles> findAll(){
        return returnArticlesRepository.findAll();

   }

    public ReturnArticles findById(Long id){
         return returnArticlesRepository.findById(id).get();

    }
    
    public boolean existById(Long id){
        return returnArticlesRepository.existsById(id);

    }

    public List<ReturnArticles> findByRange(LocalDate dateInicial, LocalDate dateFinal){
        List<ReturnArticles> returns = new ArrayList<ReturnArticles>();
        if(!findAll().isEmpty()){
        for (ReturnArticles returnArticles : findAll()) {
            if(returnArticles.getDate() != null){
              if(returnArticles.getDate().isAfter(dateInicial) && returnArticles.getDate().isBefore(dateFinal)){
                 returns.add(returnArticles);
              }
            }
        }
    }
        return returns;
    }
    
    public boolean addInventoryEntry(ArticleReturn article){
          
          Entry entry = new Entry();
          entry.setCode(article.getArticle().getCode());
          entry.setDetail("Devolución");
          entry.setQuantity(article.getQuantity());
          entry.setUnitCost(article.getArticle().getUnitCost());
          entry.setTotalCost(article.getQuantity()*article.getArticle().getUnitCost());
          entry.setArticle(article.getArticle());
          
          ArticleInventory articleInventory = null;
          for (ArticleInventory a : articleInventoryService.findAll()) {
               if(a.getArticle().getId() == article.getArticle().getId()){
                  articleInventory = a;
               }  
          } 

          if(articleInventory != null && articleInventoryService.addEntry(articleInventory,entry) == true){
             return true;
         }

          return false;
    }

    public boolean addInventoryOutput(ArticleReturn article){
          
        Output output = new Output();
        output.setCode(article.getArticle().getCode());
        output.setDetail("Devolución");
        output.setQuantity(article.getQuantity());
        output.setUnitCost(article.getArticle().getUnitCost());
        output.setTotalCost(article.getQuantity()*article.getArticle().getUnitCost());
        output.setArticle(article.getArticle());
        
        ArticleInventory articleInventory = null;
        for (ArticleInventory a : articleInventoryService.findAll()) {
             if(a.getArticle().getId() == article.getArticle().getId()){
                articleInventory = a;
             }  
        } 

        if(articleInventory != null && !articleInventoryService.addOuput(articleInventory,output)){
            return true;
        }

        return false;
    }

    public boolean create(ReturnArticles returnArticles){
           if(returnArticles != null && !returnArticles.getArticles().isEmpty() ){
              if(returnArticles.getSaleReturn() == true){
                 for ( ArticleReturn article: returnArticles.getArticles()){
                       articleReturnService.create(article);
                           
                  }
               }
               else if(returnArticles.getBuyReturn() == true){
                          for ( ArticleReturn article: returnArticles.getArticles() ){
                                 articleReturnService.create(article);
                             
                        }

               }
               returnArticlesRepository.save(returnArticles);
               return true;
           }
           
           return false;     
    }

    public boolean save(ReturnArticles returnArticles){
         
        if(returnArticles != null){
           returnArticlesRepository.save(returnArticles);
           return true;
        }

          return false;
    }
    
    public boolean delete(ReturnArticles returnArticles){

        if(existById(returnArticles.getId()) == true){
           returnArticlesRepository.delete(returnArticles);
           return true;
        }

        return false;
    }

    public boolean deleteAll(){
         returnArticlesRepository.deleteAll();
         return true;
    }

}