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

     public boolean isOutputAlreadyPresent(Output output) {
		Output outputFound = outputRepository.findByCode(output.getCode());
		if (outputFound==null) 
			return false;
		return true;
     }

     public boolean create(String code, String detail, int quantity, double unitCost, double totalCost, Article article){
      
       if(outputRepository.findByCode(code) == null && article != null && articleService.isArticleAlreadyPresent(article)){
           if(!code.isEmpty() && quantity >= 0 && unitCost > 0.0 && totalCost > 0.0 && articleService.isArticleAlreadyPresent(article)){
               Output newOutput = new Output(code, detail, quantity, unitCost, totalCost);
               newOutput.setArticle(article);
               outputRepository.save(newOutput);
               return true;

            }
       }
    
       return false;

     }

     public boolean create(Output output){
                 
        if(output != null && outputRepository.findByCode(output.getCode()) == null && articleService.isArticleAlreadyPresent(output.getArticle())){
            outputRepository.save(output);
            return true;
        }
        
        return false;

    }

    public boolean delete(Long id){

        if(outputRepository.findById(id) != null){
           outputRepository.deleteById(id);
           return true;
        }
        return false;
    }
    
    public boolean delete(Output output){

        if(outputRepository.findById(output.getId()) != null){
           outputRepository.delete(output);
           return true;
        }

        return false;
    }
    
    public boolean save(Output output){
       
        if(output != null && outputRepository.findByCode(output.getCode()) != null){
            outputRepository.save(output);
            return true;
         }

        return false;

    }

    public void deleteAll(){
        outputRepository.deleteAll();
    }

}