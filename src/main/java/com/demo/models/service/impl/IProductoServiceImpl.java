package com.demo.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.models.dao.IProductoDao;
import com.demo.models.entity.Producto;
import com.demo.models.service.IProductoService;

@Service
public class IProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoDao iProductoDao;

	/*Listado de Productos con disponibilidad en Stock*/
	@Override
	@Transactional(readOnly = true)
	public List<Producto> listProductStock() {
		// TODO Auto-generated method stub
		return (List<Producto>) iProductoDao.listProductStock();
	}

	/*Consulta Producto por ID*/
	@Override
	public Producto findProductoById(Long id) {
		// TODO Auto-generated method stub
		return iProductoDao.findById(id).orElse(null);
	}

	/*Listado de todos los Productos*/
	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return (List<Producto>) iProductoDao.findAll();
	}
	
	/*Guardar Producto*/
	@Override
	public void save(Producto producto) {
		// TODO Auto-generated method stub
		iProductoDao.save(producto);
	}

	/*Borrar Producto por ID*/
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		iProductoDao.deleteById(id);
	}

	/*Listado paginado de Productos activos*/
	@Override
	public Page<Producto> productosActivo(Pageable pageable) {
		// TODO Auto-generated method stub
		return iProductoDao.productosActivo(pageable);
	}

}
