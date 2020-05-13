package com.panda.bambu.model.service_famiempresa;

import com.panda.bambu.model.article.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceArticleRepository extends JpaRepository<ServiceArticle, Long>{
    ServiceArticle findByArticle(Article article);
}