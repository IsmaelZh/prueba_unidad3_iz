package com.uce.edu.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.modelo.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepository productoRepository;

	@Override
	@Transactional()
	public void ingresarProducto(Producto producto){

		Producto prodEncontrado = this.productoRepository.buscarCodigoBarras(producto.getCodigoBarras());
		if (prodEncontrado == null) {
			this.productoRepository.insertar(producto);
		} else {
			prodEncontrado.setStock(prodEncontrado.getStock() + producto.getStock());
			this.productoRepository.actualizar(prodEncontrado);
		}
	}

	@Override
	public Producto buscarCodigoBarras(String codigoBarras) {
		// TODO Auto-generated method stub
		return this.productoRepository.buscarCodigoBarras(codigoBarras);
	}

}
