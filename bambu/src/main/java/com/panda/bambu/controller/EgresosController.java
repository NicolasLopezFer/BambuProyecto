package com.panda.bambu.controller;

import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.panda.bambu.model.egreso.Egreso;
import com.panda.bambu.service.egreso.EgresoService;

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
public class EgresosController 

{
    @Autowired
	EgresoService egresoService;
	
	List<Egreso> egresos=new ArrayList<>();
	Boolean filtrar=false;

    @RequestMapping(value = "/egreso", method = RequestMethod.GET)
	public ModelAndView egresos(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = new ModelAndView();
		modelAndView.addObject("egresos", egresoService.findAll());
		modelAndView.setViewName("egreso"); // resources/template/egresos.html
		return modelAndView;
    }
    
    @RequestMapping(value = "/egreso-crear", method = RequestMethod.POST)
	public ModelAndView crearEgreso(Egreso a) 
	{
		ModelAndView modelAndView = new ModelAndView();
		if(egresoService.create(a)) {
			modelAndView.addObject("responseMessage", "Articulo guardado Exitosamente!");	
			System.out.println("Egreso guardado Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al guardar el articulo");
			System.out.println("NO SE GUARDO");
		}
		modelAndView.setViewName("redirect:/egreso");
		
		return modelAndView;
    }

    @RequestMapping(value = "/egreso-editar", method = RequestMethod.POST)
	public ModelAndView modificarEgreso(Egreso a) {
		ModelAndView modelAndView = new ModelAndView();
		//articleRepository.save(a);
		if(egresoService.modify(a)) {
			modelAndView.addObject("responseMessage", "Egreso editado Exitosamente!");	
			System.out.println("Articulo guardado Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al editado el egreso");
			System.out.println("NO SE GUARDO");
		}
		modelAndView.setViewName("redirect:/egreso");
		return modelAndView;
	}

    @RequestMapping(value = "/egreso-borrar", method = RequestMethod.GET)
	public ModelAndView borrarEgreso(long id) {
		ModelAndView modelAndView = new ModelAndView();
		if(egresoService.delete(egresoService.findById(id))) 
		{
			modelAndView.addObject("responseMessage", "Articulo borrado Exitosamente!");	
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al guardar el articulo");
		}
		modelAndView.setViewName("redirect:/egreso");
		return modelAndView;
	}

	@RequestMapping(value = "/filtrarFechas", method = RequestMethod.POST)
	public ModelAndView filtrarFechas(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin) 
	{
		return filtrarFechasEgreso(fechaInicio,fechaFin);
	}

	@RequestMapping(value = "/filtrarFechasEgreso")
	public ModelAndView filtrarFechasEgreso(String fechaInicio,String fechaFin)
	{
		ModelAndView modelAndView = new ModelAndView();
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateInicial=LocalDate.parse(fechaInicio,formatter);
		LocalDate dateFinal=LocalDate.parse(fechaFin,formatter);
		if (!dateInicial.isBefore(dateFinal))
		{
			modelAndView.setViewName("redirect:/egreso");
			return modelAndView;
		}
		List<Egreso> list_egresos=new ArrayList<>();
		for(Egreso e:egresoService.findAll()){
			if(e.getFecha().isAfter(dateInicial) && e.getFecha().isBefore(dateFinal))
				list_egresos.add(e);
		}
		egresos=list_egresos;
		System.out.println(fechaInicio);
		filtrar=true;
		modelAndView.setViewName("redirect:/reporteEgresos");
		return modelAndView;
	}

	@RequestMapping(value = "/reporteEgresos", method = RequestMethod.GET)
	public ModelAndView reporteEgresos(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("lista_egresos", egresos);
		modelAndView.setViewName("reporteEgresos"); // resources/template/reporteEgresos.html
		return modelAndView;
	}


    @RequestMapping(value = "/egreso-encontrarUno", method = RequestMethod.GET)
	@ResponseBody
	public Egreso encontrarEgreso(long id) 
	{
		return egresoService.findById(id);
	}
    


}