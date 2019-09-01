package com.demo.models.service;

import java.util.List;

import com.demo.models.entity.Venta;

public interface IVentaService {
	
	public List<Venta> findAll();
	
	public void save(Venta venta);
	
	public Venta productVenta(Long producto_id);

}
