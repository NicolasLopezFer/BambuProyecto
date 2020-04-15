package com.panda.bambu.service.inventory;

import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.inventory.Balance;
import com.panda.bambu.model.inventory.BalanceRepository;
import com.panda.bambu.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

     
    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
	private ArticleService articleService;

    public Balance findById(Long id){
        return balanceRepository.findById(id).get();
    }

    public List<Balance> findAll(){
        return balanceRepository.findAll();
    }

    public void create(int quantity, double totalCost, Article article){
          
        if(quantity >= 0 && totalCost >= 0 && article != null){
             Balance newBalance = new Balance(quantity,totalCost);
             newBalance.setArticle(article);
             balanceRepository.save(newBalance);
        }

    }

    public void create(int quantity, double totalCost, Long articleId){
        
        Article article = articleService.findById(articleId);

        if(quantity >= 0 && totalCost >= 0 && article != null){
           Balance newBalance = new Balance(quantity,totalCost);
           newBalance.setArticle(article);
           balanceRepository.save(newBalance);
        }
    }

    public void create(int quantity, double totalCost, String articleCode){
        
        Article article = articleService.findByCode(articleCode);
        
        if(quantity >= 0 && totalCost >= 0 && article != null){
           Balance newBalance = new Balance(quantity,totalCost);
           newBalance.setArticle(article);
           balanceRepository.save(newBalance);
        }
    }

    public void delete(Long id){
         balanceRepository.deleteById(id);  
    }

    public void delete(Balance balance){
         balanceRepository.delete(balance);
    }

    public void deleteAll(){
         balanceRepository.deleteAll();
    }

}