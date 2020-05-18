package com.panda.bambu.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.panda.bambu.model.sale_bill.ServiceSaleBill;
import com.panda.bambu.service.sale_bill.ServiceSaleBillService;


@Controller
public class ReporteFacturaServiciosController {

	@Autowired
	ServiceSaleBillService servicioFacturaServicio;
	
	List<ServiceSaleBill> facturasServicio=new ArrayList<>();
	
	Boolean filtrar=false;
	
	 /*@RequestMapping(value = "/reporteFacturaServicio", method = RequestMethod.GET)
		public ModelAndView facturas(@RequestParam(defaultValue="0") int page) {
			ModelAndView modelAndView = new ModelAndView();
			if (!filtrar)
			{
				modelAndView.addObject("lista_facturas_servicio", servicioFacturaServicio.findAll());
			}
			else
			{
				modelAndView.addObject("lista_facturas_servicio", servicioFacturaServicio.findAll());
				filtrar=false;
			}
			
			modelAndView.setViewName("reporteFacturaServicio"); // resources/template/reporteFacturaServicio.html
			
			return modelAndView;
		}*/
		@RequestMapping(value = "/filtrarFechasFactura", method = RequestMethod.POST)
		public ModelAndView filtrarFechasFactura(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin) 
		{
			return filtrarFechasFacturaServicioPost(fechaInicio, fechaFin);
		}
	
		@RequestMapping(value = "/filtrarFechasFacturaServicio")
		public ModelAndView filtrarFechasFacturaServicioPost(String fechaInicio,String fechaFin) 
		{
			ModelAndView modelAndView = new ModelAndView();
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateInicial=LocalDate.parse(fechaInicio,formatter);
			LocalDate dateFinal=LocalDate.parse(fechaFin,formatter);
			List<ServiceSaleBill> list_facturas_servicio=new ArrayList<>();
			for(ServiceSaleBill s:servicioFacturaServicio.findAll()){
				if(s.getDate().isAfter(dateInicial) && s.getDate().isBefore(dateFinal))
					list_facturas_servicio.add(s);
			}
			facturasServicio=list_facturas_servicio;
			System.out.println(fechaInicio);
			filtrar=true;
			modelAndView.setViewName("redirect:/reporteFacturasServicio");
			return modelAndView;
		}
		
		@RequestMapping(value = "/reporteFacturasServicio", method = RequestMethod.GET)
		public ModelAndView reporteFacturas(@RequestParam(defaultValue="0") int page) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("lista_factura_venta", facturasServicio);
			modelAndView.setViewName("reporteFacturasServicio"); // resources/template/reporteEgresos.html
			return modelAndView;
		}

	    @RequestMapping(value = "/reporteFacturaServicio-encontrarUno", method = RequestMethod.GET)
		@ResponseBody
		public ServiceSaleBill encontrarFacturaServicio(long id) 
		{
			return servicioFacturaServicio.findById(id);
		}
}
