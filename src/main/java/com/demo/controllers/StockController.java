package com.demo.controllers;

import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.models.entity.Producto;
import com.demo.models.entity.Stock;
import com.demo.models.service.IProductoService;
import com.demo.models.service.IStockService;

@Controller
@RequestMapping("/stock")
@SessionAttributes("stock")
public class StockController {

	@Autowired
	private IProductoService iProductoService;
	
	@Autowired
	private IStockService iStockService;

	/*Controlador para mostrar vista de crear Stock. GET*/
	@GetMapping("/form/{productoId}")
	public String crear(@PathVariable(value = "productoId") Long productoId, Map<String, Object> model,
			RedirectAttributes flash, Locale locale) {

		Producto producto = iProductoService.findProductoById(productoId);

		if (producto == null) {
			flash.addFlashAttribute("error", "El producto no existe en la base de datos");
			return "redirect:/listar";
		}
		
		Stock stock = new Stock();
		
		if(producto.getStocks().size()!=0) {
			stock.setId(producto.getStocks().get(0).getId());
			stock.setCantidadStock(producto.getStocks().get(0).getCantidadStock());
			stock.setProducto(producto);
			stock.setFechaRegistro(producto.getFechaRegistro());
		}else {
			stock.setProducto(producto);
		}
		
		model.put("stock", stock);
		model.put("titulo", "Agregar Stock");

		return "producto/form_compra";
	}

	/*Controlador para guardar Stock. POST*/
	@PostMapping("/form")
	public String guardar(@Valid Stock stock, BindingResult result, Model model,
			RedirectAttributes flash,
			SessionStatus status, Locale locale) {
		
		System.out.println(stock.getCantidadComprada()+" -- "+stock.getPrecioCompra());

		if (result.hasErrors()) {
			return "redirect:/listar";
		}
		
		if(stock.getCantidadComprada()>0 && stock.getPrecioCompra()!=null) {
			
			stock.setCantidadStock(stock.getCantidadStock()+stock.getCantidadComprada());
			stock.setPrecioUnidad((stock.getPrecioCompra()+(stock.getPrecioCompra()/100)*30));
			iStockService.save(stock);
			status.setComplete();

			flash.addFlashAttribute("success", "Registro éxitoso");
			
		}else {
			flash.addFlashAttribute("error", "Todos los campos son requeridos");
			return "redirect:/listar";
		}
		
		return "redirect:/listar";
	}

}
