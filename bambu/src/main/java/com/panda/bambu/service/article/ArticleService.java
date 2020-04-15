package com.panda.bambu.service.article;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.article.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
      
    @Autowired
    private ArticleRepository articleRepository;   
    
    public Article findById(Long id){
        return articleRepository.findById(id).get();
    }

    public Article findByCode(String code){
        return articleRepository.findByCode(code);
    }
   

}