package com.panda.bambu.model.return_articles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnArticlesRepository extends JpaRepository<ReturnArticles, Long> {

}