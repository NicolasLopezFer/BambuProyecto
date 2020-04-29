package com.panda.bambu.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.Inventory;
import com.panda.bambu.model.inventory.InventoryRepository;
import com.panda.bambu.model.inventory.Output;
import com.panda.bambu.model.inventory.Entry;
import com.panda.bambu.service.article.ArticleService;
import com.panda.bambu.service.inventory.ArticleInventoryService;
import com.panda.bambu.service.inventory.InventoryService;

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
public class InventoryController {

    
    @Autowired
	ArticleInventoryService articleInventoryService;

	@RequestMapping(value = "/inventario", method = RequestMethod.GET)
	public ModelAndView indexInventory() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("inventory", articleInventoryService.findAll());
		modelAndView.setViewName("inventario"); 
		return modelAndView;
	}


	@RequestMapping(value = "/entradaInventario", method = RequestMethod.POST)
	public ModelAndView entradaInventario() {
		ModelAndView modelAndView = new ModelAndView();
		ArticleInventory arinven = new ArticleInventory();
		Entry entry = new Entry();
		if(articleInventoryService.addEntry(arinven,entry)) {
			modelAndView.addObject("responseMessage", "Entrada exitosa!");	
			System.out.println("Entrada exitosa!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores");
			System.out.println("No entrada");
		}
		modelAndView.setViewName("redirect:/inventario");
		return modelAndView;
	}
}