package com.demo.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.dao.IStockDao;
import com.demo.models.entity.Stock;
import com.demo.models.service.IStockService;

@Service
public class IStockServiceImpl implements IStockService{
	
	@Autowired
	private IStockDao iStockDao;

	/*Guardar Stock*/
	@Override
	public void save(Stock stock) {
		// TODO Auto-generated method stub
		iStockDao.save(stock);
	}

	/*Consulta Stock por ID*/
	@Override
	public Stock productStock(Long producto_id) {
		// TODO Auto-generated method stub
		return iStockDao.productStock(producto_id);
	}

	/*Listado de todo el Stock disponible*/
	@Override
	public List<Stock> findAll() {
		// TODO Auto-generated method stub
		return (List<Stock>) iStockDao.findAll();
	}

	

}
