package com.demo.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.demo.models.entity.Producto;

public interface IProductoDao extends PagingAndSortingRepository<Producto, Long>{
	
	@Query(value = "SELECT * FROM producto p INNER JOIN stock s ON p.id = s.producto_id AND s.cantidad_stock > 0 AND p.activo = 1 AND p.nombre like %?1%", nativeQuery = true)
	public List<Producto> listProductStock(String term);
	
	@Query(value = "SELECT * FROM producto p WHERE p.activo = 1", nativeQuery = true)
	public Page<Producto> productosActivo(Pageable pageable);

}
