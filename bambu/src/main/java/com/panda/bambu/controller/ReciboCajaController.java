package com.panda.bambu.controller;

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


    @RequestMapping(value = "/reciboCaja", method = RequestMethod.GET)
	public ModelAndView reciboCaja(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("paginaActual",page);
		modelAndView.setViewName("reciboCaja"); // resources/template/reciboCaja.html
		return modelAndView;
	}

	@RequestMapping(value = "/reciboCaja-crear", method = RequestMethod.POST)
	public ModelAndView crearArticulo(ReciboCaja a) 
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
}