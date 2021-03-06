package com.panda.bambu.controller;

import com.panda.bambu.model.article.Article;
import com.panda.bambu.model.sale_bill.ServiceSale;
import com.panda.bambu.model.sale_bill.ServiceSaleBill;
import com.panda.bambu.model.service_famiempresa.ServiceArticle;
import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresa;
import com.panda.bambu.service.sale_bill.ServiceSaleBillService;
import com.panda.bambu.service.sale_bill.ServiceSaleService;
import com.panda.bambu.service.service_famiempresa.ServiceFamiEmpresaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ServiceBillController {

	private List<ServiceSale> serSale = new ArrayList<ServiceSale>();

	private double t = 0;

	@Autowired
	ServiceFamiEmpresaService serviceFamiEmpresaService;

	@Autowired
	ServiceSaleService serSaleService;

	@Autowired
	ServiceSaleBillService serSaleBillService;
	
	List<ServiceSaleBill> facturasServicio=new ArrayList<>();
	
	Boolean filtrar=false;

	@RequestMapping(value = "/serviFacturaForm", method = RequestMethod.GET)
	public ModelAndView servicioHome() {
		final ModelAndView modelAndView = new ModelAndView();
		final List<ServiceSale> s = serSale;
		double total = 0;
		t = 0;
		for (int i = 0; i < s.size(); i++) {
			total = total + s.get(i).getTotalValue();
			t = t + s.get(i).getTotalValue();
		}
		modelAndView.addObject("serviceSale", s);
		modelAndView.addObject("total", total);
		modelAndView.setViewName("serviFacturaForm");
		return modelAndView;
	}

	@RequestMapping(value = "/guardarBill", method = RequestMethod.POST)
	public ModelAndView servicioHomePost(@RequestParam(value = "numFact") String code,
			@RequestParam(value = "nombreCliente") String nombre, @RequestParam(value = "nitcc") String nitcc,
			@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaVenci") String fechaVenci) {
		ModelAndView modelAndView = new ModelAndView();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateFactura = LocalDate.parse(fechaInicio, formatter);
		LocalDate dateVencimiento = LocalDate.parse(fechaVenci, formatter);

		// for(int i = 0; i < serSale.size();i++){
		// serSaleService.create(serSale.get(i));
		// }

		Set<ServiceSale> sett = new HashSet<ServiceSale>(serSale);
		serSaleBillService.create(code, nombre, dateFactura, dateVencimiento, sett, nitcc, t);

		System.out.println("A 2");

		serSale.clear();
		t = 0;
		modelAndView.setViewName("redirect:/serviFacturaForm");
		return modelAndView;
	}

	@RequestMapping(value = "/agregarServicio", method = RequestMethod.POST)
	public ModelAndView serviBill(@RequestParam(value = "code") String code,
			@RequestParam(value = "cantidad") String cantidad, @RequestParam(value = "descuento") String descuento) {
		ModelAndView modelAndView = new ModelAndView();
		ServiceSale ss = new ServiceSale();
		ServiceFamiEmpresa sfe = new ServiceFamiEmpresa();
		sfe = serviceFamiEmpresaService.findByCode(code);
		if (sfe != null) {
			ss.setServiceFamiEmpresa(sfe);
			ss.setDiscount(Float.parseFloat(descuento));
			ss.setQuantity(Float.parseFloat(cantidad));
			ss.setTotalValue(
					serSaleService.calculateTotal(sfe, Integer.parseInt(cantidad), Float.parseFloat(descuento)));
			serSale.add(ss);
		} else {
			modelAndView.addObject("mensaje", "No existe tal servicio");
		}

		modelAndView.setViewName("redirect:/serviFacturaForm");
		return modelAndView;
	}

	@RequestMapping(value = "/borrarServicio", method = RequestMethod.GET)
	public ModelAndView borrarArticulo(final float id) {
		final ModelAndView modelAndView = new ModelAndView();
		for (int i = 0; i < serSale.size(); i++) {
			double code = serSale.get(i).getTotalValue();
			if (code == id) {
				serSale.remove(i);
			}
		}
		modelAndView.setViewName("redirect:/serviFacturaForm");
		return modelAndView;
	}

	@RequestMapping(value = "/irAtras", method = RequestMethod.GET)
	public ModelAndView limpiarLista() {
		final ModelAndView modelAndView = new ModelAndView();
		serSale.clear();
		t = 0;
		modelAndView.setViewName("redirect:/emprendedor");
		return modelAndView;
	}

	@RequestMapping(value = "/filtrarFechasFactura", method = RequestMethod.POST)
	public ModelAndView filtrarFechasFactura(@RequestParam(value = "fechaInicio") String fechaInicio,
			@RequestParam(value = "fechaFin") String fechaFin) {
		return filtrarFechasFacturaServicioPost(fechaInicio, fechaFin);
	}

	@RequestMapping(value = "/filtrarFechasFacturaServicio")
	public ModelAndView filtrarFechasFacturaServicioPost(String fechaInicio, String fechaFin) {
		ModelAndView modelAndView = new ModelAndView();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateInicial = LocalDate.parse(fechaInicio, formatter);
		LocalDate dateFinal = LocalDate.parse(fechaFin, formatter);
		List<ServiceSaleBill> list_facturas_servicio = new ArrayList<>();
		for (ServiceSaleBill s : serSaleBillService.findAll()) {
			for(ServiceSale serv: s.getServices()) {
				System.out.println("Servicios "+serv.getServiceFamiEmpresa().getName());
			}
			if (s.getDate().isAfter(dateInicial) && s.getDate().isBefore(dateFinal))
				list_facturas_servicio.add(s);
		}
		facturasServicio = list_facturas_servicio;
		filtrar = true;
		modelAndView.setViewName("redirect:/reporteFacturasServicio");
		return modelAndView;
	}

	@RequestMapping(value = "/reporteFacturasServicio", method = RequestMethod.GET)
	public ModelAndView reporteFacturas(@RequestParam(defaultValue = "0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("listaFiltrada", facturasServicio);
		modelAndView.setViewName("reporteFacturasServicio"); // resources/template/reporteEgresos.html
		return modelAndView;
	}

	@RequestMapping(value = "/reporteFacturaServicio-encontrarUno", method = RequestMethod.GET)
	@ResponseBody
	public ServiceSaleBill encontrarFacturaServicio(long id) {
		return serSaleBillService.findById(id);
	}
}