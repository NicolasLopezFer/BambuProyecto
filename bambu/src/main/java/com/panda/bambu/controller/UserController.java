package com.panda.bambu.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.panda.bambu.model.Role;
import com.panda.bambu.model.RoleRepository;
import com.panda.bambu.model.User;
import com.panda.bambu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
	
	/*@RequestMapping({"/login-form.html"})
	public String loginForm(){
		return "./user-form/login-form";
	}

	@RequestMapping({"/register-form.html"})
	public String registerForm(){
		return "./user-form/register-form";
	} */ 
	
	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;
	
	@RequestMapping({"/"})
		public String llegada() {
			return "llegada";
		}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("./user-form/login-form"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		List<Role> rols = roleRepository.findAll();
		rols.remove(0);
		modelAndView.addObject("roles",rols);
		modelAndView.addObject("user", user);
		modelAndView.setViewName("./user-form/register-form"); // resources/template/register.html
		return modelAndView;
	}

	@RequestMapping(value = "/emprendedor", method = RequestMethod.GET)
	public ModelAndView emprendedorHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("emprendedor"); // resources/template/emprendedor.html
		return modelAndView;
	}


	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
		return modelAndView;
	}

	@RequestMapping(value = "/inventario", method = RequestMethod.GET)
	public ModelAndView inventarioHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("inventario"); // resources/template/inventario.html
		return modelAndView;
	}

	@RequestMapping(value = "/entradaInv", method = RequestMethod.GET)
	public ModelAndView entradaInvHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("entradaInv"); // resources/template/entradaInv.html
		return modelAndView;
	}

	@RequestMapping(value = "/salidaInv", method = RequestMethod.GET)
	public ModelAndView salidaInvHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("salidaInv"); // resources/template/entradaInv.html
		return modelAndView;
	}

	@RequestMapping(value = "/kardex", method = RequestMethod.GET)
	public ModelAndView kardexHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("kardex"); // resources/template/kardex.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin"); // resources/template/admin.html
		return modelAndView;
	}

	

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		// Check for the validations
		if(bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Existen errores en el formulario.");
			modelMap.addAttribute("bindingResult", bindingResult);
		}
		else if(userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage", "Error: El usuario ya existe.");			
		}
		// we will save the user if, no binding errors
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Usuario registrado correctamente.");
		}
		modelAndView.addObject("user", new User());
		modelAndView.addObject("roles",roleRepository.findAll());
		modelAndView.setViewName("./user-form/register-form");
		return modelAndView;
	}

}
