package com.panda.bambu.controller;

import com.panda.bambu.service.article.ArticleService;
import com.panda.bambu.service.sale_bill.ArticleSaleBillService;
import com.panda.bambu.service.tax.TaxService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.sale_bill.ArticleSale;
import com.panda.bambu.model.sale_bill.ArticleSaleBill;
import com.panda.bambu.model.tax.Tax;

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
@RequestMapping("/factura-de-venta-articulos")
public class ArticleSaleBillController {

    @Autowired
	ArticleSaleBillService articleSaleBillService;

	@Autowired
	TaxService taxService;

	@Autowired
	ArticleService articleService;

	List<ArticleSale> articlesSaleBill = new ArrayList<ArticleSale>(); 
	
	List<Tax> taxes = new ArrayList<Tax>();
	
	List<List<Tax>> articlesTaxes = new ArrayList<List<Tax>>();

	int change = -1;
	String code = "";
	String name_customer = "";
    Integer idCustomer = null;
    LocalDate date;
	LocalDate dateEx;
	Double total = 0.0;

	@GetMapping()
	public ModelAndView indexFacturaDeVentaArticulos() {
	
		final List<Article> articles = articleService.findAll();
		List<Tax> taxesD = taxService.findAll();
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("articlesSaleBill", articlesSaleBill);
		modelAndView.addObject("code", code);
		modelAndView.addObject("customer", name_customer);
		modelAndView.addObject("idCustomer", idCustomer);
        modelAndView.addObject("date", date);
        modelAndView.addObject("dateEx", dateEx);
		modelAndView.addObject("articles", articles);
		modelAndView.addObject("taxes", taxesD);
		modelAndView.addObject("taxesArticle", taxes);
		modelAndView.addObject("total", total);
		
		modelAndView.setViewName("factura-de-venta-articulos");
		
		//IMPORTANTE
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//System.out.println(auth.getName());

		return modelAndView;
	}
	
