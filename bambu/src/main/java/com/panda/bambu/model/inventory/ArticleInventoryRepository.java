package com.panda.bambu.model.inventory;

import com.panda.bambu.model.article.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleInventoryRepository extends JpaRepository<ArticleInventory, Long> {

    public ArticleInventory findById(int id);

    public ArticleInventory findByArticle(Article articulo);

    //public Boolean contains(ArticleInventory article);

    //public ArticleInventory merge(ArticleInventory article);
}
