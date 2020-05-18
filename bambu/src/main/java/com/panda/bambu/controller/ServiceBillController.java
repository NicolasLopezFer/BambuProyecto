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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ServiceBillController {

	private List<ServiceSale> serSale = new ArrayList<ServiceSale>();

     @Autowired
	 ServiceFamiEmpresaService serviceFamiEmpresaService;
	 
	 @Autowired
	 ServiceSaleService serSaleService;

	 @Autowired
	 ServiceSaleBillService serSaleBillService;
	 
	 @RequestMapping(value = "/serviFacturaForm", method = RequestMethod.GET)
	 public ModelAndView servicioHome() {
		 final ModelAndView modelAndView = new ModelAndView();
		 final List<ServiceSale> s = serSale;
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
	 public ModelAndView servicioHomePost(@RequestParam(value = "numeroFacturaDeVenta") String codeString,
								   @RequestParam(value = "clienteFacturaDeVenta") String nombre,
								   @RequestParam(value = "nitFacutraDeVenta") String nit,
								   @RequestParam(value = "fechaFacturaDeVenta") String fechaVenta,
								   @RequestParam(value = "fechaVencimiento") String fechaVencimiento,
								   @RequestParam(value = "valorTotal") String totalPago) {
		ModelAndView modelAndView = new ModelAndView();
		 ServiceSale ss = new ServiceSale();
		 ServiceSaleBill ssb = new ServiceSaleBill();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 LocalDate dateFactura = LocalDate.parse(fechaVenta,formatter);
		 LocalDate dateVencimiento = LocalDate.parse(fechaVencimiento, formatter);
		
		for(int i = 0; i < serSale.size();i++){
			serSaleService.create(serSale.get(i));
		}

		serSaleBillService.create(codeString, nombre, dateFactura, dateVencimiento, serSale)
		 modelAndView.setViewName("redirect:/serviFacturaForm");	
		 return modelAndView;
	 }

	 @RequestMapping(value = "/agregarServicio", method = RequestMethod.POST)
	 public ModelAndView serviBill(@RequestParam(value = "code") String code,
	 					   @RequestParam(value = "cantidad") String cantidad,
	 					   @RequestParam(value = "descuento") String descuento) {
		 ModelAndView modelAndView = new ModelAndView();
		 ServiceSale ss = new ServiceSale();
		 ServiceFamiEmpresa sfe = new ServiceFamiEmpresa();
		 sfe = serviceFamiEmpresaService.findByCode(code);
		 if(sfe != null){
			ss.setServiceFamiEmpresa(sfe);
			 ss.setDiscount(Float.parseFloat(descuento));
		 	ss.setQuantity(Float.parseFloat(cantidad));
		 	ss.setTotalValue(serSaleService.calculateTotal(sfe,Integer.parseInt(cantidad),Float.parseFloat(descuento)));
		 	serSale.add(ss); 
		 }else{
			modelAndView.addObject("mensaje","No existe tal servicio");
		 }
		 
		 modelAndView.setViewName("redirect:/serviFacturaForm");					
		 return modelAndView;
	 }

	 @RequestMapping(value = "/borrarServicio", method = RequestMethod.GET)
	public ModelAndView borrarArticulo(final float id) {
		final ModelAndView modelAndView = new ModelAndView();
		for(int i = 0;i<serSale.size();i++){
			double code = serSale.get(i).getTotalValue();
			if(code == id){
				serSale.remove(i);
			}
		}
		modelAndView.setViewName("redirect:/serviFacturaForm");
		return modelAndView;
	}

	@RequestMapping(value = "/irAtras", method = RequestMethod.GET)
	public ModelAndView limpiarLista() {
		final ModelAndView modelAndView = new ModelAndView();
		serSale.clear();
		modelAndView.setViewName("redirect:/emprendedor");
		return modelAndView;
	}
}