package com.panda.bambu.service.inventory;

import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.ArticleInventoryRepository;
import com.panda.bambu.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleInventoryService {

    @Autowired
    private ArticleInventoryRepository articleIRepository;

    @Autowired
	ArticleService articleService;

    public ArticleInventory findById(Long id){
        return articleIRepository.findById(id).get();
    }
        
    public ArticleInventory findByCode(Article article){
         return articleIRepository.findByCode(article.getCode());
    }
    
    public List<ArticleInventory> findAll(){
        return articleIRepository.findAll();        
    }

    public Boolean create(Article article) {
       
        if(articleIRepository.findByCode(article.getCode()) != null){
            if(article !=null){
                ArticleInventory newArticle = new ArticleInventory();
                newArticle.setCode(article.getCode());
                newArticle.setArticle(article);    
                articleIRepository.save(newArticle);
                return true;            
            }
        }
        return false;    
	}
    
    public boolean create(Long articleId){
        
        Article article = articleService.findById(articleId);

        if(articleIRepository.findByCode(article.getCode()) != null){
            if(article !=null){
                ArticleInventory newArticle = new ArticleInventory();
                newArticle.setCode(article.getCode());
                newArticle.setArticle(article);    
                articleIRepository.save(newArticle);
                return true;
            }
        }
        return false;

    }

    public boolean create(String codeId){
        
        Article article = articleService.findByCode(codeId);
        if(articleIRepository.findByCode(article.getCode()) != null){
            if(article !=null){

                ArticleInventory newArticle = new ArticleInventory();
                newArticle.setCode(article.getCode());
                newArticle.setArticle(article);    
                articleIRepository.save(newArticle);            
            }
            return true;
        }
        return false;

    }
    
    public boolean delete(String code){
          
         ArticleInventory article = articleIRepository.findByCode(code);
         if( article != null){
             articleIRepository.delete(article);
             return true;
         } 

         return false;
   }

    public boolean delete(Article article){
          
         for (ArticleInventory element: articleIRepository.findAll()){
              if(element.getArticle().equals(article)){
                 articleIRepository.delete(element);
                 return true;
              }
         }
         return false;
    }

    public boolean delete(Long id){

         ArticleInventory article = findById(id);
         if( article != null){
             articleIRepository.delete(article);
             return true;
         } 
         return false;
    }

    public void deleteAll(){
        articleIRepository.deleteAll();
    }
}