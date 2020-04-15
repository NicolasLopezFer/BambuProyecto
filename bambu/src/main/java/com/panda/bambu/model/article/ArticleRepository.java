package com.panda.bambu.model.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    public Article findByCode(String code);

    //public Boolean contains(Article article);
    

    //public Article merge(Article article);
}

