package com.panda.bambu.controller;

import com.panda.bambu.service.article.ArticleService;
import com.panda.bambu.service.return_articles.ReturnArticlesService;
import com.panda.bambu.service.sale_bill.ArticleSaleBillService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.return_articles.ArticleReturn;
import com.panda.bambu.model.return_articles.ReturnArticles;
import com.panda.bambu.model.sale_bill.ArticleSaleBill;

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
@RequestMapping("/devoluciones")
public class ReturnArticlesController {

	@Autowired
	ReturnArticlesService returnArticlesService;

	@Autowired
	ArticleSaleBillService articleSaleBillService;

	@Autowired
	ArticleService articleService;

	List<ArticleReturn> returns = new ArrayList<ArticleReturn>(); 

	int change = -1;

	String code = "";
	String name_customer = "";
	Integer idCustomer = null;
	LocalDate date = null;


	@GetMapping()
	public ModelAndView indexReturnArticles() {
	
		final List<Article> articles = articleService.findAll();
		final List<ArticleSaleBill> saleBills = articleSaleBillService.findAll();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("returns", returns);
		modelAndView.addObject("codeReturn", code);
		modelAndView.addObject("customerReturn", name_customer);
		modelAndView.addObject("idCustomer", idCustomer);
		modelAndView.addObject("dateReturn", date);
		modelAndView.addObject("articles", articles);
		modelAndView.addObject("articlesEdit", articles);
		modelAndView.addObject("saleBills", saleBills);
		modelAndView.addObject("saleBillsEdit", saleBills);

		modelAndView.setViewName("devoluciones");
		
		//IMPORTANTE
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//System.out.println(auth.getName());

		return modelAndView;
	}

	@RequestMapping(value = "/manetenerInformacion", method = RequestMethod.GET)
	@ResponseBody
	public void mantenerInformacion(String code, String name, int nit, String date){
		 this.code = code;
		 this.name_customer = name;
		 this.idCustomer = nit;
		 this.date = LocalDate.parse(date);
	}
	
	@RequestMapping(value = "/obtenerPrecioArticulo", method = RequestMethod.GET)
	@ResponseBody
	public double obtenerPrecioArticulo(String index){
			
		  double price = 0.0;
		  Article article = articleService.findAll().get(Integer.parseInt(index)-1);
		  if(article != null){
             return article.getSalePrice();
		  }
    	  return price;
	}

	@RequestMapping(value = "/agregarArticulo", method = RequestMethod.POST)
	public ModelAndView agregarArticuloDevolucion(int idArticle, String nSale, ArticleReturn articleReturn){
		ModelAndView modelAndView = new ModelAndView();  
		Article article = articleService.findAll().get(idArticle);
		ArticleSaleBill saleBill = articleSaleBillService.findByCode(nSale);
		
		if(article != null) {
		   articleReturn.setArticle(article);
		}

		if(saleBill != null){
		   articleReturn.setnSale(nSale);
		}

        returns.add(articleReturn);
		System.out.println();
		System.out.println();
		System.out.println("LLEGUEEEE");
		modelAndView.setViewName("redirect:/devoluciones");
		return modelAndView;
	}
	
	@RequestMapping(value = "/obtenerArticulo", method = RequestMethod.GET)
	@ResponseBody
	public ArticleReturn obtenerArticuloDevolucion(String index){
		
		ArticleReturn articleReturn = null;

    	System.out.println();
		System.out.println();
		System.out.println("LLEGUEEEE EDIT "+index);
        change = Integer.parseInt(index);
		if(!index.isEmpty()) {
		   return returns.get(Integer.parseInt(index));
		}
        
		return articleReturn;
	}
	
	@RequestMapping(value = "/editarArticulo", method = RequestMethod.POST)
	public ModelAndView editarArticuloDevolucion(int idArticle, String nSale, ArticleReturn articleReturn){
		ModelAndView modelAndView = new ModelAndView();  
		Article article = articleService.findAll().get(idArticle);
		ArticleSaleBill saleBill = articleSaleBillService.findByCode(nSale);
		ArticleReturn articleL = null;

		if(articleReturn != null && idArticle > 0){
			if(article != null) {
			articleReturn.setArticle(article);
			}

			if(saleBill != null){
			articleReturn.setnSale(nSale);
			}
			
			articleL = returns.get(change);

			System.out.println();
			System.out.println();
			System.out.println("LLEGUEEEE A EDITAR LA DEVO´" +change);
			modelAndView.setViewName("redirect:/devoluciones");

			articleL.setArticle(article);
			articleL.setQuantity(articleReturn.getQuantity());
			articleL.setMotive(articleReturn.getMotive());
			articleL.setValue(articleReturn.getValue());
			articleL.setnSale(articleReturn.getnSale());
	    }

		System.out.println();
		System.out.println();
		System.out.println("LLEGUEEEE A EDITAR LA DEVO");
		modelAndView.setViewName("redirect:/devoluciones");
		
		return modelAndView;
	}

	@RequestMapping(value = "/obtenerArticuloEliminar", method = RequestMethod.GET)
	@ResponseBody
	public int obtenerArticuloEliminarDevolucion(String index){

    	System.out.println();
		System.out.println();
		System.out.println("LLEGUEEEE DELETE "+index);
        int i = -1;
		if(!index.isEmpty()) {
		   i = Integer.parseInt(index);
		   return i;
		}
        
		return i;
	}

	@RequestMapping(value = "/eliminarArticulo", method = RequestMethod.POST)
	public ModelAndView eliminarArticuloDevolucion(int index){
		ModelAndView modelAndView = new ModelAndView();  

		if(index >=0 ){
		   returns.remove(index);
		}

		modelAndView.setViewName("redirect:/devoluciones");
		
		return modelAndView;
	}
	
    @RequestMapping(value = "/crearDevolucion", method = RequestMethod.POST)
	public ModelAndView crearDevolucion(int typeReturn, ReturnArticles returnArticle) {
		ModelAndView modelAndView = new ModelAndView();
		if(returnArticle != null && returns != null){
			if(typeReturn == 1){
			   returnArticle.setSaleReturn(true);
			   returnArticle.setBuyReturn(false);
			  	
			}
			else{
				returnArticle.setSaleReturn(false);
				returnArticle.setBuyReturn(true);
				   
			}

			returnArticle.setArticles(returns);
			if(returnArticlesService.create(returnArticle) == true){
			   System.out.println("HIJOLE LLEGUE");
			   System.out.println("HIJOLE LLEGUE A DEVOLUCION");
			}
		}
		modelAndView.setViewName("redirect:/devoluciones");
		return modelAndView;
	}
	

}