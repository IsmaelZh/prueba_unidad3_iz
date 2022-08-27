package com.uce.edu.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IDetalleVentaRepository;
import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.IVentaRepository;
import com.uce.edu.demo.repository.modelo.DetalleVenta;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.repository.modelo.Venta;

@Service
public class GestorServiceImpl implements IGestorService{

	private static Logger LOG = Logger.getLogger(GestorServiceImpl.class);
	
	@Autowired
	private IProductoRepository productoRepository;
	
	@Autowired
	private IVentaRepository ventaRepository;
	
	@Autowired
	private IDetalleVentaRepository detalleVentaRepository;
	
	@Override
	public void realizarVenta(String codigoDeBarras, Integer cantidad, String cedulaCliente, String numeroDeVenta) {
		// TODO Auto-generated method stub
		
		try {
			Producto venta = this.productoRepository.buscarCodigoBarras(codigoDeBarras);
			if (venta == null || venta.getStock() == 0) {
				throw new RuntimeException("El stock es insuficiente o el producto no existe");
			} else {
				Venta vent = new Venta();
				vent.setNumero(cantidad);
				vent.setFecha(LocalDateTime.now());
				vent.setCedulaCliente(cedulaCliente);
				vent.setTotalVenta(venta.getPrecio().multiply(new BigDecimal((cantidad <= venta.getStock()) ? cantidad : venta.getStock())));
				
				DetalleVenta dv = new DetalleVenta();
				dv.setCantidad(cantidad);
				dv.setPrecioUnitario(venta.getPrecio());
				dv.setSubtotal(vent.getTotalVenta());
				dv.setVenta(vent);
				dv.setProducto(venta);
				
				vent.getDetalles().add(dv);
				this.ventaRepository.insertarVenta(vent);
		
				this.detalleVentaRepository.insertar(dv);
			}
		} catch (Exception e) {
			// TODO: handle exception
			LOG.error("No se pudo realizar la venta");
		}
		
	}

	@Override
	public void reporteVentas(LocalDateTime fechaVenta, String categoriaProducto, Integer cantidad) {
		// TODO Auto-generated method stub
		List<Venta> listaVentas = this.ventaRepository.buscarVentaPorFecha(fechaVenta);
		listaVentas.stream().forEach(v -> {
			LOG.info(v.getDetalles());
		});
	}

	@Override
	public List<ProductoSencillo> consultarStock(String codigoBarras, String Nombre, String categoria, Integer stock) {
		// TODO Auto-generated method stub
		return this.productoRepository.consultarStock(codigoBarras, Nombre, categoria, stock);
	}
	
}
