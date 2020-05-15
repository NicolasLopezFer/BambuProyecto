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
public class ServiceBillController {

     @Autowired
	 ServiceFamiEmpresaService serviceFamiEmpresaService;
	 
	 @RequestMapping(value = "/serviFacturaForm", method = RequestMethod.GET)
	 public ModelAndView servicioHome() {
		 final ModelAndView modelAndView = new ModelAndView();
		 final List<ServiceFamiEmpresa> s = serviceFamiEmpresaService.findAll();
		 modelAndView.addObject("service", s);
		 modelAndView.setViewName("serviFacturaForm"); 
		 return modelAndView;
	 }

}