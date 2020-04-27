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
    
    public boolean isBalanceAlreadyPresent(Balance balance) {
		Balance balanceFound = balanceRepository.findById(balance.getId()).get();
		if (balanceFound==null) 
			return false;
		return true;
    }

    public boolean create(Balance balance, Article article){
        
        if(articleService.findById(article.getId()) != null){
           balance.setArticle(article);
           balanceRepository.save(balance);
           return true;
        }

        return false;
    }

    public boolean create(int quantity, double totalCost, Article article){
      
        if(article != null){
            if(quantity >= 0 && totalCost >= 0 && articleService.isArticleAlreadyPresent(article)){
                Balance newBalance = new Balance(quantity,totalCost);
                newBalance.setArticle(article);
                balanceRepository.save(newBalance);
                return true;
            }
        }
        return false;

    }

    public boolean create(int quantity, double totalCost, String articleCode){
        
        Article article = articleService.findByCode(articleCode);
        
        if(quantity >= 0 && totalCost >= 0 && article != null){
           Balance newBalance = new Balance(quantity,totalCost);
           newBalance.setArticle(article);
           balanceRepository.save(newBalance);
           return true;
        }

        return false;
    }

    public boolean delete(Long id){

         if (balanceRepository.existsById(id)){
             balanceRepository.deleteById(id); 
             return true;
         }
         return false;
          
    }

    public boolean delete(Balance balance){

        if(balance != null && balanceRepository.findById(balance.getId()) != null){
            balanceRepository.delete(balance);
            return true;
        }

        return false;  
    }

    public void deleteAllBalances(){
         balanceRepository.deleteAll();
    }

}