package com.panda.bambu.controller;

import com.panda.bambu.model.recibo_caja.ReciboCaja;
import com.panda.bambu.model.sale_bill.ServiceSale;
import com.panda.bambu.model.sale_bill.ServiceSaleBill;
import com.panda.bambu.model.service_famiempresa.ServiceFamiEmpresa;
import com.panda.bambu.service.recibo_caja.ReciboCajaService;
import com.panda.bambu.service.sale_bill.ServiceSaleBillService;
import com.panda.bambu.service.service_famiempresa.ServiceFamiEmpresaService;

import org.hibernate.mapping.Set;
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
import java.util.List;

@Controller
public class ReporteServiciosFamiEmpresaController {
    @Autowired
	ServiceFamiEmpresaService serviceFamiEmpresaSer;

	@Autowired
	ServiceSaleBillService serviceSaleBillSer;
	
	List<auxServiReport> serviReport=new ArrayList<auxServiReport>();
	List<auxServiReport> serviReportLlegada = new ArrayList<auxServiReport>();

    @RequestMapping(value = "/reporteServicioFamiEmpresa", method = RequestMethod.GET)
	public ModelAndView servicios(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();

		serviReportLlegada.clear();

		int ubicacionLocal = 0;
		List<ServiceSaleBill> ssb = serviceSaleBillSer.findAll();

		for(int i = 0; i<ssb.size();i++){
			auxServiReport asr = new auxServiReport();;
			List<ServiceSale> ss = new ArrayList<ServiceSale>(ssb.get(i).getServices());
			boolean existente = false;
			for(int j = 0; j < ss.size();j++){
				for(int k = 0; k < serviReportLlegada.size();k++){
					if(ss.get(j).getServiceFamiEmpresa().getCode() == serviReportLlegada.get(k).getCode()){
						existente = true;
						ubicacionLocal = k;
						System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
					}
				}
				if(existente == true){
					double cantSol = serviReportLlegada.get(ubicacionLocal).getCantSolicitudes();
					double tot = serviReportLlegada.get(ubicacionLocal).getTotal();
					String c = serviReportLlegada.get(ubicacionLocal).getCode();
					String n = serviReportLlegada.get(ubicacionLocal).getNombre();

					serviReportLlegada.remove(ubicacionLocal);

					asr.setCode(c);
					asr.setNombre(n);
					asr.setCantSolicitudes(cantSol + ss.get(j).getQuantity());
					asr.setTotal(tot + ss.get(j).getTotalValue());

					serviReportLlegada.add(asr);

				}else{
					asr.setCode(ss.get(j).getServiceFamiEmpresa().getCode());
					asr.setNombre(ss.get(j).getServiceFamiEmpresa().getName());
					asr.setCantSolicitudes(ss.get(j).getQuantity());
					asr.setTotal(ss.get(j).getTotalValue());
					serviReportLlegada.add(asr);
				}
			}
		}	
		modelAndView.addObject("lista_servicios", serviReportLlegada);		
		modelAndView.setViewName("reporteServicioFamiEmpresa"); // resources/template/reporteReciboCaja.html
		
		return modelAndView;
	}
	
	// @RequestMapping(value = "/filtrarFechasRecibo", method = RequestMethod.POST)
	// public ModelAndView filtrarFechasRecibo(@RequestParam(value = "fechaInicio") String fechaInicio, @RequestParam(value = "fechaFin") String fechaFin) 
	// {
	// 	ModelAndView modelAndView = new ModelAndView();
	// 	DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
	// 	LocalDate dateInicial=LocalDate.parse(fechaInicio,formatter);
	// 	LocalDate dateFinal=LocalDate.parse(fechaFin,formatter);
	// 	List<ReciboCaja> list_recibos=new ArrayList<>();
	// 	for(ReciboCaja r: serviceFamiEmpresaSer.findAll()){
	// 		if(r.getFecha().isAfter(dateInicial) && r.getFecha().isBefore(dateFinal))
	// 			list_recibos.add(r);
	// 	}
	// 	recibos=list_recibos;
	// 	modelAndView.setViewName("redirect:/reporteRecibosCaja");
	// 	return modelAndView;
	// }
	
	@RequestMapping(value = "/reporteServiciosFamiEmpresa", method = RequestMethod.GET)
	public ModelAndView reporteRecibosCaja(@RequestParam(defaultValue="0") int page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("lista_servicios", servicios);
		modelAndView.setViewName("reporteServiciosFamiEmpresa"); // resources/template/reporteReciboCajas.html
		return modelAndView;
	}

    // @RequestMapping(value = "/reporteReciboCaja-encontrarUno", method = RequestMethod.GET)
	// @ResponseBody
	// public ReciboCaja encontrarReciboCaja(long id) 
	// {
	// 	return serviceFamiEmpresaSer.findById(id);
	// }
    
}