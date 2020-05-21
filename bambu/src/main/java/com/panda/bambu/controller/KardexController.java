package com.panda.bambu.controller;


import com.panda.bambu.service.inventory.ArticleInventoryService;
import com.panda.bambu.service.inventory.InventoryService;

import java.time.LocalDate;

import java.util.List;


import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.Kardex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("kardex")
public class KardexController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
	ArticleInventoryService articleInventoryService;
    
    
    @GetMapping()
    @ResponseBody
	public ModelAndView kardexInventory(Long idArticle, String dateI, String dateF) {
        ModelAndView modelAndView = new ModelAndView();
        ArticleInventory article = articleInventoryService.findById(idArticle);
        if(article.getInventories()!=null && !article.getInventories().isEmpty()){
            LocalDate dateInicial = LocalDate.parse(dateI);
            LocalDate dateFinal = LocalDate.parse(dateF);
            List<Kardex> kardex = inventoryService.createKardex(dateInicial, dateFinal, article.getInventories().get(0));
            modelAndView.addObject("kardex", kardex);
            modelAndView.setViewName("kardex");
        }
       
		return modelAndView;
    }
    



    
}