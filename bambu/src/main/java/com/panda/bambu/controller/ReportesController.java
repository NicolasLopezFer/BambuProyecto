package com.panda.bambu.controller;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import com.panda.bambu.model.Role;
import com.panda.bambu.model.RoleRepository;
import com.panda.bambu.model.User;
import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.article.ArticleRepository;
import com.panda.bambu.model.inventory.ArticleInventory;
import com.panda.bambu.model.inventory.Inventory;
import com.panda.bambu.model.inventory.InventoryRepository;
import com.panda.bambu.model.inventory.Output;
import com.panda.bambu.model.inventory.Entry;
import com.panda.bambu.model.return_articles.ArticleReturn;
import com.panda.bambu.model.return_articles.ReturnArticles;
import com.panda.bambu.service.UserService;
import com.panda.bambu.service.article.ArticleService;
import com.panda.bambu.service.company.CompanyService;
import com.panda.bambu.service.return_articles.ArticleReturnService;
import com.panda.bambu.service.return_articles.ReturnArticlesService;
import com.panda.bambu.service.inventory.ArticleInventoryService;
import com.panda.bambu.service.inventory.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ReportesController 
{

    @RequestMapping(value = "/reportes", method = RequestMethod.GET)
    public ModelAndView reportes()
     {
        ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("reportes"); // resources/template/egresos.html
		return modelAndView;
    }

    
    @RequestMapping(value = "/formularioReportes", method = RequestMethod.POST)
    public RedirectView reportes(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin, 
                                                @RequestParam(value = "tipoReporte") String tipoReporte)
     {
        
        RedirectView rv=new RedirectView();

        if (tipoReporte.equals("egreso"))
            rv = new RedirectView("filtrarFechasEgreso",true);
        else if(tipoReporte.equals("reciboCaja"))
            rv = new RedirectView("filtrarFechasReciboCaja",true);
        else if(tipoReporte.equals("facturaServicio"))
            rv = new RedirectView("filtrarFechasFacturaServicio",true);
        else if(tipoReporte.equals("devolucion"))
            rv = new RedirectView("reporte-devoluciones",true); 
        else if(tipoReporte.equals("ingreso"))
            rv = new RedirectView("filtrarAFechasRecibo",true);
        else if(tipoReporte.equals("ingreYegre"))
            rv = new RedirectView("filtrarAFechasRecibo",true);
        else if(tipoReporte.equals("servicios"))
            rv = new RedirectView("filtrarFechasReporteServicio",true);
        else if(tipoReporte.equals("facturaArticulo"))
            rv = new RedirectView("reporte-factura-articulos",true);
        else if(tipoReporte.equals("inventario"))
            rv = new RedirectView("filtrarAFechasRecibo",true);
        rv.addStaticAttribute("fechaInicio",fechaInicio);
        rv.addStaticAttribute("fechaFin",fechaFin);
            
		return rv;
    }
    
}