package com.panda.bambu.controller;

import com.panda.bambu.model.egreso.Egreso;
import com.panda.bambu.service.egreso.EgresoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReporteEgresoController {
    @Autowired
    EgresoService egresoService;

    @RequestMapping(value = "/reporteEgreso", method = RequestMethod.GET)
	public ModelAndView egresos(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("lista_egresos", egresoService.findAll());
		modelAndView.setViewName("reporteEgreso"); // resources/template/egresos.html
		return modelAndView;
    }

    @RequestMapping(value = "/reporteEgreso-encontrarUno", method = RequestMethod.GET)
	@ResponseBody
	public Egreso encontrarEgreso(long id) 
	{
		return egresoService.findById(id);
	}
    
}