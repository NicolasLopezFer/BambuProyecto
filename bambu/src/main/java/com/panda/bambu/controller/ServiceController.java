package com.panda.bambu.controller;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresa;
import com.panda.bambu.service.service_famiempresa.ServiceFamiEmpresaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ServiceController {

     @Autowired
	 ServiceFamiEmpresaService serviceFamiEmpresaService;
	 
	 @RequestMapping(value = "/serviciosform", method = RequestMethod.GET)
	 public ModelAndView servicioHome() {
		 final ModelAndView modelAndView = new ModelAndView();
		 final List<ServiceFamiEmpresa> s = serviceFamiEmpresaService.findAll();
		 modelAndView.addObject("service", s);
		 modelAndView.setViewName("serviciosform"); 
		 return modelAndView;
	 }

	 @RequestMapping(value = "/serviarticulos", method = RequestMethod.GET)
	 public ModelAndView serviArticulos(List<Article> a){
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("article", a);
		modelAndView.setViewName("serviarticulos");
		return modelAndView;
	 }

	 @RequestMapping(value = "/newArtServ", method = RequestMethod.POST)
	 public ModelAndView serviArticulos(List<Article> a){
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("article", a);
		modelAndView.setViewName("serviarticulos");
		return modelAndView;
	 }

	@RequestMapping(value = "/serviciocrear", method = RequestMethod.POST)
	public ModelAndView crearServicio(final ServiceFamiEmpresa s) {
		final ModelAndView modelAndView = new ModelAndView();
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

	@RequestMapping(value = "/serviciomodificar", method = RequestMethod.POST)
	public ModelAndView modificarArticulo(final ServiceFamiEmpresa s) {
		final ModelAndView modelAndView = new ModelAndView();
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

	@RequestMapping(value = "/servicioborrar", method = RequestMethod.GET)
	public ModelAndView borrarArticulo(final long id) {
		final ModelAndView modelAndView = new ModelAndView();
		if(serviceFamiEmpresaService.delete(serviceFamiEmpresaService.findById(id))) 
		{
			modelAndView.addObject("responseMessage", "Servicio borrado Exitosamente!");	
		}else {
			modelAndView.addObject("responseMessage", "Problems bbcito pra");
		}
		modelAndView.setViewName("redirect:/servicios-form");
		return modelAndView;
	}

	@RequestMapping(value = "/servicioencontraruno", method = RequestMethod.GET)
	@ResponseBody
	public ServiceFamiEmpresa encontrarServicio(final long id) {
		return serviceFamiEmpresaService.findById(id);
	}


}