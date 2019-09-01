package com.demo.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.demo.util.paginator.PageRender;
import com.demo.models.entity.Producto;
import com.demo.models.service.IProductoService;


@Controller
@SessionAttributes("store")
public class StoreController {
	
	@Autowired
	private IProductoService iProductoService;
	
	/*Controlador para mostrar todos los Productos y su Stock*/
	@RequestMapping(value = {"/listar", "/"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			HttpServletRequest request) {
				
		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Producto> productos = iProductoService.productosActivo(pageRequest);

		PageRender<Producto> pageRender = new PageRender<Producto>("/listar", productos);
		
		model.addAttribute("titulo", "Listado");
		model.addAttribute("productos", productos);
		model.addAttribute("page", pageRender);
		
		return "listar";
		
	}
	
}
