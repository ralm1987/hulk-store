package com.demo.models.service;

import java.util.List;

import com.demo.models.entity.Stock;

public interface IStockService {
	
	public void save(Stock stock);
	
	public Stock productStock(Long producto_id);
	
	public List<Stock> findAll();

}
