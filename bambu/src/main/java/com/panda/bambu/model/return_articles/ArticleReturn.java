package com.panda.bambu.model.return_articles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.panda.bambu.model.article.Article;

@Entity
public class ArticleReturn {
     
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private int id;
            
    @NotNull(message="Quantity is compulsory")
	@Column(name = "quanity")
    private String quanity;

    @NotNull(message="Motive is compulsory")
    @Column(name = "motive")
    private String detail;

    @ManyToOne()
    private Article article;
    

}