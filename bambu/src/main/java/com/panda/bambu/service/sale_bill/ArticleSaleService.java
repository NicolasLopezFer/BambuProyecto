package com.panda.bambu.service.sale_bill;

import java.util.Set;
import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.article.ArticleRepository;
import com.panda.bambu.model.sale_bill.ArticleSale;
import com.panda.bambu.model.sale_bill.ArticleSaleBill;
import com.panda.bambu.model.sale_bill.ArticleSaleBillRepository;
import com.panda.bambu.model.sale_bill.ArticleSaleRepository;
import com.panda.bambu.model.tax.Tax;

import org.springframework.beans.factory.annotation.Autowired;


public class ArticleSaleService {
    
    @Autowired
    ArticleSaleRepository articuloSaleRepository;
    
    @Autowired
    ArticleSaleBillRepository articuloSaleBillRepository;
    
    @Autowired
    ArticleRepository articuloRepository;

    public float calculoTotalArticulo(float precio, int cantidad,float descuento,Set<Tax> impuestos){
        if(descuento!=0)
            precio-=precio*descuento;
        for(Tax i : impuestos)
            precio+=precio*i.getValue();
        return precio*cantidad;
    }
    

	public boolean isArticleAlreadyPresent(ArticleSale articuloSale) {
		ArticleSale articleFound = articuloSaleRepository.findByArticle(articuloSale.getArticle());
		if (articleFound==null) 
			return false;
		return true;
    }

    public ArticleSale createArticle(String code,int quantity,float descuento, Set<Tax> impuestos,float total) {
        float totalImpuestos=0;
        for(Tax i:impuestos)
            totalImpuestos+=i.getValue();
        ArticleSale articulo_new=new ArticleSale();
        Article articulo=articuloRepository.findByCode(code);
        articulo_new.setArticle(articulo);
        articulo_new.setQuantity(quantity);
        articulo_new.setDiscount(descuento);
        articulo_new.setTax(totalImpuestos);
        articulo_new.setTotal(total);
        articuloSaleRepository.save(articulo_new);
        return articulo_new;    
	}
}