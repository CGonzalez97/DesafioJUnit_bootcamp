package com.bootcamp.junit.services;

import java.util.List;

import com.bootcamp.junit.models.Articulo;

public interface CarritoCompraService {
	
	void limpiarCesta();
	void addArticulo(Articulo a);
	int getNumArticulo();
	List<Articulo> getArticulos();
	Double totalPrice();
	Double calculadorDescuento(double precio, double porcentajeDescuento);
	Double aplicarDescuento(Integer idArticulo, Double porcentaje);
	
}
