package com.panda.bambu.model.inventory;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.panda.bambu.model.article.Article;

@Entity
public class ArticleInventory {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
    
    @OneToOne()
    private Article article;
 
    @OneToMany()
    private Set<Inventory> inventories;
    
}