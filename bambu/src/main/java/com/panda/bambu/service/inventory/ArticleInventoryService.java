package com.panda.bambu.service.inventory;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.article.ArticleRepository;
import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.ArticleInventoryRepository;
import com.panda.bambu.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;

public class ArticleInventoryService {
    
    @Autowired
	ArticleInventoryRepository articuloInventoryRepository;

    @Autowired
	ArticleRepository articuloRepository;

	public Boolean saveArticleInventory(String code) {
        Article articulo=articuloRepository.findByCode(code);
        if(articulo!=null){
            ArticleInventory articuloInventory=new ArticleInventory();
            articuloInventory.setArticle(articulo);
            articuloInventoryRepository.save(articuloInventory);
            return true;
        }
        return false;
        
	}

	public Boolean isArticleAlreadyPresent(ArticleInventory articuloInventory) {
		ArticleInventory articleInventoryFound = articuloInventoryRepository.findByArticle(articuloInventory.getArticle());
		if (articleInventoryFound==null) 
			return false;
		return true;
    }

    public Boolean deleteArticleInventory(ArticleInventory articuloInventory) {
        if(isArticleAlreadyPresent(articuloInventory)){
            articuloInventoryRepository.delete(articuloInventory);
            Article articulo=articuloRepository.findByCode(articuloInventory.getArticle().getCode());
            if (articulo!=null){
                articuloRepository.delete(articulo);
                return true;
            }     
        }
        return false;
        
    }


    public Boolean modifyArticleInventory(ArticleInventory articulo_inventory_new) {
        // ....
        // EntityManager em;
        // ....

        // Get 'item' into 'managed' state
        /*if(!articuloRepository.contains(articulo_new)){
            articulo_new = articuloRepository.merge(articulo_new);
        }
        /*articulo_new.price = articulo_new.get;
        item.quantity = newQuantity;*/
        // You don't even need to call save(), JPA provider/Hibernate will do it automatically

        ArticleInventory articleInventoryFound = articuloInventoryRepository.findByArticle(articulo_inventory_new.getArticle());
        if (articleInventoryFound!=null){
            articleInventoryFound.setArticle(articulo_inventory_new.getArticle());
            articleInventoryFound.setInventories(articulo_inventory_new.getInventories());
            articuloInventoryRepository.save(articleInventoryFound);
            return true;
        }   
        return false;
	}
}