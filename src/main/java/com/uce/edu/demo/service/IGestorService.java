package com.uce.edu.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.ProductoSencillo;

public interface IGestorService {

	
	void realizarVenta(String codigoDeBarras, Integer cantidad, String cedulaCliente, String numeroDeVenta);
	void reporteVentas(LocalDateTime fechaVenta, String categoriaProducto, Integer cantidad);
	public List<ProductoSencillo> consultarStock(String codigoBarras, String Nombre, String categoria, Integer stock);
}

	

