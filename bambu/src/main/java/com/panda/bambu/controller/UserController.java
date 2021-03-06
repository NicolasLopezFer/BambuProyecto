package com.panda.bambu.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.panda.bambu.model.Role;
import com.panda.bambu.model.RoleRepository;
import com.panda.bambu.model.User;
import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.article.ArticleRepository;
import com.panda.bambu.service.UserService;
import com.panda.bambu.service.article.ArticleService;
import com.panda.bambu.service.company.CompanyService;
import com.panda.bambu.service.return_articles.ReturnArticlesService;
import com.panda.bambu.service.inventory.ArticleInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@Autowired
	ArticleInventoryService articleInventoryService;

	@Autowired
	ArticleService articleService;
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	CompanyService companyService;

	@Autowired
	ReturnArticlesService returnArticlesService;
	
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
		modelAndView.addObject("empresasUsuario",companyService.findAll());
		System.out.println("LAS EMPRESAS" + companyService.findAll());
		modelAndView.setViewName("emprendedor"); // resources/template/emprendedor.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/articulos", method = RequestMethod.GET)
	public ModelAndView articulos(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("articulosEmpresa",articleRepository.findAll());
		modelAndView.addObject("paginaActual",page);
		modelAndView.setViewName("articulos"); // resources/template/articulos.html
		return modelAndView;
	}
	
	@RequestMapping(value = "/articulos-crear", method = RequestMethod.POST)
	public ModelAndView crearArticulo(Article a) {
		ModelAndView modelAndView = new ModelAndView();
		if(articleService.create(a)) {
			modelAndView.addObject("responseMessage", "Articulo guardado Exitosamente!");	
			System.out.println("Articulo guardado Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al guardar el articulo");
			System.out.println("NO SE GUARDO");
		}
		modelAndView.setViewName("redirect:/articulos");
		return modelAndView;
	}
	
	@RequestMapping(value = "/articulos-modificar", method = RequestMethod.POST)
	public ModelAndView modificarArticulo(Article a) {
		ModelAndView modelAndView = new ModelAndView();
		//articleRepository.save(a);
		if(articleService.modify(a)) {
			modelAndView.addObject("responseMessage", "Articulo editado Exitosamente!");	
			System.out.println("Articulo guardado Exitosamente!");
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al editado el articulo");
			System.out.println("NO SE GUARDO");
		}
		modelAndView.setViewName("redirect:/articulos");
		return modelAndView;
	}
	
	@RequestMapping(value = "/articulos-borrar", method = RequestMethod.GET)
	public ModelAndView borrarArticulo(long id) {
		ModelAndView modelAndView = new ModelAndView();
		if(articleService.delete(articleService.findById(id))) 
		{
			modelAndView.addObject("responseMessage", "Articulo borrado Exitosamente!");	
		}else {
			modelAndView.addObject("responseMessage", "Existen errores al guardar el articulo");
		}
		modelAndView.setViewName("redirect:/articulos");
		return modelAndView;
	}
	
	@RequestMapping(value = "/articulos-encontraruno", method = RequestMethod.GET)
	@ResponseBody
	public Optional<Article> encontrarArticulo(long id) {
		return articleRepository.findById(id);
	}
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home"); // resources/template/home.html
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
