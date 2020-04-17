package com.panda.bambu.service.sale_bill;

import java.time.LocalDate;
import java.util.Set;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.ArticleInventoryRepository;
import com.panda.bambu.model.sale_bill.ArticleSale;
import com.panda.bambu.model.sale_bill.ArticleSaleBill;
import com.panda.bambu.model.sale_bill.ArticleSaleBillRepository;
import com.panda.bambu.model.sale_bill.ArticleSaleRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceSaleBillService {

    
    @Autowired
    ArticleSaleRepository articuloSaleRepository;
    
    @Autowired
    ArticleSaleBillRepository articuloSaleBillRepository;
    
    @Autowired
    ArticleInventoryRepository articuloInventRepository;
    
    public Boolean saveArticleSaleBill(String id_factura, String nombre_cliente, String id_cliente,LocalDate fecha, LocalDate fecha_expiracion,float total,Set<ArticleSale> articulos) {
        ArticleSaleBill articuloSaleBill = articuloSaleBillRepository.findByCode(id_factura);
        if(articuloSaleBill==null){
            for(ArticleSale a:articulos){
                int nVeces=0;
                for(ArticleSale ar: articulos){
                    if (a.getArticle().getCode().equals(ar.getArticle().getCode()) || nVeces==0){
                        nVeces++;
                    }
                    if(nVeces>1)
                        return false;
                }
            }
            //Inventario
            for(ArticleSale a:articulos){
                ArticleInventory articleInventory = articuloInventRepository.findByArticle(a.getArticle());
                if(articleInventory!=null){
                    Article articulo=articleInventory.getArticle();
                    if(articulo.getQuantity() - a.getQuantity()>=0){
                        articulo.setQuantity(articulo.getQuantity() - a.getQuantity());
                        //articuloInventory.getArticle()
                    }
                    else return false;
                }
                else return false;
            }
            ArticleSaleBill articulo_new=new ArticleSaleBill();
            articulo_new.setCode(id_factura);
            articulo_new.setCustomer_name(nombre_cliente);
            articulo_new.setIdCustomer(id_cliente);
            articulo_new.setDate(fecha);
            articulo_new.setExpiration(fecha_expiracion);
            articulo_new.setTotal(total);
            articulo_new.setArticles(articulos);
            articuloSaleBillRepository.save(articulo_new);
            return true;
        }
        return false;    
    }
    
}