package com.panda.bambu.controller;

import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.panda.bambu.model.recibo_caja.ReciboCaja;
import com.panda.bambu.service.recibo_caja.ReciboCajaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class ReciboCajaController 
{

    @Autowired
    ReciboCajaService reciboCajaService;

	List<ReciboCaja> recibos=new ArrayList<>();

    @RequestMapping(value = "/reciboCaja", method = RequestMethod.GET)
	public ModelAndView reciboCaja(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("recibosCaja", reciboCajaService.findAll());
		modelAndView.setViewName("reciboCaja"); // resources/template/reciboCaja.html
		return modelAndView;
	}

	@RequestMapping(value = "/reciboCaja-crear", method = RequestMethod.POST)
	public ModelAndView crearReciboCaja(ReciboCaja a) 
	{
		ModelAndView modelAndView = new ModelAndView();
		System.out.println(a.getFecha());
		if(reciboCajaService.create(a)) {
			modelAndView.addObject("responseMessage", "Articulo guardado Exitosamente!");	
			System.out.println("Articulo guardado Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al guardar el articulo");
			System.out.println("NO SE GUARDO");
		}
		modelAndView.setViewName("redirect:/reciboCaja");
		
		return modelAndView;
	}

	@RequestMapping(value = "/reciboCaja-editar", method = RequestMethod.POST)
	public ModelAndView modificarReciboCaja(ReciboCaja a) {
		ModelAndView modelAndView = new ModelAndView();
		//articleRepository.save(a);
		if(reciboCajaService.modify(a)) {
			modelAndView.addObject("responseMessage", "Articulo editado Exitosamente!");	
			System.out.println("Articulo guardado Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al editado el articulo");
			System.out.println("NO SE GUARDO");
		}
		modelAndView.setViewName("redirect:/reciboCaja");
		return modelAndView;
	}

	@RequestMapping(value = "/reciboCaja-borrar", method = RequestMethod.GET)
	public ModelAndView borrarReciboCaja(long id) {
		ModelAndView modelAndView = new ModelAndView();
		if(reciboCajaService.delete(reciboCajaService.findById(id))) 
		{
			modelAndView.addObject("responseMessage", "Articulo borrado Exitosamente!");	
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al guardar el articulo");
		}
		modelAndView.setViewName("redirect:/reciboCaja");
		return modelAndView;
	}

	@RequestMapping(value = "/filtrarFechasRecibo", method = RequestMethod.POST)
	public ModelAndView filtrarFechasRecibo(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin) 
	{
		return filtrarFechasReciboPost(fechaInicio, fechaFin);
	}
	
	@RequestMapping(value = "/filtrarFechasReciboCaja")
	public ModelAndView filtrarFechasReciboPost(String fechaInicio,String fechaFin) 
	{
		ModelAndView modelAndView = new ModelAndView();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateInicial=LocalDate.parse(fechaInicio,formatter);
		LocalDate dateFinal=LocalDate.parse(fechaFin,formatter);
		if(!dateInicial.isBefore(dateFinal)){
			modelAndView.setViewName("redirect:/reciboCaja");
			return modelAndView;
		}
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

	@RequestMapping(value = "/reciboCaja-encontrarUno", method = RequestMethod.GET)
	@ResponseBody
	public ReciboCaja encontrarReciboCaja(long id) 
	{
		return reciboCajaService.findById(id);
	}
}