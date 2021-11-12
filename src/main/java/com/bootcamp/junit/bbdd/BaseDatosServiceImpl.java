package com.bootcamp.junit.bbdd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bootcamp.junit.models.Articulo;

@Service
public class BaseDatosServiceImpl implements BaseDatosService {
	
	private Map<Integer,Articulo> storage;

	@Override
	public void inicializarDB() {		
		storage = new HashMap();
//		storage.put(1, new Articulo("Camiseta",18.95));
//		storage.put(2, new Articulo("Jersey",15.95));
//		storage.put(3, new Articulo("Cinturon",18.95));
//		storage.put(4, new Articulo("Sudadera",18.95));
//		storage.put(5, new Articulo("Pantalón",18.95));
//		storage.put(6, new Articulo("Zapatos",18.95));
//		storage.put(7, new Articulo("Chaqueta",18.95));
//		storage.put(8, new Articulo("Chandal",18.95));
		
		insertarArticulo(new Articulo("Camiseta",18.95));
		insertarArticulo(new Articulo("Jersey",15.95));
		insertarArticulo(new Articulo("Cinturon",18.95));
		insertarArticulo(new Articulo("Sudadera",18.95));
		insertarArticulo(new Articulo("Pantalón",18.95));
		insertarArticulo(new Articulo("Zapatos",18.95));
		insertarArticulo(new Articulo("Chaqueta",18.95));
		insertarArticulo(new Articulo("Chandal",18.95));
	}

	@Override
	public Articulo findArticuloById(int id) {
		return storage.get(id);
	}

	@Override
	public Articulo insertarArticulo(Articulo articulo) {
		storage.put(storage.size()+1, articulo);
		return articulo;
	}

	public int lastIndex() {
		return storage.size();
	}
	
}
