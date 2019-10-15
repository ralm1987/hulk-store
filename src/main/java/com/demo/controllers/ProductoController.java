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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.models.entity.Producto;
import com.demo.models.entity.Venta;
import com.demo.models.service.IProductoService;
import com.demo.models.service.IVentaService;


@Controller
@SessionAttributes("producto")
public class ProductoController {
	
	@Autowired
	private IProductoService iProductoService;
	
	@Autowired
	private IVentaService iVentaService;
		
	/*Controlador para mostrar vista de crear Producto. GET*/
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model, Locale locale) {

		Producto producto = new Producto();
		model.put("producto", producto);
		model.put("titulo", "Agregar Producto");
		return "form";
	}
	
	/*Controlador para guardar Producto. POST*/
	@PostMapping("/form")
	public String guardar(@Valid Producto producto, BindingResult result, Model model,
			RedirectAttributes flash,
			SessionStatus status, Locale locale) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Agregar Producto");
			return "/form";
		}
		
		if(!producto.getNombre().equals("") && !producto.getDescripcion().equals("")) {
			producto.setActivo(1);
			producto.getStock().setCantidadStock(producto.getStock().getCantidadComprada());
			producto.getStock().setPrecioUnidad((producto.getStock().getPrecioCompra()+(producto.getStock().getPrecioCompra()/100)*30));
			producto.getStock().setProducto(producto);
			iProductoService.save(producto);
			status.setComplete();

			flash.addFlashAttribute("success", "Registro éxitoso");
		}else {
			model.addAttribute("titulo", "Agregar Producto");						
			return "/form";
		}
		

		return "redirect:/listar";
	}
	
	/*Controlador para eliminar Producto. GET*/
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash, Locale locale) {
		
		Producto producto = iProductoService.findProductoById(id);
		
		if (producto.getId() > 0) {
			
			Venta venta = iVentaService.productVenta(id);
			
			if(venta==null) {
				iProductoService.delete(id);
				flash.addFlashAttribute("success", "Producto borrado");
			}else {
				producto.setActivo(0);
				iProductoService.save(producto);
				flash.addFlashAttribute("success", "Producto borrado");
			}
			
			
		}
		return "redirect:/listar";
	}
	
	@GetMapping(value = "/allProduct", produces = { "application/json" })
	public @ResponseBody  List<Producto> allProduct(){
		return iProductoService.findAll();
	}

}
