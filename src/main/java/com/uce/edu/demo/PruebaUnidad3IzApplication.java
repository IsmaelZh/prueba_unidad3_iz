package com.uce.edu.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.service.GestorServiceImpl;
import com.uce.edu.demo.service.IGestorService;
import com.uce.edu.demo.service.IProductoService;

@SpringBootApplication
public class PruebaUnidad3IzApplication implements CommandLineRunner {

	private static Logger LOG = Logger.getLogger(PruebaUnidad3IzApplication.class);

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IGestorService gestorService;

	public static void main(String[] args) {
		SpringApplication.run(PruebaUnidad3IzApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		// a)
		Producto producto = new Producto();
		producto.setCodigoBarras("0-001");
		producto.setNombre("CocaCola");
		producto.setCategoria("Bebidas");
		producto.setStock(50);
		producto.setPrecio(new BigDecimal("1.5"));
		// this.productoService.ingresarProducto(producto);

		// b)
		this.gestorService.realizarVenta("0-001", 1, "1718496944", "24");


		// c)
		Producto productoEncontrado = this.productoService.buscarCodigoBarras(producto.getCodigoBarras());
		LOG.info(productoEncontrado);

		List<ProductoSencillo> listaProductos = this.gestorService.consultarStock(producto.getCodigoBarras(),
				producto.getNombre(), producto.getCategoria(), producto.getStock());
		for (ProductoSencillo i : listaProductos) {
			LOG.info("Producto " + i);
		}

	}

}
