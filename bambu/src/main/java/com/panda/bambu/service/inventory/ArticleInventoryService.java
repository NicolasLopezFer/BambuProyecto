package com.panda.bambu.service.inventory;

import java.util.List;
import java.util.TimerTask;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.ArticleInventoryRepository;
import com.panda.bambu.model.inventory.Balance;
import com.panda.bambu.model.inventory.Entry;
import com.panda.bambu.model.inventory.Inventory;
import com.panda.bambu.model.inventory.Output;
import com.panda.bambu.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleInventoryService extends TimerTask{

    @Autowired
    private ArticleInventoryRepository articleIRepository;

    @Autowired
    ArticleService articleService;
    
    @Autowired
    InventoryService inventoryService;
    
    @Autowired
    EntryService entryService;
    
    @Autowired
	OutputService outputService;

    public ArticleInventory findById(Long id){
        return articleIRepository.findById(id).get();
    }
        
    public ArticleInventory findByArticle(Article article){
         return articleIRepository.findByArticle(article);
    }
    
    public List<ArticleInventory> findAll(){
        return articleIRepository.findAll();        
    }

    public Boolean create(Article article) {
       
        if(articleService.isArticleAlreadyPresent(article) == true){
            if(article !=null){
                ArticleInventory newArticle = new ArticleInventory();
                newArticle.setArticle(article);    
                articleIRepository.save(newArticle);
                newArticle.getInventories().add(inventoryService.create());
                return true;            
            }
        }
        return false;    
	}
    
    public boolean create(Long articleId){
        
        Article article = articleService.findById(articleId);

        if(article !=null){
            ArticleInventory newArticle = new ArticleInventory();
            newArticle.setArticle(article);    
            articleIRepository.save(newArticle);
            return true;
        }
        
        return false;

    }
    
    public boolean delete(ArticleInventory article){

        if( article != null && articleIRepository.existsById(article.getId())){
            articleIRepository.delete(article);
            return true;
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

    public boolean save(ArticleInventory article){

        ArticleInventory articleF = articleIRepository.findByArticle(article.getArticle());
        articleF = articleIRepository.findById(article.getId()).get();
        if( articleF != null || articleIRepository.existsById(article.getId()) == true){
            articleIRepository.save(article);
            return true;
        } 
        return false;
    }

    public void deleteAll(){
        articleIRepository.deleteAll();
    }

    public boolean addEntry(ArticleInventory articleInventory, Entry entry){
                
        if(entry != null && entry.getQuantity() > 0){
            entry.setArticle(articleInventory.getArticle());

            Article article = articleInventory.getArticle();
            article.setQuantity(entry.getQuantity()+article.getQuantity());
            articleService.save(article);

            if(articleIRepository.existsById(articleInventory.getId()) == true && !entryService.createEntry(entry)){
                if(articleInventory.getInventories().isEmpty()){
                   articleInventory.getInventories().add(inventoryService.create());
                }
                articleInventory.getArticle().setUnitCost(inventoryService.addEntryInventory(articleInventory.getInventories().get(articleInventory.getInventories().size()-1), entry));
                articleService.save(articleInventory.getArticle());
             return true;
            }
        }
        return false;
    }

    
    public boolean addOuput(ArticleInventory articleInventory, Output output){
        
        Article article = articleInventory.getArticle();
        if(output != null && output.getQuantity() > 0 && output.getQuantity()<article.getQuantity()){
            output.setArticle(articleInventory.getArticle());
            output.setUnitCost(articleInventory.getArticle().getUnitCost());
            output.setTotalCost(output.getQuantity()*output.getUnitCost());

            article.setQuantity(article.getQuantity()-output.getQuantity());
            articleService.save(article);

            if(articleIRepository.existsById(articleInventory.getId()) == true && !outputService.create(output)){
                if(articleInventory.getInventories().isEmpty()){
                    articleInventory.getInventories().add(inventoryService.create());
                    articleIRepository.save(articleInventory);
                }
                output.setArticle(articleInventory.getArticle());
                outputService.create(output);   
            
                articleInventory.getArticle().setUnitCost(inventoryService.addOutputInventory(articleInventory.getInventories().get(articleInventory.getInventories().size()-1), output));
                articleService.save(articleInventory.getArticle());
                return true;
            }
        }

        return false;
    }

    @Override
    public void run() {
          createInventory();
    }

      public void createInventory(){

             for(ArticleInventory article: articleIRepository.findAll()){
                 Inventory inventory = inventoryService.create();
                 List<Inventory> inventories = article.getInventories();
                 List<Balance> balances = inventories.get(inventories.size()-1).getBalances();
                 Balance balance = balances.get(balances.size()-1);
                 
                 if(balance.getQuantity() == 0 && balance.getTotalCost() == 0){
                    if(!inventoryService.addBalanceInventory(inventory,balance,article.getArticle())){
                        article.getInventories().add(inventory);
                        articleIRepository.save(article);
                    }
                  
                 }
                 else{
                    article.getInventories().add(inventory);
                    articleIRepository.save(article);
                 }
                 
             }
             
      }
}