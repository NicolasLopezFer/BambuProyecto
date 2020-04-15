package com.panda.bambu.service.inventory;

import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.ArticleInventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleInventoryService {

    @Autowired
    private ArticleInventoryRepository articleIRepository;

    public ArticleInventory findById(Long id){

        return articleIRepository.findById(id).get();
    }
        
    public ArticleInventory findByArticle(Article article){
          
         return articleIRepository.findByArticle(article);
    }
    
    public List<ArticleInventory> findAll(){

        return articleIRepository.findAll();        
    }

    public void create(Article article){
      
      if(article !=null){

           ArticleInventory newArticle = new ArticleInventory();
           newArticle.setArticle(article);    
           articleIRepository.save(newArticle);            
       }
      
    }

    public void delete(Article article){
          
         for (ArticleInventory element: articleIRepository.findAll()){
              if(element.getArticle().equals(article)){
                 articleIRepository.delete(element);
              }
         }
         
    }

    public void delete(Long id){

         ArticleInventory article = findById(id);
         if( article != null){
             articleIRepository.delete(article);
         } 
    }
         
}