package com.demo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.models.entity.Producto;
import com.demo.models.entity.Stock;
import com.demo.models.entity.Venta;
import com.demo.models.service.impl.IProductoServiceImpl;
import com.demo.models.service.impl.IStockServiceImpl;
import com.demo.models.service.impl.IVentaServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class SpringBootDataJpaApplicationTests {
	
	@Autowired
	IProductoServiceImpl iProductoServiceImpl;
	
	@Autowired
	IStockServiceImpl iStockServiceImpl;
	
	@Autowired
	IVentaServiceImpl iVentaServiceImpl;
	
	@Test
	public void contextLoads() {
				
	}
	
	/***Test Producto Services***/
	//@Test
	public void listProductStockTest() {
		
		assertEquals(11, iProductoServiceImpl.listProductStock().size());
		
	}
	
	
	//@Test
	public void findProductoByIdTest() throws ParseException {
						
		assertNotNull(iProductoServiceImpl.findProductoById(1L));

	}	
	
	@Test
	public void saveProductoTest() throws ParseException {
		
		Producto producto = new Producto();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaRegistro = format.parse("2019-09-01");
		
		producto.setActivo(1);
		producto.setDescripcion("Comic");
		producto.setFechaRegistro(fechaRegistro);
		producto.setNombre("Hulk");
		
		iProductoServiceImpl.save(producto);
								
		assertEquals(13, iProductoServiceImpl.findAll().size());

	}
	
	@Test
	public void findAllProductosTest() {
				
		assertEquals(12, iProductoServiceImpl.findAll().size());
		
	}
	
	/*Test Stock Services*/
	@Test
	public void saveStockTest() throws ParseException {
		
		Producto producto = new Producto();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaRegistro = format.parse("2019-09-01");
				
		producto.setActivo(1);
		producto.setDescripcion("Comic");
		producto.setFechaRegistro(fechaRegistro);
		producto.setId(1L);
		producto.setNombre("El Increible Hulk");
		
		Stock stock = new Stock();
		stock.setCantidadComprada(20);
		stock.setCantidadStock(10);
		stock.setFechaRegistro(fechaRegistro);
		stock.setPrecioCompra(100.00);
		stock.setPrecioUnidad(130.00);
		stock.setProducto(producto);

		
		
		iStockServiceImpl.save(stock);
								
		assertEquals(13, iStockServiceImpl.findAll().size());

	}
	
	@Test
	public void findAllStockTest() {
				
		assertEquals(12, iStockServiceImpl.findAll().size());
		
	}
	
	@Test
	public void productStockTest() throws ParseException {
		
		assertNotNull(iStockServiceImpl.productStock(6L));
		
	}
	
	/*Test Venta Services*/
	@Test
	public void saveVentaTest() throws ParseException {
		
		Producto producto = new Producto();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaRegistro = format.parse("2019-09-01");
				
		producto.setActivo(1);
		producto.setDescripcion("Comic");
		producto.setFechaRegistro(fechaRegistro);
		producto.setId(1L);
		producto.setNombre("El Increible Hulk");
		
		Venta venta = new Venta();
		venta.setCantidad(10);
		venta.setTotal(1300.00);
		venta.setProducto(producto);
		venta.setFechaRegistro(fechaRegistro);
		iVentaServiceImpl.save(venta);
										
		assertEquals(2, iVentaServiceImpl.findAll().size());

	}
	
	@Test
	public void findAllVentaTest() {
				
		assertEquals(1, iVentaServiceImpl.findAll().size());
		
	}
	
	@Test
	public void productVentaTest() throws ParseException {
		
		Producto producto = new Producto();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date fechaRegistro = format.parse("2019-09-01");
				
		producto.setActivo(1);
		producto.setDescripcion("Comic");
		producto.setFechaRegistro(fechaRegistro);
		producto.setId(1L);
		producto.setNombre("El Increible Hulk");
		
		Venta venta = new Venta();
		venta.setCantidad(10);
		venta.setTotal(1300.00);
		venta.setProducto(producto);
		venta.setFechaRegistro(fechaRegistro);
		iVentaServiceImpl.save(venta);
		
		assertNotNull(iVentaServiceImpl.productVenta(1L));
		
	}
}
