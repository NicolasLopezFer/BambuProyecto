package com.panda.bambu.controller;

import java.time.LocalDate;

import com.panda.bambu.service.return_articles.ReturnArticlesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("reporte-devoluciones")
public class ReportReturnArticlesController {
      
    @Autowired
	ReturnArticlesService returnArticlesService;

    @GetMapping()
	@ResponseBody
	public ModelAndView  reporteFactura(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("HOLAAAAAA REPORTEES" );
       LocalDate date = LocalDate.parse(fechaInicio);
       LocalDate dateF = LocalDate.parse(fechaFin);
       returnArticlesService.findByRange(date, dateF);
        modelAndView.addObject("returns",returnArticlesService.findAll());
		modelAndView.setViewName("reporte-devoluciones");
		return modelAndView;          
	}

}