package com.panda.bambu.service.sale_bill;

import java.util.List;
import java.util.Set;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.sale_bill.ArticleSale;
import com.panda.bambu.model.sale_bill.ArticleSaleRepository;
import com.panda.bambu.model.tax.Tax;
import com.panda.bambu.service.article.ArticleService;
import com.panda.bambu.service.tax.TaxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleSaleService {
    
    @Autowired
    ArticleSaleRepository articleSaleRepository;
    
    @Autowired
    ArticleService articleService;

    @Autowired
    TaxService taxService;
    
    public ArticleSale findById(Long id){
          return articleSaleRepository.findById(id).get();
    }

    public List<ArticleSale> findAll(){
        return articleSaleRepository.findAll();
    }
   
	public boolean isArticleAlreadyPresent(ArticleSale articuloSale) {
		ArticleSale articleFound = articleSaleRepository.findByArticle(articuloSale.getArticle());
		if (articleFound==null) 
			return false;
		return true;
    }
   
    public double calculateTotal(Article article, String code, int quantity, float discount, Tax tax){
          double total = 0.0; 
          if(article != null && !code.isEmpty() && quantity > 0){
             total = article.getUnitCost() * quantity;
             if(discount > 1){
                discount = discount/100;
             }
             if(discount > 0){
                total -= total*discount;
             }
             total += total*taxService.findByName("IVA").getValue();
             if(tax != null){
                total += total*tax.getValue();
             }
             return total; 
           }
        return total;
    }

    public float calculoTotalArticulo(float precio, int cantidad,float descuento,Set<Tax> impuestos){
        if(descuento!=0)
            precio-=precio*descuento;
        for(Tax i : impuestos)
            precio+=precio*i.getValue();
        return precio*cantidad;
    }

    public boolean create(ArticleSale article){

        if(article != null){
           articleSaleRepository.save(article);
           return true;
        }
        return false;
    }

    public ArticleSale create(Article article, int quantity, float discount, float tax,float total) {
        ArticleSale articleNew = new ArticleSale();
        if(articleService.findByCode(article.getCode()) != null){
            articleNew.setArticle(article);
            articleNew.setQuantity(quantity);
            articleNew.setDiscount(discount);
            articleNew.setTax(total);
            articleNew.setTotal(total);
            articleSaleRepository.save(articleNew);
        }

        return articleNew;    
    }
    
    public ArticleSale createArticleSale(String code,int quantity,float descuento, Set<Tax> impuestos,float total) {
        float totalImpuestos=0;
        for(Tax i:impuestos)
            totalImpuestos+=i.getValue();
        ArticleSale articulo_new=new ArticleSale();
        Article articulo=articleService.findByCode(code);
        articulo_new.setArticle(articulo);
        articulo_new.setQuantity(quantity);
        articulo_new.setDiscount(descuento);
        articulo_new.setTax(totalImpuestos);
        articulo_new.setTotal(total);
        articleSaleRepository.save(articulo_new);
        return articulo_new;    
    }

    public boolean delete(ArticleSale article){

       if(articleSaleRepository.existsById(article.getId())){
           articleSaleRepository.delete(article);
           return true;
       }

       return false;
   }
}