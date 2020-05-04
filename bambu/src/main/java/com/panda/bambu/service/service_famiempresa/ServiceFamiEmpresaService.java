package com.panda.bambu.service.service_famiempresa;

import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresa;
import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresaRepository;
import com.panda.bambu.service.article.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceFamiEmpresaService {
    
    @Autowired
    private ServiceFamiEmpresaRepository serviceFamiEmpresaRepository;  

    @Autowired
    private ArticleService articleService;

    public ServiceFamiEmpresa findById(Long id){
        return serviceFamiEmpresaRepository.findById(id).get();
    }

    public ServiceFamiEmpresa findByCode(String code){
        return serviceFamiEmpresaRepository.findByCode(code);
    }
    
    public List<ServiceFamiEmpresa> findAll(){
        return serviceFamiEmpresaRepository.findAll();
    }

    public Boolean create(String code, String name, double price) {
        ServiceFamiEmpresa service =serviceFamiEmpresaRepository.findByCode(code);
        if(service==null){
            if (code.matches(".*[a-z].*")){
                ServiceFamiEmpresa newArticle = new ServiceFamiEmpresa(code, name, price);
                serviceFamiEmpresaRepository.save(newArticle);
                return true;
            }
        }
        return false;    
    }

    public Boolean create(String code, String name, double price, List<Article> articles) {
        ServiceFamiEmpresa service =serviceFamiEmpresaRepository.findByCode(code);
        if(service==null){
            if (code.matches(".*[a-z].*")){
                ServiceFamiEmpresa newArticle = new ServiceFamiEmpresa(code, name, price, articles);
                serviceFamiEmpresaRepository.save(newArticle);
                return true;
            }
        }
        return false;    
    }
    
    public Boolean create(ServiceFamiEmpresa service) {
        ServiceFamiEmpresa serviceF=serviceFamiEmpresaRepository.findByCode(service.getCode());
        if(serviceF==null){
            serviceFamiEmpresaRepository.save(service);
            return true;
        }
        return false;    
    }
    
    public boolean isArticleAlreadyPresent(ServiceFamiEmpresa service) {
		ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service.getCode());
		if (serviceFound==null) 
			return false;
		return true;
    }
       
    public Boolean modify(ServiceFamiEmpresa service_new) {
 
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_new.getCode());
        if (serviceFound!=null){
            serviceFound.setName(service_new.getName());
            serviceFound.setPrice(service_new.getPrice());
            serviceFound.setArticles(service_new.getArticles());
            serviceFamiEmpresaRepository.save(serviceFound);
            return true;
        }   
        return false;
    }

    public Boolean modify(ServiceFamiEmpresa service_new, List<Article> articles) {
 
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_new.getCode());
        if (serviceFound!=null){
            serviceFound.setName(service_new.getName());
            serviceFound.setPrice(service_new.getPrice());
            serviceFound.setArticles(articles);
            serviceFamiEmpresaRepository.save(serviceFound);
            return true;
        }   
        return false;
    }

    public Boolean modify(String code, String name, double unitCost, double salePrice, List<Article> articles) {
        
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(code);
        if (serviceFound!=null){
            serviceFound.setName(name);
            serviceFound.setPrice(salePrice);
            serviceFound.setArticles(articles);
            serviceFamiEmpresaRepository.save(serviceFound);
            return true;
        }   
        return false;
    }

    public Boolean addArticle(ServiceFamiEmpresa service_new, Article article)
    {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_new.getCode());
        Article articleFound = articleService.findByCode(article.getCode());
        if (serviceFound!=null && articleFound != null){
            serviceFound.addArticle(articleFound);
            serviceFamiEmpresaRepository.save(serviceFound);
            return true;
        }   
        return false;
    }

    public Boolean addArticle(String service_code, Article article)
    {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_code);
        Article articleFound = articleService.findByCode(article.getCode());
        if (serviceFound!=null && articleFound != null){
            serviceFound.addArticle(articleFound);
            serviceFamiEmpresaRepository.save(serviceFound);
            return true;
        }   
        return false;
    }


    public Boolean removeArticle(ServiceFamiEmpresa service_new, Article article)
    {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_new.getCode());
        Article articleFound = articleService.findByCode(article.getCode());
        if (serviceFound!=null && articleFound != null){
            serviceFound.removeArticle(articleFound);
            serviceFamiEmpresaRepository.save(serviceFound);
            return true;
        }   
        return false;
    }

    public Boolean removeArticle(String service_code, Article article)
    {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_code);
        Article articleFound = articleService.findByCode(article.getCode());
        if (serviceFound!=null && articleFound != null){
            serviceFound.removeArticle(articleFound);
            serviceFamiEmpresaRepository.save(serviceFound);
            return true;
        }   
        return false;
    }
    
    public Boolean delete(ServiceFamiEmpresa service) {
        if(serviceFamiEmpresaRepository.existsById(service.getId())){
            serviceFamiEmpresaRepository.delete(service);
            return true;
        }
        return false;
    }
    
    public Boolean save(ServiceFamiEmpresa service) {
        if(!isArticleAlreadyPresent(service)){
            if (service.getCode().matches(".*[a-z].*")){
                serviceFamiEmpresaRepository.save(service);
                return true;
            }
        }
        return false;    
    }

    public void deleteAllServicesFamiEmpresa(){
        serviceFamiEmpresaRepository.deleteAll();
    }


}