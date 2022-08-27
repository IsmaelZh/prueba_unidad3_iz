package com.uce.edu.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.uce.edu.demo.repository.modelo.Venta;

public interface IVentaRepository {

	void insertarVenta(Venta venta);
	List<Venta> buscarVentaPorFecha(LocalDateTime fecha);
}
