package com.panda.bambu.service.service_famiempresa;

import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.service_famiempresa.ServiceArticle;
import com.panda.bambu.model.service_famiempresa.ServiceArticleRepository;
import com.panda.bambu.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceArticleService {

    @Autowired
    private ServiceArticleRepository serviceArticleRepository; 
    
    @Autowired
    private ArticleService articleService;


    public ServiceArticle findById(Long id){
        return serviceArticleRepository.findById(id).get();
    }

    public List<ServiceArticle> findAll()
    {
        return serviceArticleRepository.findAll();
    }

    public boolean create(ServiceArticle serviceArticle){

        if(serviceArticle != null){
            serviceArticleRepository.save(serviceArticle);
            return true;
        }
        return false;
    }

    public Article getArticle(ServiceArticle serviceArticle)
    {
        return articleService.findByCode(serviceArticle.getArticle().getCode());
    }

    public ServiceArticle create(Article article, int quantity) {
        ServiceArticle serviceArticleNew = new ServiceArticle();
        if(articleService.findByCode(article.getCode()) != null){
            serviceArticleNew.setArticle(article);;
            serviceArticleNew.setQuantity(quantity);
            serviceArticleRepository.save(serviceArticleNew);
        }

        return serviceArticleNew;    
    }

    public boolean delete(ServiceArticle serviceArticle){

        if(serviceArticleRepository.existsById(serviceArticle.getId())){
            serviceArticleRepository.delete(serviceArticle);
            return true;
        }
 
        return false;
    }

	public boolean modify(ServiceArticle serviceArticle) {

        ServiceArticle sa = findById(serviceArticle.getId());
        if(sa != null){
            sa.setQuantity(serviceArticle.getQuantity());
            serviceArticleRepository.save(sa);
            return true;
        }
        return false;
	}

    

}