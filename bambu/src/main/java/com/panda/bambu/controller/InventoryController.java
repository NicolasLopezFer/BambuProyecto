package com.panda.bambu.controller;

import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.Output;
import com.panda.bambu.model.inventory.Entry;
import com.panda.bambu.service.article.ArticleService;
import com.panda.bambu.service.inventory.ArticleInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inventario")
public class InventoryController {

	@Autowired
	ArticleInventoryService articleInventoryService;


	@Autowired
	ArticleService articleService;

	@GetMapping()
	public ModelAndView indexInventory() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("inventory", articleInventoryService.findAll());
		modelAndView.setViewName("inventario");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return modelAndView;
	}
	
	@RequestMapping(value = "/encontrar-articulo", method = RequestMethod.GET)
	@ResponseBody
	public ArticleInventory encontrarArticuloInventario(long id) {
		ArticleInventory article = articleInventoryService.findById(id);
		return article;
	}

	@RequestMapping(value = "/entradaInventario", method = RequestMethod.POST)
	public ModelAndView entradaInventario(long idArticle, Entry entry ) {

		ModelAndView modelAndView = new ModelAndView();      
        	
		if (articleInventoryService.addEntry(articleInventoryService.findById(idArticle), entry) == true) {
			modelAndView.addObject("responseMessage", "Entrada exitosa!");
		} 

		modelAndView.setViewName("redirect:/inventario");
		return modelAndView;
	}
	
	   
	@RequestMapping(value = "/salidaInventario",  method = RequestMethod.POST)
	public ModelAndView salidaInventario( long idArticle, Output output ){
		

		ModelAndView modelAndView = new ModelAndView();
		articleInventoryService.addOuput(articleInventoryService.findById(idArticle),output);

		articleInventoryService.addOuput(articleInventoryService.findById(idArticle),output);

		modelAndView.setViewName("redirect:/inventario");
		return modelAndView;
	}

	@RequestMapping(value = "/modificarMetodo",  method = RequestMethod.POST)
	public ModelAndView modificarMetodo(String method){
		
		ModelAndView modelAndView = new ModelAndView();
		if(!method.isEmpty()){
			articleInventoryService.modifyMethod(method);
		}

		modelAndView.setViewName("redirect:/inventario");
		return modelAndView;
	}


}