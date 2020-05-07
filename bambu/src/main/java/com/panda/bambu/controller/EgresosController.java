package com.panda.bambu.controller;

import java.util.Optional;

import com.panda.bambu.service.egreso.EgresoService;

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
public class EgresosController 

{
    @Autowired
    EgresoService egresoService;

    @RequestMapping(value = "/egreso", method = RequestMethod.GET)
	public ModelAndView egresos(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("egreso", egresoService.findAll());
		modelAndView.setViewName("egreso"); // resources/template/egresos.html
		return modelAndView;
	}

}