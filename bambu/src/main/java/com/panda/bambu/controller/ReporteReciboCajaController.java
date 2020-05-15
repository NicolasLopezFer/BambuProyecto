package com.panda.bambu.controller;

import com.panda.bambu.model.recibo_caja.ReciboCaja;
import com.panda.bambu.service.recibo_caja.ReciboCajaService;

import org.hibernate.mapping.Set;
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

@Controller
public class ReporteReciboCajaController {
    @Autowired
	ReciboCajaService reciboCajaService;
	
	List<ReciboCaja> recibos=new ArrayList<>();

    @RequestMapping(value = "/reporteReciboCaja", method = RequestMethod.GET)
	public ModelAndView recibos(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
			
		modelAndView.addObject("lista_recibos", reciboCajaService.findAll());		
		modelAndView.setViewName("reporteReciboCaja"); // resources/template/reporteReciboCaja.html
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/filtrarFechasRecibo", method = RequestMethod.POST)
	public ModelAndView filtrarFechasRecibo(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin) 
	{
		ModelAndView modelAndView = new ModelAndView();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateInicial=LocalDate.parse(fechaInicio,formatter);
		LocalDate dateFinal=LocalDate.parse(fechaFin,formatter);
		List<ReciboCaja> list_recibos=new ArrayList<>();
		for(ReciboCaja r:reciboCajaService.findAll()){
			if(r.getFecha().isAfter(dateInicial) && r.getFecha().isBefore(dateFinal))
				list_recibos.add(r);
		}
		recibos=list_recibos;
		modelAndView.setViewName("redirect:/reporteRecibosCaja");
		return modelAndView;
	}
	
	@RequestMapping(value = "/reporteRecibosCaja", method = RequestMethod.GET)
	public ModelAndView reporteRecibosCaja(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("lista_recibos", recibos);
		modelAndView.setViewName("reporteRecibosCaja"); // resources/template/reporteReciboCajas.html
		return modelAndView;
	}

    @RequestMapping(value = "/reporteReciboCaja-encontrarUno", method = RequestMethod.GET)
	@ResponseBody
	public ReciboCaja encontrarReciboCaja(long id) 
	{
		return reciboCajaService.findById(id);
	}
    
}