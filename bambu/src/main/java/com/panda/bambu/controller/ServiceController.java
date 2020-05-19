package com.panda.bambu.controller;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.service_famiempresa.ServiceArticle;
import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresa;
import com.panda.bambu.service.article.ArticleService;
import com.panda.bambu.service.service_famiempresa.ServiceArticleService;
import com.panda.bambu.service.service_famiempresa.ServiceFamiEmpresaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ServiceController {

	private long idServ = 0;

     @Autowired
	 ServiceFamiEmpresaService serviceFamiEmpresaService;

	 @Autowired
	 ServiceArticleService sArticleService;

	 @Autowired
	 ArticleService aSer;
	 
	 @RequestMapping(value = "/serviciosform", method = RequestMethod.GET)
	 public ModelAndView servicioHome() {
		 final ModelAndView modelAndView = new ModelAndView();
		 final List<ServiceFamiEmpresa> s = serviceFamiEmpresaService.findAll();
		 modelAndView.addObject("service", s);
		 modelAndView.setViewName("serviciosform"); 
		 return modelAndView;
	 }

	 @RequestMapping(value = "/verArticuloServicio", method = RequestMethod.GET)
	 public ModelAndView serArtHome() {
		 final ModelAndView modelAndView = new ModelAndView();
		 ServiceFamiEmpresa sfe = serviceFamiEmpresaService.findById(idServ);
		 List<ServiceArticle> sa = sfe.getArticles();
		 modelAndView.addObject("articles", sa);
		 modelAndView.addObject("nombreService", sfe.getName());
		 modelAndView.setViewName("verArticuloServicio"); 
		 return modelAndView;
	 }

	 @RequestMapping(value = "/serviarticulos", method = RequestMethod.GET)
	 public ModelAndView serviArticulos(final long id){
		final ModelAndView modelAndView = new ModelAndView();
		idServ = id;
		modelAndView.setViewName("redirect:/verArticuloServicio");
		return modelAndView;
	 }

	 @RequestMapping(value = "/newServArt", method = RequestMethod.POST)
	 public ModelAndView serviArticulos(@RequestParam(value = "code")String code,
	 									@RequestParam(value = "cantReque")String cant){
		final ModelAndView modelAndView = new ModelAndView();
		// System.out.println("AAAAAAAAAAAAAAAAAAAA");
		// System.out.println(code);
		// System.out.println(cant);
		Article a = aSer.findByCode(code);


		ServiceArticle servart = new ServiceArticle();

		servart.setArticle(a);
		servart.setQuantity(Integer.parseInt(cant));
		System.out.println(servart);
		System.out.println(serviceFamiEmpresaService);
		// sArticleService.create(a, Integer.parseInt(cant));

		serviceFamiEmpresaService.addArticle(serviceFamiEmpresaService.findById(idServ), servart);
		modelAndView.setViewName("redirect:/verArticuloServicio");
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
		modelAndView.setViewName("redirect:/serviciosform");
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
		modelAndView.setViewName("redirect:/serviciosform");
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
		modelAndView.setViewName("redirect:/serviciosform");
		return modelAndView;
	}

	@RequestMapping(value = "/servicioencontraruno", method = RequestMethod.GET)
	@ResponseBody
	public ServiceFamiEmpresa encontrarServicio(final long id) {
		return serviceFamiEmpresaService.findById(id);
	}


}