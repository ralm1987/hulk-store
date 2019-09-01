package com.demo.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.dao.IVentaDao;
import com.demo.models.entity.Venta;
import com.demo.models.service.IVentaService;

@Service
public class IVentaServiceImpl implements IVentaService{
	
	@Autowired
	private IVentaDao iVentaDao;

	/*Guardar Venta*/
	@Override
	public void save(Venta venta) {
		// TODO Auto-generated method stub
		iVentaDao.save(venta);
	}

	/*Listado de todas las Ventas*/
	@Override
	public List<Venta> findAll() {
		// TODO Auto-generated method stub
		return (List<Venta>) iVentaDao.findAll();
	}

	/*Consulta Venta por ID*/
	@Override
	public Venta productVenta(Long producto_id) {
		// TODO Auto-generated method stub
		return iVentaDao.productVenta(producto_id);
	}

	

}
