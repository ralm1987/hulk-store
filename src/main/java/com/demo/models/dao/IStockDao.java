package com.demo.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.models.entity.Stock;

public interface IStockDao extends CrudRepository<Stock, Long>{
	
	@Query(value = "SELECT * FROM stock s WHERE s.producto_id = ?", nativeQuery = true)
	public Stock productStock(Long producto_id);

}
