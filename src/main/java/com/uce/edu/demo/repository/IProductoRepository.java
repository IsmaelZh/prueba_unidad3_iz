package com.uce.edu.demo.repository;

import java.util.List;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;

public interface IProductoRepository {

	public void insertar(Producto poducto);
	public void actualizar(Producto producto);
	public Producto buscarCodigoBarras(String codigoBarras);
	public List<ProductoSencillo> consultarStock(String codigoBarras, String Nombre, String categoria, Integer stock);
}
