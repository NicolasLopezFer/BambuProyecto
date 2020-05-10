package com.panda.bambu.controller;

import com.panda.bambu.model.egreso.Egreso;
import com.panda.bambu.service.egreso.EgresoService;

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
import java.util.Date;
import java.util.List;

@Controller
public class ReporteEgresoController {
    @Autowired
	EgresoService egresoService;
	
	List<Egreso> egresos=new ArrayList<>();
	Boolean filtrar=false;

    @RequestMapping(value = "/reporteEgreso", method = RequestMethod.GET)
	public ModelAndView egresos(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		if (!filtrar)
		{
			modelAndView.addObject("lista_egresos", egresoService.findAll());
		}
		else
		{
			modelAndView.addObject("lista_egresos", egresos);
			filtrar=false;
		}
		
		modelAndView.setViewName("reporteEgreso"); // resources/template/reporteEgreso.html
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/filtrarFechas", method = RequestMethod.POST)
	public ModelAndView filtrarFechas(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin) 
	{
		ModelAndView modelAndView = new ModelAndView();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateInicial=LocalDate.parse(fechaInicio,formatter);
		LocalDate dateFinal=LocalDate.parse(fechaFin,formatter);
		List<Egreso> list_egresos=new ArrayList<>();
		for(Egreso e:egresoService.findAll()){
			if(e.getFecha().isAfter(dateInicial) && e.getFecha().isBefore(dateFinal))
				list_egresos.add(e);
		}
		egresos=list_egresos;
		System.out.println(fechaInicio);
		filtrar=true;
		modelAndView.setViewName("redirect:/reporteEgreso");
		return modelAndView;
    }

    @RequestMapping(value = "/reporteEgreso-encontrarUno", method = RequestMethod.GET)
	@ResponseBody
	public Egreso encontrarEgreso(long id) 
	{
		return egresoService.findById(id);
	}
    
}