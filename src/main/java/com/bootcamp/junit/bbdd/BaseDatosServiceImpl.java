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
	
	
	public Integer insertarArticulo(Integer id,Articulo articulo) {
		Integer retorno = null;
		if(!storage.containsKey(id)) {
			storage.put(id, articulo);
		}else {
			System.out.println("Id ya en uso. Seleccione otro.");
		}
		for (Integer clave : storage.keySet()) {
			if(storage.get(clave).equals(articulo)) {
				retorno = clave;
			}
		}
		return retorno;
	}

	public int lastIndex() {
		return storage.size();
	}

	public Map<Integer, Articulo> getStorage() {
		return storage;
	}

	public void setStorage(Map<Integer, Articulo> storage) {
		this.storage = storage;
	}
	
	
	
}
