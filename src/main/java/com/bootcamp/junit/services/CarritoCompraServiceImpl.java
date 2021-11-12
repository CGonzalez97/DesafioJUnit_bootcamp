package com.bootcamp.junit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.junit.bbdd.BaseDatosServiceImpl;
import com.bootcamp.junit.models.Articulo;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraService {
	
	@Autowired
	private BaseDatosServiceImpl baseDatosService;
	
	List<Articulo> listaArticulos = new ArrayList();
	
	@Override
	public void limpiarCesta() {
		listaArticulos.clear();
	}

	@Override
	public void addArticulo(Articulo a) {
		listaArticulos.add(a);
	}

	@Override
	public int getNumArticulo() {
		return listaArticulos.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		return listaArticulos;
	}

	@Override
	public Double totalPrice() {
		Double total = 0.0;
		for (Articulo articulo : listaArticulos) {
			total += articulo.getPrecio();
		}
		return total;
	}

	@Override
	public Double calculadorDescuento(double precio, double porcentajeDescuento) {
		return precio - precio / 100 * porcentajeDescuento;
	}

	@Override
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {
		Articulo articulo = baseDatosService.findArticuloById(idArticulo);
		Double precioNuevo = null;
		if(articulo != null) {
			precioNuevo = calculadorDescuento(articulo.getPrecio(), porcentaje);
		}else {
			System.out.println("No se ha encontrado artículo.");
		}
		return precioNuevo;
	}
	
}
