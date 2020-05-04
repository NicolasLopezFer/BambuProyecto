package com.panda.bambu.controller;

import java.util.Optional;

import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresa;
import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresaRepository;
import com.panda.bambu.service.service_famiempresa.ServiceFamiEmpresaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServiceController {

     @Autowired
     ServiceFamiEmpresaService serviceFamiEmpresaService;

     @Autowired
     ServiceFamiEmpresaRepository serviceFamiEmpresaRepository;

	 @RequestMapping(value = "/servicios-form", method = RequestMethod.GET)
	 public ModelAndView servicioHome() {
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.addObject("service", serviceFamiEmpresaService.findAll());
		 modelAndView.setViewName("servicio"); 
		 return modelAndView;
	 }

	@RequestMapping(value = "/servicio-crear", method = RequestMethod.POST)
	public ModelAndView crearServicio(ServiceFamiEmpresa s) {
		ModelAndView modelAndView = new ModelAndView();
		if(serviceFamiEmpresaService.create(s)) {
			modelAndView.addObject("responseMessage", "Servicio creado exitosamente!");	
			System.out.println("Servicio guardado exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Errores al guardar el servicio");
			System.out.println("No se guardo servicio");
		}
		modelAndView.setViewName("redirect:/servicios-form");
		return modelAndView;
	}

	@RequestMapping(value = "/servicio-modificar", method = RequestMethod.POST)
	public ModelAndView modificarArticulo(ServiceFamiEmpresa s) {
		ModelAndView modelAndView = new ModelAndView();
		//articleRepository.save(a);
		if(serviceFamiEmpresaService.modify(s)) {
			modelAndView.addObject("responseMessage", "Servicio editado Exitosamente!");	
			System.out.println("Servicio guardado Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al modificar este servicio");
			System.out.println("No se guardo");
		}
		modelAndView.setViewName("redirect:/servicios-form");
		return modelAndView;
	}

	@RequestMapping(value = "/servicio-borrar", method = RequestMethod.GET)
	public ModelAndView borrarArticulo(long id) {
		ModelAndView modelAndView = new ModelAndView();
		if(serviceFamiEmpresaService.delete(serviceFamiEmpresaService.findById(id))) 
		{
			modelAndView.addObject("responseMessage", "Servicio borrado Exitosamente!");	
		}else {
			modelAndView.addObject("responseMessage", "Problems bbcito pra");
		}
		modelAndView.setViewName("redirect:/servicios-form");
		return modelAndView;
	}

	@RequestMapping(value = "/servicio-encontraruno", method = RequestMethod.GET)
	@ResponseBody
	public Optional<ServiceFamiEmpresa> encontrarServicio(long id) {
		return serviceFamiEmpresaRepository.findById(id);
	}

}