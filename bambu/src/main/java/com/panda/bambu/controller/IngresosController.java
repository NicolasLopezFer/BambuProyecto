package com.panda.bambu.controller;

import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.panda.bambu.model.ingreso.Ingreso;
import com.panda.bambu.service.ingreso.IngresoService;

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
public class IngresosController 

{
    @Autowired
	IngresoService ingresoService;
	
	List<Ingreso> ingresos=new ArrayList<>();
	Boolean filtrar=false;

    @RequestMapping(value = "/ingreso", method = RequestMethod.GET)
	public ModelAndView ingresos(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = new ModelAndView();
		modelAndView.addObject("ingresos", ingresoService.findAll());
		modelAndView.setViewName("ingreso"); // resources/template/ingresos.html
		return modelAndView;
    }
    
    @RequestMapping(value = "/ingreso-crear", method = RequestMethod.POST)
	public ModelAndView crearIngreso(Ingreso a) 
	{
		ModelAndView modelAndView = new ModelAndView();
		if(ingresoService.create(a)) {
			modelAndView.addObject("responseMessage", "Ingreso guardado Exitosamente!");	
			System.out.println("Ingreso guardado Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al guardar el ingreso");
			System.out.println("NO SE GUARDO");
		}
		modelAndView.setViewName("redirect:/ingreso");
		
		return modelAndView;
    }

    @RequestMapping(value = "/ingreso-editar", method = RequestMethod.POST)
	public ModelAndView modificarIngreso(Ingreso a) {
		ModelAndView modelAndView = new ModelAndView();
		//articleRepository.save(a);
		if(ingresoService.modify(a)) {
			modelAndView.addObject("responseMessage", "Articulo editado Exitosamente!");	
			System.out.println("Articulo guardado Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al editado el articulo");
			System.out.println("NO SE GUARDO");
		}
		modelAndView.setViewName("redirect:/ingreso");
		return modelAndView;
	}

    @RequestMapping(value = "/ingreso-borrar", method = RequestMethod.GET)
	public ModelAndView borrarIngreso(long id) {
		ModelAndView modelAndView = new ModelAndView();
		if(ingresoService.delete(ingresoService.findById(id))) 
		{
			modelAndView.addObject("responseMessage", "Articulo borrado Exitosamente!");	
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al guardar el articulo");
		}
		modelAndView.setViewName("redirect:/ingreso");
		return modelAndView;
	}


	@RequestMapping(value = "/reporteIngresos", method = RequestMethod.GET)
	public ModelAndView reporteIngresos(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("lista_ingresos", ingresos);
		modelAndView.setViewName("reporteIngresos"); // resources/template/reporteIngresos.html
		return modelAndView;
	}


    @RequestMapping(value = "/ingreso-encontrarUno", method = RequestMethod.GET)
	@ResponseBody
	public Ingreso encontrarIngreso(long id) 
	{
		return ingresoService.findById(id);
	}
    


}