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
        if(articleIRepository.findById(id).get() != null){
            return articleIRepository.findById(id).get();
        }
        return null;
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
                newArticle.setMeasureMethod("Promedio Ponderado");
                newArticle.getInventories().add(inventoryService.create());    
                articleIRepository.save(newArticle);
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
    
    public void modifyMethod(String method){
        
        List<Inventory> inventories = null;
        double unitCost = 0.0;
        if(!method.isEmpty()){
            for (ArticleInventory article : findAll()) {
                 article.setMeasureMethod(method);
                 save(article);
                 inventories = article.getInventories();
                 System.out.println("");
                 System.out.println("");
                 System.out.println("");
                 System.out.println("");
                 System.out.println("SOY YO CAMBIANDO EL METODO" + inventories.size());
                if(!inventories.isEmpty()){
                    System.out.println("SiGO AQUI BABY" + inventories.size());
                    unitCost = inventoryService.modifyMeasureMethodInventory(inventories.get(inventories.size()-1), method);
                    Article articleI = article.getArticle();
                    articleI.setUnitCost(unitCost);
                    articleService.save(articleI);
                }
                
                save(article);
            }
        }
    }

    public boolean addEntry(ArticleInventory articleInventory, Entry entry){
                
        if(entry != null && entry.getQuantity() > 0){
            entry.setArticle(articleInventory.getArticle());

            Article article = articleInventory.getArticle();
            article.setQuantity(entry.getQuantity()+article.getQuantity());
            article.setTotalCost(article.getQuantity()*article.getUnitCost());
            articleService.save(article);
            
            entry.setTotalCost(entry.getQuantity()*entry.getUnitCost());

            if(articleIRepository.existsById(articleInventory.getId()) == true && entryService.createEntry(entry) == true){
               if(articleInventory.getInventories().isEmpty()){
                    articleInventory.getInventories().add(inventoryService.create());
                }
                
                double unitCost = inventoryService.addEntryInventory(articleInventory.getInventories().get(articleInventory.getInventories().size()-1), entry, articleInventory.getMeasureMethod());
                articleInventory.getArticle().setUnitCost(unitCost);
                articleService.save(articleInventory.getArticle());
                save(articleInventory);
             return true;
            }
        }
        return false;
    }

    
    public boolean addOuput(ArticleInventory articleInventory, Output output){
        
        System.out.println("Salida soyyy id:" + articleInventory.getId());
        Article article = articleInventory.getArticle();
        if(output != null && output.getQuantity() > 0 && output.getQuantity() < article.getQuantity()){
            output.setArticle(articleInventory.getArticle());
         
            article.setQuantity(article.getQuantity()-output.getQuantity());
            article.setTotalCost(article.getQuantity()*article.getUnitCost());
            articleService.save(article);

            if(articleIRepository.existsById(articleInventory.getId()) == true && outputService.create(output) == true){
                if(articleInventory.getInventories().isEmpty()){
                    articleInventory.getInventories().add(inventoryService.create());
                    articleIRepository.save(articleInventory);
                }
                output.setArticle(articleInventory.getArticle());
                outputService.create(output);   
            
                articleInventory.getArticle().setUnitCost(inventoryService.addOutputInventory(articleInventory.getInventories().get(articleInventory.getInventories().size()-1), output,articleInventory.getMeasureMethod()));
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