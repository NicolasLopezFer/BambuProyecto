package com.panda.bambu.model.inventory;

import java.util.HashSet;
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
    private long id;
    
    @OneToOne()
    private Article article;
 
    @OneToMany()
    private Set<Inventory> inventories;
    
    public ArticleInventory(){
        
         inventories = new HashSet<Inventory>(); 
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((article == null) ? 0 : article.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((inventories == null) ? 0 : inventories.hashCode());
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
        ArticleInventory other = (ArticleInventory) obj;
        if (article == null) {
            if (other.article != null)
                return false;
        } else if (!article.equals(other.article))
            return false;
        if (id != other.id)
            return false;
        if (inventories == null) {
            if (other.inventories != null)
                return false;
        } else if (!inventories.equals(other.inventories))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ArticleInventory [article=" + article + ", id=" + id + ", inventories=" + inventories + "]";
    }

    
    
}