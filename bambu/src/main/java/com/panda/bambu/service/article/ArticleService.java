package com.panda.bambu.service.article;

import com.panda.bambu.model.Role;
import com.panda.bambu.model.User;
import com.panda.bambu.model.UserRepository;
import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.article.ArticleRepository;
import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.ArticleInventoryRepository;
import com.panda.bambu.model.inventory.Balance;
import com.panda.bambu.model.inventory.Entry;
import com.panda.bambu.model.inventory.Inventory;
import com.panda.bambu.model.inventory.Output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
    
    @Autowired
    ArticleRepository articuloRepository;
    
    @Autowired
	ArticleInventoryRepository articuloInventoryRepository;

	public Boolean saveArticle(Article articulo) {
        if(!isArticleAlreadyPresent(articulo)){
            //Article articulo_new=new Article();
            if (articulo.getCode().matches(".*[a-z].*")){
                articuloRepository.save(articulo);
                return true;
            }
        }
        return false;    
	}

	public boolean isArticleAlreadyPresent(Article articulo) {
		Article articleFound = articuloRepository.findByCode(articulo.getCode());
		if (articleFound==null) 
			return false;
		return true;
    }

    public Boolean deleteArticle(Article articulo) {
        if(articuloRepository.contains(articulo)){
            articuloRepository.delete(articulo);
            ArticleInventory articuloInventory=articuloInventoryRepository.findByCode(articulo.getCode());
            if(articuloInventory!=null){
                for(Inventory i:articuloInventory.getInventories()){
                    for(Entry e:i.getEntries()){
                        if(e.getArticle().getCode().equals(articulo.getCode())){
                            i.getEntries().remove(e);
                        }
                    }
                    for(Output o:i.getOutputs()){
                        if(o.getArticle().getCode().equals(articulo.getCode())){
                            i.getOutputs().remove(o);
                        }
                    }
                    for(Balance b:i.getBalances()){
                        if(b.getArticle().getCode().equals(articulo.getCode())){
                            i.getBalances().remove(b);
                        }
                    }
                }
                return true;
            }
            
        }
        return false;
    }


    public Boolean modifyArticle(Article articulo_new) {
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

        Article articleFound = articuloRepository.findByCode(articulo_new.getCode());
        if (articleFound!=null){
            articleFound.setName(articulo_new.getName());
            articleFound.setQuantity(articulo_new.getQuantity());
            articleFound.setSalePrice(articulo_new.getSalePrice());
            articleFound.setTotalCost(articulo_new.getTotalCost());
            articleFound.setUnitCost(articulo_new.getUnitCost());
            articuloRepository.save(articleFound);
            return true;
        }   
        return false;
	}
}