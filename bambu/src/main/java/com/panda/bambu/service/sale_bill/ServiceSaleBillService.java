package com.panda.bambu.service.sale_bill;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.inventory.Output;
import com.panda.bambu.model.sale_bill.ServiceSale;
import com.panda.bambu.model.sale_bill.ServiceSaleBill;
import com.panda.bambu.model.sale_bill.ServiceSaleBillRepository;
import com.panda.bambu.model.service_famiempresa.ServiceArticle;
import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresa;
import com.panda.bambu.service.inventory.ArticleInventoryService;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceSaleBillService {
      
    @Autowired
    private ServiceSaleBillRepository serviceSaleBillRepository;
     
    @Autowired
    private ServiceSaleService serviceSaleService;

    @Autowired
    private ArticleInventoryService articleInventoryService;


    public ServiceSaleBill findById(Long id){   
        return serviceSaleBillRepository.findById(id).get();
    }

    public boolean existById(Long id){
        return serviceSaleBillRepository.existsById(id);
    }

    public ServiceSaleBill findByCode(String code){   
        return serviceSaleBillRepository.findByCode(code);
    }
     
    public List<ServiceSaleBill> findAll(){
        return serviceSaleBillRepository.findAll();
    }

    public boolean isRepeatCode(ServiceSaleBill serviceBill, ServiceSale serviceSale){
         
        for(ServiceSale service: serviceBill.getServices())
        {
            if(service.equals(serviceSale))
            {
               return false;
            }
        }
        return true;
    }

    public boolean create(ServiceSaleBill serviceBill){
           
        if(serviceSaleBillRepository.findByCode(serviceBill.getCode()) == null){
            if(!serviceBill.getServices().isEmpty()){
                for(ServiceSale service: serviceBill.getServices()){
                    if(service == null || isRepeatCode(serviceBill, service)){
                        return false;   
                    }

                    if(serviceSaleService.create(service) == false){
                      return false;
                    }
                    ServiceFamiEmpresa  serviceFE = service.getServiceFamiEmpresa();
                    for(ServiceArticle serviceArticle : serviceFE.getArticles())
                    {
                        Article article = serviceArticle.getArticle();
                        Output output = new Output();
                        output.setCode(serviceBill.getCode());
                        output.setDetail("Venta Servicio");
                        output.setQuantity(article.getQuantity());
                        articleInventoryService.addOuput(articleInventoryService.findByArticle(article), output);
                    }
                }
                serviceSaleBillRepository.save(serviceBill);
            }   
        }
        return false;
    }

    public boolean create(String code, String customer_name, LocalDate date, LocalDate expiration, Set<ServiceSale> services){
        ServiceSaleBill serviceBill =  new ServiceSaleBill(code, customer_name, date, expiration, services);
        if(serviceSaleBillRepository.findByCode(serviceBill.getCode()) == null){
            if(!serviceBill.getServices().isEmpty()){
                for(ServiceSale service: serviceBill.getServices()){
                    if(service == null || isRepeatCode(serviceBill, service)){
                        return false;   
                    }

                    if(serviceSaleService.create(service) == false){
                      return false;
                    }
                    ServiceFamiEmpresa  serviceFE = service.getServiceFamiEmpresa();
                    for(ServiceArticle serviceArticle : serviceFE.getArticles())
                    {
                        Article article = serviceArticle.getArticle();
                        Output output = new Output();
                        output.setCode(serviceBill.getCode());
                        output.setDetail("Venta Servicio");
                        output.setQuantity(article.getQuantity());
                        articleInventoryService.addOuput(articleInventoryService.findByArticle(article), output);
                    }
                }
                serviceSaleBillRepository.save(serviceBill);
            }   
        }
        return false;
    }



}