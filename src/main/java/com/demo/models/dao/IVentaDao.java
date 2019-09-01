package com.demo.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.models.entity.Venta;

public interface IVentaDao extends CrudRepository<Venta, Long>{
	
	@Query(value = "SELECT * FROM venta v WHERE v.producto_id = ?", nativeQuery = true)
	public Venta productVenta(Long producto_id);

}
