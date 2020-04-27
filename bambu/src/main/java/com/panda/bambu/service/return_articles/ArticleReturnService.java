package com.panda.bambu.service.return_articles;

import java.util.List;

import com.panda.bambu.model.return_articles.ArticleReturn;
import com.panda.bambu.model.return_articles.ArticleReturnRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArticleReturnService {

    @Autowired
    ArticleReturnRepository articleReturnRepository;

    public ArticleReturn findById(Long id){
         return articleReturnRepository.findById(id).get();

    }

    public Boolean existById(Long id){
         return articleReturnRepository.existsById(id);

    }
    
    public List<ArticleReturn> findAll(){
          return articleReturnRepository.findAll();
    }

    public boolean create(ArticleReturn article){

          if(article != null){
             articleReturnRepository.save(article);
             return true;
          }
        return false;
    }
    
    public boolean modify(ArticleReturn article){

        if(existById(article.getId()) == true){
           articleReturnRepository.save(article);
           return true;
        }
      return false;
    }
 
    public boolean delete(ArticleReturn article){
          
         if(existById(article.getId()) == true){
            articleReturnRepository.delete(article);
            return true;
         }

         return false;
    }

    public boolean deleteAll(){
          articleReturnRepository.deleteAll();
          return true;
    }
     
    public boolean save(ArticleReturn article){

          if(existById(article.getId()) == true){
             articleReturnRepository.save(article);
             return true;
          }
        return false;
    }   

}