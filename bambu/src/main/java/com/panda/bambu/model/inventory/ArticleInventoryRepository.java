package com.panda.bambu.model.inventory;

import com.panda.bambu.model.article.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleInventoryRepository extends JpaRepository<ArticleInventory, Long> {

       public ArticleInventory findByArticle(Article article);
       
}
