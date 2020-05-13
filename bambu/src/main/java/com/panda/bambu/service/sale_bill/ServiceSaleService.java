package com.panda.bambu.service.sale_bill;

import java.util.List;

import com.panda.bambu.model.sale_bill.ServiceSale;
import com.panda.bambu.model.sale_bill.ServiceSaleRepository;
import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresa;
import com.panda.bambu.service.service_famiempresa.ServiceFamiEmpresaService;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceSaleService {
    @Autowired
    ServiceSaleRepository serviceSaleRepository;
    
    @Autowired
    ServiceFamiEmpresaService serviceFamiEmpresaService;
    
    public ServiceSale findById(Long id){
          return serviceSaleRepository.findById(id).get();
    }

    public List<ServiceSale> findAll(){
        return serviceSaleRepository.findAll();
    }
   
	public boolean isServiceSaleAlreadyPresent(ServiceSale serviceSale) {
		ServiceSale serviceSaleFound = serviceSaleRepository.findByServiceFamiEmpresa(serviceSale.getServiceFamiEmpresa());
		if (serviceSaleFound ==null) 
			return false;
		return true;
    }

    public double calculateTotal(ServiceFamiEmpresa service, int quantity, float discount){
        double total = 0.0; 
        if(service != null  && quantity > 0){
           total = service.getPrice() * quantity;
           if(discount > 1){
              discount = discount/100;
           }
           if(discount > 0){
              total -= total*discount;
           }
           return total; 
         }
      return total;
    }

    public boolean create(ServiceSale serviceSale){

        if(serviceSale != null){
           serviceSaleRepository.save(serviceSale);
           return true;
        }
        return false;
    }

    public ServiceSale create(ServiceFamiEmpresa service, int quantity, float discount) {
        ServiceSale serviceNew = new ServiceSale();
        if(serviceFamiEmpresaService.findByCode(service.getCode()) != null){
            serviceNew.setServiceFamiEmpresa(service);
            serviceNew.setQuantity(quantity);
            serviceNew.setDiscount(discount);
            serviceNew.setTotalValue(calculateTotal(service, quantity, discount));
            serviceSaleRepository.save(serviceNew);
        }

        return serviceNew;    
    }

    public boolean delete(ServiceSale article){

        if(serviceSaleRepository.existsById(article.getId())){
            serviceSaleRepository.delete(article);
            return true;
        }
 
        return false;
    }


}