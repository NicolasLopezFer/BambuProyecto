package com.panda.bambu.model.sale_bill;

import com.panda.bambu.model.article.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleSaleRepository extends JpaRepository<ArticleSale, Long> {
    
    ArticleSale findByArticle(Article articulo);
    ArticleSale findById(int id);
}
