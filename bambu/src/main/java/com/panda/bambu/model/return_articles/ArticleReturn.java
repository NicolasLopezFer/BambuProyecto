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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuanity() {
        return quanity;
    }

    public void setQuanity(String quanity) {
        this.quanity = quanity;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((article == null) ? 0 : article.hashCode());
        result = prime * result + ((detail == null) ? 0 : detail.hashCode());
        result = prime * result + id;
        result = prime * result + ((quanity == null) ? 0 : quanity.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArticleReturn other = (ArticleReturn) obj;
        if (article == null) {
            if (other.article != null)
                return false;
        } else if (!article.equals(other.article))
            return false;
        if (detail == null) {
            if (other.detail != null)
                return false;
        } else if (!detail.equals(other.detail))
            return false;
        if (id != other.id)
            return false;
        if (quanity == null) {
            if (other.quanity != null)
                return false;
        } else if (!quanity.equals(other.quanity))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ArticleReturn [article=" + article + ", detail=" + detail + ", id=" + id + ", quanity=" + quanity + "]";
    }
    
    
}