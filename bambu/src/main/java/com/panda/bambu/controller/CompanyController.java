package com.panda.bambu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.panda.bambu.model.company.Company;
import com.panda.bambu.service.company.CompanyService;

@Controller
public class CompanyController {

	@Autowired
	CompanyService companyService;
	
	@RequestMapping(value = "/emprendedor/empresa", method = RequestMethod.GET)
	public ModelAndView empresa(long EmpresaNIT, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		redirectAttributes.addFlashAttribute("empresaElegida", companyService.findByNit(EmpresaNIT));
		modelAndView.setViewName("redirect:/emprendedor"); // resources/template/articulos.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/emprendedor/empresa-crear", method = RequestMethod.POST)
	public ModelAndView crearEmpresa(Company c) {
		ModelAndView modelAndView = new ModelAndView();
		if(companyService.create(c)) {
			modelAndView.addObject("responseMessage", "Empresa guardada Exitosamente!");	
			System.out.println("Empresa guardada Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al guardar la empresa");
			System.out.println("NO SE GUARDO EMPRESA");
		}
		modelAndView.setViewName("redirect:/emprendedor");
		return modelAndView;
	}
	
	@RequestMapping(value = "/emprendedor/empresa-editar", method = RequestMethod.POST)
	public ModelAndView editarEmpresa(Company c) {
		ModelAndView modelAndView = new ModelAndView();
		if(companyService.modify(c)) {
			modelAndView.addObject("responseMessage", "Empresa editada Exitosamente!");	
			System.out.println("Empresa editada Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al guardar la empresa");
			System.out.println("NO SE GUARDO EMPRESA");
		}
		modelAndView.setViewName("redirect:/emprendedor");
		return modelAndView;
	}

}
