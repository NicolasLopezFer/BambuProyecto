package com.panda.bambu.model.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleInventoryRepository extends JpaRepository<ArticleInventory, Long> {
    public ArticleInventory findByCode(String code);

    public Boolean contains(ArticleInventory article);

    public ArticleInventory merge(ArticleInventory article);
}
