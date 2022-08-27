package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.service.GestorServiceImpl;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.persist(producto);
	}

	@Override
	public Producto buscarCodigoBarras(String codigoBarras) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Producto> myTypedQuery = this.entityManager
					.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras", Producto.class);
			myTypedQuery.setParameter("codigoBarras", codigoBarras);
			return myTypedQuery.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.merge(producto);
	}

	@Override
	public List<ProductoSencillo> consultarStock(String codigoBarras, String Nombre, String categoria, Integer stock) {
		// TODO Auto-generated method stub
		TypedQuery<ProductoSencillo> myQuery = this.entityManager.createQuery(
				"SELECT NEW com.uce.edu.demo.repository.modelo.ProductoSencillo(p.codigoBarras, p.nombre, p.categoria, p.stock) FROM Producto p WHERE p.codigoBarras = :codigoBarras",
				ProductoSencillo.class);
		myQuery.setParameter("codigoBarras", codigoBarras);
		return myQuery.getResultList();
	}



//	public List<ProductoSencillo> consultarStock(String codigoBarras, String Nombre, String categoria, Integer stock) {
//		// TODO Auto-generated method stub
//		Query myQuery = this.entityManager.createNativeQuery(
//				"SELECT NEW com.uce.edu.demo.repository.modelo.ProductoSencillo(p.codigoBarras, p.nombre, p.categoria, p.stock) FROM  producto WHERE prod_codigo_barras = :codigoBarras",
//				ProductoSencillo.class);
//		myQuery.setParameter("codigoBarras", codigoBarras);
//		return (List<ProductoSencillo>) myQuery.getResultList();
//	}

}
