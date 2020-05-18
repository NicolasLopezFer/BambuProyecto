package com.panda.bambu.controller;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.sale_bill.ServiceSale;
import com.panda.bambu.model.sale_bill.ServiceSaleBill;
import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresa;
import com.panda.bambu.service.sale_bill.ServiceSaleBillService;
import com.panda.bambu.service.sale_bill.ServiceSaleService;
import com.panda.bambu.service.service_famiempresa.ServiceFamiEmpresaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ServiceBillController {

     @Autowired
	 ServiceFamiEmpresaService serviceFamiEmpresaService;
	 
	 @Autowired
	 ServiceSaleService serSaleService;

	 @Autowired
	 ServiceSaleBillService serSaleBillService;
	 
	 @RequestMapping(value = "/serviFacturaForm", method = RequestMethod.GET)
	 public ModelAndView servicioHome() {
		 final ModelAndView modelAndView = new ModelAndView();
		 final List<ServiceSale> s = serSaleService.findAll();
		 double total = 0;
		 for(int i = 0;i<s.size();i++){
			total = total + s.get(i).getTotalValue();
		 }
		 modelAndView.addObject("serviceSale", s);
		 modelAndView.addObject("total", total);
		 modelAndView.setViewName("serviFacturaForm"); 
		 return modelAndView;
	 }

	 @RequestMapping(value = "/guardarBill", method = RequestMethod.POST)
	 public void servicioHomePost(@RequestParam(value = "numeroFacturaDeVenta") String codeString,
								   @RequestParam(value = "clienteFacturaDeVenta") String nombre,
								   @RequestParam(value = "nitFacutraDeVenta") String nit,
								   @RequestParam(value = "fechaFacturaDeVenta") String fechaVenta,
								   @RequestParam(value = "fechaVencimiento") String fechaVencimiento,
								   @RequestParam(value = "valorTotal") String totalPago) {
		 ServiceSale ss = new ServiceSale();
		 ServiceSaleBill ssb = new ServiceSaleBill();
		 
	 }

	 @RequestMapping(value = "/agregarServicio", method = RequestMethod.POST)
	 public ModelAndView serviBill(@RequestParam(value = "code") String code,
	 					   @RequestParam(value = "cantidad") String cantidad,
	 					   @RequestParam(value = "descuento") String descuento) {
		 ModelAndView modelAndView = new ModelAndView();
		 ServiceSale ss = new ServiceSale();
		 ServiceFamiEmpresa sfe = new ServiceFamiEmpresa();
		 System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		 sfe = serviceFamiEmpresaService.findByCode(code);
		 ss.setServiceFamiEmpresa(sfe);
		 ss.setDiscount(Float.parseFloat(descuento));
		 ss.setQuantity(Float.parseFloat(cantidad));
		 ss.setTotalValue(serSaleService.calculateTotal(sfe,Integer.parseInt(cantidad),Float.parseFloat(descuento)));
		 serSaleService.create(ss);
		 modelAndView.setViewName("redirect:/serviFacturaForm");					
		 return modelAndView;
	 }

	 @RequestMapping(value = "/borrarServicio", method = RequestMethod.GET)
	public ModelAndView borrarArticulo(final long id) {
		final ModelAndView modelAndView = new ModelAndView();
		if(serSaleService.delete(serSaleService.findById(id))) 
		{
			modelAndView.addObject("responseMessage", "Servicio borrado Exitosamente!");	
		}else {
			modelAndView.addObject("responseMessage", "Problems bbcito pra");
		}
		modelAndView.setViewName("redirect:/serviFacturaForm");
		return modelAndView;
	}

}