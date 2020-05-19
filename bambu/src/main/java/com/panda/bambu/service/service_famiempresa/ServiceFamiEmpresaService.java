package com.panda.bambu.service.service_famiempresa;

import java.util.ArrayList;
import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.service_famiempresa.ServiceArticle;
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
    private ServiceArticleService serviceArticleService;



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

    public Boolean create(String code, String name, double price, List<ServiceArticle> articles) {
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
    
    public boolean isServiceAlreadyPresent(ServiceFamiEmpresa service) {
		ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service.getCode());
		if (serviceFound==null) 
			return false;
		return true;
    }

    public boolean isArticleAlreadyPresentInService(ServiceFamiEmpresa service, ServiceArticle article) {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service.getCode());
        Article articleFound = serviceArticleService.getArticle(article);
        if (articleFound != null && serviceFound != null)
            for(ServiceArticle art : serviceFound.getArticles())
                if(art.getArticle().getCode().equals(article.getArticle().getCode())) 
                    return true;
		return false;
    }
    
    public Boolean modify(ServiceFamiEmpresa service_new) {
 
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_new.getCode());
        if (serviceFound!=null){
            serviceFound.setName(service_new.getName());
            serviceFound.setPrice(service_new.getPrice());

            serviceFamiEmpresaRepository.save(serviceFound);
            return true;
        }   
        return false;
    }

    public Boolean modify(ServiceFamiEmpresa service_new, List<ServiceArticle> articles) {
 
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_new.getCode());
        if (serviceFound!=null){
            serviceFound.setName(service_new.getName());
            serviceFound.setPrice(service_new.getPrice());

            serviceFamiEmpresaRepository.save(serviceFound);
            return true;
        }   
        return false;
    }

    public Boolean modify(String code, String name, double unitCost, double salePrice, List<ServiceArticle> articles) {
        
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

    public Boolean addArticle(ServiceFamiEmpresa service_new, ServiceArticle article)
    {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_new.getCode());
        Article articleFound = serviceArticleService.getArticle(article);
        if (serviceFound!=null && articleFound != null){
            if(!isArticleAlreadyPresentInService(serviceFound, article))
            {
                serviceArticleService.create(article);
                serviceFound.addArticle(article);
                serviceFamiEmpresaRepository.save(serviceFound);
                return true;
            }
        }   
        return false;
    }

    public Boolean addArticle(String service_code, ServiceArticle article)
    {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_code);
        Article articleFound = serviceArticleService.getArticle(article);
        if (serviceFound!=null && articleFound != null){
            if(!isArticleAlreadyPresentInService(serviceFound, article))
            {
                serviceFound.addArticle(article);
                serviceFamiEmpresaRepository.save(serviceFound);
                return true;
            }
        }   
        return false;
    }


    public Boolean removeArticle(ServiceFamiEmpresa service_new, ServiceArticle article)
    {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_new.getCode());
        Article articleFound = serviceArticleService.getArticle(article);
        if (serviceFound!=null && articleFound != null){
            if(isArticleAlreadyPresentInService(serviceFound, article))
            {
                serviceFound.removeArticle(article);
                serviceArticleService.delete(article);
                serviceFamiEmpresaRepository.save(serviceFound);
                return true;
            }
        }   
        return false;
    }

    public Boolean removeArticle(String service_code, ServiceArticle article)
    {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_code);
        Article articleFound = serviceArticleService.getArticle(article);
        if (serviceFound!=null && articleFound != null){
            if(isArticleAlreadyPresentInService(serviceFound, article))
            {
                serviceFound.removeArticle(article);
                serviceArticleService.delete(article);
                serviceFamiEmpresaRepository.save(serviceFound);
                return true;
            }
        }   
        return false;
    }
    
    public Boolean modifyArticle(ServiceFamiEmpresa service_new, ServiceArticle article)
    {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_new.getCode());
        Article articleFound = serviceArticleService.getArticle(article);
        if (serviceFound!=null && articleFound != null && article != null){
            if(isArticleAlreadyPresentInService(serviceFound, article))
            {
                for(ServiceArticle art : serviceFound.getArticles()) {
                    if(art.getId().equals(article.getId())) {
                        art.setQuantity(article.getQuantity());
                        serviceArticleService.modify(art);
                    }
                }
                serviceFamiEmpresaRepository.save(serviceFound);
                return true;
            }
        }   
        return false;
    }

    public Boolean modifyArticle(String service_code, ServiceArticle article)
    {
        ServiceFamiEmpresa serviceFound = serviceFamiEmpresaRepository.findByCode(service_code);
        Article articleFound = serviceArticleService.getArticle(article);
        if (serviceFound!=null && articleFound != null){
            if(isArticleAlreadyPresentInService(serviceFound, article))
            {
                for(ServiceArticle art : serviceFound.getArticles()) {
                    if(art.getId().equals(article.getId())) {
                        art.setQuantity(article.getQuantity());
                        serviceArticleService.modify(art);
                    }
                }
                serviceFamiEmpresaRepository.save(serviceFound);
                return true;
            }
        }   
        return false;
    }

    public Boolean delete(ServiceFamiEmpresa service) {
        if(serviceFamiEmpresaRepository.existsById(service.getId())){
            List<ServiceArticle> sArtList = new ArrayList<ServiceArticle>(service.getArticles()); 
            service.getArticles().clear();
            
            for(ServiceArticle sArt: sArtList)
                serviceArticleService.delete(sArt);

            serviceFamiEmpresaRepository.delete(service);
            return true;
        }
        return false;
    }
    
    public Boolean save(ServiceFamiEmpresa service) {
        if(!isServiceAlreadyPresent(service)){
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