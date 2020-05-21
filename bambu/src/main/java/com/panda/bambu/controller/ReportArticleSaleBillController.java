package com.panda.bambu.controller;

import java.time.LocalDate;

import com.panda.bambu.service.sale_bill.ArticleSaleBillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("reporte-factura-articulos")
public class ReportArticleSaleBillController {
    

    @Autowired
	ArticleSaleBillService articleSaleBillService;

    @GetMapping()
	@ResponseBody
	public ModelAndView  reporteFactura(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin){
        ModelAndView modelAndView = new ModelAndView();

        LocalDate date = LocalDate.parse(fechaInicio);
        LocalDate dateF = LocalDate.parse(fechaFin);
        modelAndView.addObject("saleBills", articleSaleBillService.findByRange(date, dateF));
		modelAndView.setViewName("reporte-factura-articulos");
		return modelAndView;          
	}


}