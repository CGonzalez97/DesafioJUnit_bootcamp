package com.bootcamp.junit.bbdd;

import com.bootcamp.junit.models.Articulo;

public interface BaseDatosService {
	
	public void inicializarDB();
	
	public Articulo findArticuloById(int id);
	
	public Articulo insertarArticulo(Articulo articulo);
		
}
