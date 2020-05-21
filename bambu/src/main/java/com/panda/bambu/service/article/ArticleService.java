package com.panda.bambu.service.article;

import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.article.ArticleRepository;
import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.Balance;
import com.panda.bambu.model.inventory.Entry;
import com.panda.bambu.model.inventory.Inventory;
import com.panda.bambu.model.inventory.Output;
import com.panda.bambu.service.inventory.ArticleInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService{
      
    @Autowired
    private ArticleRepository articleRepository;  
    
    @Autowired
    ArticleInventoryService articleInventoryService;
    
    
    public Article findById(Long id){
        return articleRepository.findById(id).get();
    }

    public Article findByCode(String code){
        return articleRepository.findByCode(code);
    }

    public Article findByPosition(int index){
        List<Article> articles = findAll();
        Article article = articles.get(index);
        
        if(!articles.isEmpty() && article != null){
           return article;
        }

        return article;
    }
    
    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    public Boolean create(String code, String name, double salePrice) {
        Article article=articleRepository.findByCode(code);
        if(article==null){
            if (code.matches(".*[a-z].*")){
                Article newArticle = new Article(code,name,salePrice);
                articleRepository.save(newArticle);
                articleInventoryService.create(newArticle);
                return true;
            }
        }
        return false;    
    }
    
    public Article getLastArticleCreate(){
           
          List<Article> articles = findAll();
          return articles.get(articles.size()-1); 
    }

    public Boolean create(Article article) {
        Article articleF=articleRepository.findByCode(article.getCode());
        if(articleF==null){
            //if (article.getCode().matches(".*[a-z].*")){
                articleRepository.save(article);
                articleInventoryService.create(getLastArticleCreate());
                return true;
            //}
        }
        return false;    
	}
    
	public boolean isArticleAlreadyPresent(Article article) {
		Article articleFound = articleRepository.findByCode(article.getCode());
		if (articleFound==null) 
			return false;
		return true;
    }
       
    public Boolean modify(Article articulo_new) {
 
        Article articleFound = articleRepository.findByCode(articulo_new.getCode());
        if (articleFound!=null){
            articleFound.setName(articulo_new.getName());
            articleFound.setQuantity(articulo_new.getQuantity());
            articleFound.setSalePrice(articulo_new.getSalePrice());
            articleFound.setTotalCost(articulo_new.getTotalCost());
            articleFound.setUnitCost(articulo_new.getUnitCost());
            articleRepository.save(articleFound);
            return true;
        }   
        return false;
    }

    public Boolean modify(String code, String name, int quantity, double unitCost, double totalCost, double salePrice) {
        
        Article articleFound = articleRepository.findByCode(code);
        if(articleFound != null){
            if(!code.isEmpty() && !name.isEmpty() && quantity > 0 && unitCost > 0.0 && totalCost > 0.0 && salePrice > 0.0){
                articleFound.setName(name);
                articleFound.setQuantity(quantity);
                articleFound.setSalePrice(unitCost);
                articleFound.setTotalCost(totalCost);
                articleFound.setUnitCost(salePrice);
                articleRepository.save(articleFound);
                return true;
            }
        }

        return false;
    }

    public Boolean delete(Article article) {
        if(articleRepository.existsById(article.getId())){
    
            ArticleInventory articuloInventory=articleInventoryService.findById(article.getId());
            if(articuloInventory!=null){
                if(articuloInventory.getInventories() != null && !articuloInventory.getInventories().isEmpty()){
                    for(Inventory i:articuloInventory.getInventories()){
                        for(Entry e:i.getEntries()){
                            if(e.getArticle().getCode().equals(article.getCode())){
                                i.getEntries().remove(e);
                            }
                        }
                        for(Output o:i.getOutputs()){
                            if(o.getArticle().getCode().equals(article.getCode())){
                                i.getOutputs().remove(o);
                            }
                        }
                        for(Balance b:i.getBalances()){
                            if(b.getArticle().getCode().equals(article.getCode())){
                                i.getBalances().remove(b);
                            }
                        }
                   }
               
                }
                articleInventoryService.delete(articuloInventory);
                System.out.println("HOLAAA BORRANDO ANDO ARTICULO");
                articleRepository.delete(article);
                return true;
            }
            
        }
        return false;
    }
    
    public Boolean save(Article article) {
        if(!isArticleAlreadyPresent(article)){
            if (article.getCode().matches(".*[a-z].*")){
                articleRepository.save(article);
                return true;
            }
        }
        return false;    
    }

    public void deleteAllArticles(){
        articleRepository.deleteAll();
    }


}