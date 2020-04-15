package com.panda.bambu.service.inventory;

import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.inventory.Output;
import com.panda.bambu.model.inventory.OutputRepository;
import com.panda.bambu.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutputService {


     @Autowired
     private OutputRepository outputRepository;

     @Autowired
	 private ArticleService articleService;

     public Output findById(Long id){
         return outputRepository.findById(id).get();
     }

     public Output findByCode(String code){
         return outputRepository.findByCode(code);  
     }

     public List<Output> findAll(){
         return outputRepository.findAll();
     }

     public void create(String code, String detail, int quantity, double unitCost, double totalCost, Article article){
          
        if(!code.isEmpty() && quantity >= 0 && unitCost > 0.0 && totalCost > 0.0 && article != null ){
            Output newOutput = new Output(code, detail, quantity, unitCost, totalCost);
            newOutput.setArticle(article);
            outputRepository.save(newOutput);
        }

     }

    public void create(String code, String detail, int quantity, double unitCost, double totalCost, Long articleId){
            
        Article article = articleService.findById(articleId);
          
        if(!code.isEmpty() && quantity >= 0 && unitCost > 0.0 && totalCost > 0.0 && article != null ){
            Output newOutput = new Output(code, detail, quantity, unitCost, totalCost);
            newOutput.setArticle(article);
            outputRepository.save(newOutput);
        }

    }

    public void create(String code, String detail, int quantity, double unitCost, double totalCost, String articleCode){
            
        Article article = articleService.findByCode(articleCode);
      
        if(!code.isEmpty() && quantity >= 0 && unitCost > 0.0 && totalCost > 0.0 && article != null ){
            Output newOutput = new Output(code, detail, quantity, unitCost, totalCost);
            newOutput.setArticle(article);
            outputRepository.save(newOutput);
        }

    }

    public void delete(Long id){
        outputRepository.deleteById(id);
    }
    
    public void delete(Output output){
        outputRepository.delete(output);
    }

    public void deleteAll(){
        outputRepository.deleteAll();
    }

}