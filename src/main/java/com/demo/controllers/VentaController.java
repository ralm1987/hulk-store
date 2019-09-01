package com.demo.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.models.entity.Producto;
import com.demo.models.entity.Stock;
import com.demo.models.entity.Venta;
import com.demo.models.service.IProductoService;
import com.demo.models.service.IStockService;
import com.demo.models.service.IVentaService;

@Controller
@RequestMapping("/venta")
@SessionAttributes("venta")
public class VentaController {

	@Autowired
	private IProductoService iProductoService;
	
	@Autowired
	private IVentaService iVentaService;
	
	@Autowired
	private IStockService iStockService;

	/*Controlador para mostrar vista de crear Venta. GET*/
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model, Locale locale) {

		Stock venta = new Stock();
		
		model.put("venta", venta);
		model.put("titulo", "Ventas");

		return "producto/form";
	}
	
	/*Controlador para guardar Venta. POST*/
	@PostMapping("/form")
	public String guardar(@Valid Stock ventas, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status, Locale locale) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Guardar Venta");
			return "producto/form";
		}

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", "Guardar Venta");
			model.addAttribute("error", "Error debe tener productos");
			return "producto/form";
		}
		
		for (int i = 0; i < itemId.length; i++) {

			Stock stock = iStockService.productStock(itemId[i]);

			Venta venta = new Venta();
			venta.setCantidad(cantidad[i]);
			venta.setTotal(stock.getPrecioUnidad()*cantidad[i]);
			venta.setProducto(stock.getProducto());
			iVentaService.save(venta);
			stock.setCantidadStock(stock.getCantidadStock()-cantidad[i]);
			iStockService.save(stock);
			
		}

		
		status.setComplete();

		flash.addFlashAttribute("success", "Registro éxitoso");

		return "redirect:/venta/form";
	}
	
	/*Controlador Rest para mostrar todos los Productos con Stock. GET*/
	@GetMapping(value = "/cargar-productos", produces = { "application/json" })
	public @ResponseBody List<Producto> cargarProductos() {
		return iProductoService.listProductStock();
	}

}
