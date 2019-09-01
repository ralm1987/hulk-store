package com.demo.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> listProductStock();
	
	public List<Producto> findAll();
	
	public Producto findProductoById(Long id);
	
	public void save(Producto producto);
	
	public void delete(Long id);
	
	public Page<Producto> productosActivo(Pageable pageable);

}