	@RequestMapping(value = "/manetenerInformacion", method = RequestMethod.GET)
	@ResponseBody
	public void mantenerInformacion(String code, String name, int nit, String date, String dateEx){
		 this.code = code;
		 this.name_customer = name;
		 this.idCustomer = nit;
		 this.date = LocalDate.parse(date);
		 this.dateEx = LocalDate.parse(dateEx);

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
	
	@RequestMapping(value = "/insertarImpuesto", method = RequestMethod.GET)
	@ResponseBody
	public Tax insertarImpuesto(String name){
	 
		   Tax tax = null;
		   if(!name.isEmpty()){
			  tax = taxService.findByName(name);
			  if(tax != null){
				 taxes.add(tax);
				 return tax;
				
			  }	   
		   }

		   return tax;
		 
	}

	@RequestMapping(value = "/eliminarImpuesto", method = RequestMethod.GET)
	@ResponseBody
	public void eliminarImpuesto(String name){
	 
		   if(!name.isEmpty() && !taxes.isEmpty()){
			  for (int i = 0; i < taxes.size(); i++) {
				   if(taxes.get(i).getName().equals(name)){
					  taxes.remove(i);
				   }
			  }
					   
		   }
		 
	}

	@RequestMapping(value = "/obtenerImpuestos", method = RequestMethod.GET)
	@ResponseBody
	public List<Tax> obtenerImpuestos(){
          return taxes;
	}

	public void guardarImpuestos(List<Tax> taxes){

		   List<Tax> taxesA = new ArrayList<Tax>();
		   if(!taxes.isEmpty()){
				for (Tax tax : taxes) {
					  Tax newTax = new Tax();
					  newTax.setName(tax.getName());
					  newTax.setValue(tax.getValue());
					  taxesA.add(newTax);
				}
				articlesTaxes.add(taxesA);
				System.out.println(" puzAAAAAAAAAAAAAAAAAAAeba" +" " + articlesTaxes.size());
			}
		   
		   
	}

	@RequestMapping(value = "/agregarArticulo", method = RequestMethod.POST)
	public ModelAndView agregarArticuloFactura(long idArticle, ArticleSale articleSale){
		ModelAndView modelAndView = new ModelAndView();  
		Article article = articleService.findById(idArticle);
		
		if(article != null) {
		   articleSale.setArticle(article);
		}

		articlesSaleBill.add(articleSale);

		guardarImpuestos(taxes);
		double total = articleSale.getQuantity()*article.getSalePrice();
		total = total * ( 1 - (articleSale.getDiscount()/100));
		float totalTax = 0;
		for (Tax tax : taxes) {
			 totalTax += total * (tax.getValue()/100);
		}
        articleSale.setTax(totalTax);
		taxes.clear();
		this.total += articleSale.getTotal();
		System.out.println();
		System.out.println();
		System.out.println("LLEGUEEEE" + articleSale.getQuantity() + " pueba" +" ");
		modelAndView.setViewName("redirect:/factura-de-venta-articulos");
		return modelAndView;
	}

	@RequestMapping(value = "/obtenerArticulo", method = RequestMethod.GET)
	@ResponseBody
	public ArticleSale obtenerArticuloFactura(int index){
		
		ArticleSale articleSale = null;

    	System.out.println();
		System.out.println();
		System.out.println("LLEGUEEEE EDIT "+index);
		change = index;
		System.out.println("VAMOS A EDITAR ESTAN MONDA "+ taxes.size());
		if(index >= 0) {
		   return articlesSaleBill.get(index);
		}
        
		return articleSale;
	}

	@RequestMapping(value = "/obtenerImpuestosArticulo", method = RequestMethod.GET)
	@ResponseBody
	public List<Tax> obtenerImpuestosArticulo(int index){
		
		List<Tax> taxes = null;

    	System.out.println();
		System.out.println();
		System.out.println("LLEGUEEEE EDIT IMPUESTO "+index);
        change = index;
		if(index >= 0 && !articlesTaxes.isEmpty()) {
		   return articlesTaxes.get(index);
		}
        
		return taxes;
	}

	@RequestMapping(value = "/insertarImpuestoArticulo", method = RequestMethod.GET)
	@ResponseBody
	public Tax insertarImpuestoArticulo(int index,String name){
		   
		   System.out.println("AQUIIIIIIIIIIIIII TOY TAX ADD EDIT" + name);
		   Tax tax = taxService.findByName(name);
		   if(!name.isEmpty() && !articlesTaxes.isEmpty() && tax!=null){
			  System.out.println("AQUIIIIIIIIIIIIII TOY AÑADIENDO TAX EN EL EDITAR PROBANDO :)");
			  articlesTaxes.get(index).add(tax);
			  return tax;
		   
			  }
			return tax;
	}
	
	@RequestMapping(value = "/eliminarImpuestoArticulo", method = RequestMethod.GET)
	@ResponseBody
	public void eliminarImpuestoArticulo(int index,String name){
		System.out.println("AQUIIIIIIIIIIIIII TOY " + articlesTaxes.get(index).get(0) + name);
		   if(!name.isEmpty() && !articlesTaxes.isEmpty()){
			  System.out.println("AQUIIIIIIIIIIIIII TOY PROBANDO :)");
			  for (int i = 0; i < articlesTaxes.get(index).size(); i++) {
			   	   System.out.println("AQUIIIIIIIIIIIIII TOY :)");
				   if(articlesTaxes.get(index).get(i).getName().equals(name)){
					  articlesTaxes.get(index).remove(i);
					  System.out.println("AQUIIIIIIIIIIIIII TOY :)");
					  return;
				   }
			  }
					   
		   }
		 
	}

	@RequestMapping(value = "/editarArticulo", method = RequestMethod.POST)
	public ModelAndView editarArticuloFactura(int idArticle, ArticleSale articleSale){
		ModelAndView modelAndView = new ModelAndView();  
		Article article = articleService.findAll().get(idArticle);

		ArticleSale articleList = null;

		if(articleSale != null && idArticle > 0){
			if(article != null) {
			   articleSale.setArticle(article);
			}
			
			articleList = articlesSaleBill.get(change);

			System.out.println();
			System.out.println();
			System.out.println("LLEGUEEEE A EDITAR LA FACTYRAAA EN SERIO ENTR´" +change + articleSale.getTotal());

			articleList.setArticle(article);
			articleList.setQuantity(articleSale.getQuantity());
			articleList.setDiscount(articleSale.getDiscount());
			articleList.setTax(articleSale.getTax());
			articleList.setTotal(articleSale.getTotal());
	    }
		
		
		
		System.out.println();
		System.out.println();
		System.out.println("LLEGUEEEE A EDITAR LA FACTURA");
		modelAndView.setViewName("redirect:/factura-de-venta-articulos");
		
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
		   articlesSaleBill.remove(index);
		}

		modelAndView.setViewName("redirect:/factura-de-venta-articulos");
		
		return modelAndView;
	}

	@RequestMapping(value = "/crearFacturaArticulos", method = RequestMethod.POST)
	public ModelAndView crearFacturaArticulo(ArticleSaleBill saleBill, String dateSaleBill, String dateExpiration) {
		ModelAndView modelAndView = new ModelAndView();

	    if(saleBill != null && !dateSaleBill.isEmpty()){	
			LocalDate date = LocalDate.parse(dateSaleBill);
			saleBill.setDate(date);
		
			if(!dateExpiration.isEmpty()){
				LocalDate expiration = LocalDate.parse(dateExpiration);
				saleBill.setExpiration(expiration);
			}

			if(!articlesSaleBill.isEmpty()){
			   saleBill.setArticles(articlesSaleBill);
			}

			articleSaleBillService.create(saleBill);
			articlesSaleBill.clear();
			articlesTaxes.clear();
		}

		this.code = "";
		this.name_customer = "";
		this.idCustomer = null;
		this.date = null;
		this.dateEx = null;
		this.total = 0.0;

        
		modelAndView.setViewName("redirect:/factura-de-venta-articulos");

		return modelAndView;
	}
	

}