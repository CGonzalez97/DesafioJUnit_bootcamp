package com.bootcamp.junit.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bootcamp.junit.bbdd.BaseDatosServiceImpl;
import com.bootcamp.junit.models.Articulo;

@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImplTest {
	
	@Mock
	BaseDatosServiceImpl baseDatosService;
	
	@InjectMocks
	private CarritoCompraServiceImpl carritoCompraService = new CarritoCompraServiceImpl();
	
	Articulo art1 = new Articulo("Gorro",14.95);
	Articulo art2 = new Articulo("Gorra",6.99);
	Articulo art3 = new Articulo("Sombrero",24.99);
	List<Articulo> listado = new ArrayList<>();
	
	@BeforeEach
	public void inicializar() {		
		listado.add(art1);
		listado.add(art2);
		listado.add(art3);
		carritoCompraService.addArticulo(art1);
		carritoCompraService.addArticulo(art2);
		carritoCompraService.addArticulo(art3);
	}
	
	@AfterEach
	public void resetear() {
		carritoCompraService.getArticulos().clear();
	}

	@Test
	void testLimpiarCesta() {
		for (Articulo articulo : listado) {
			assertTrue(carritoCompraService.getArticulos().contains(articulo));	
		}
		carritoCompraService.limpiarCesta();
		for (Articulo articulo : listado) {
			assertFalse(carritoCompraService.getArticulos().contains(articulo));
			assertTrue(carritoCompraService.getArticulos().isEmpty());
		}			
	}

	@Test
	void testAddArticulo() {
		Articulo articuloToAdd = new Articulo("Gafas de sol",34.99);
		int tamanyoO = carritoCompraService.listaArticulos.size();
		assertFalse(carritoCompraService.listaArticulos.contains(articuloToAdd));
		carritoCompraService.addArticulo(articuloToAdd);
		assertTrue(carritoCompraService.listaArticulos.contains(articuloToAdd));
		assertEquals(tamanyoO+1,carritoCompraService.listaArticulos.size());
	}

	@Test
	void testGetNumArticulo() {
		assertEquals(listado.size(),carritoCompraService.getArticulos().size());
	}

	@Test
	void testGetArticulos() {
		assertTrue(carritoCompraService.getArticulos().containsAll(listado));
	}

	@Test
	void testTotalPrice() {
		Double total = .0;
		for (Articulo articulo : listado) {
			total += articulo.getPrecio();
		}
		Double centena = 100.0;
		assertEquals(total, carritoCompraService.totalPrice());
	}

	@Test
	void testCalculadorDescuento() {
		assertEquals(95,carritoCompraService.calculadorDescuento(100, 5));
	}

	@Test
	void testAplicarDescuentoSuccess() {
		when(baseDatosService.findArticuloById(anyInt())).thenReturn(art1);
		Double descuento = 5.0;
		Double precioArt = art1.getPrecio();
		Double precioDescuento = precioArt - precioArt / 100 * descuento;
		assertEquals(precioDescuento, carritoCompraService.aplicarDescuento(1, descuento));
	}
	
	@Test
	void testAplicarDescuentoFailure() {
		when(baseDatosService.findArticuloById(anyInt())).thenReturn(null);
		Double descuento = 5.0;
		assertNull(carritoCompraService.aplicarDescuento(2, descuento));
	}
	
	@Test
	void testInsertarEnDbYLista() {
		Integer idEsperado = 10;
		Articulo artAux = new Articulo("Botas",32.99);
		when(baseDatosService.insertarArticulo(anyInt(), any(Articulo.class))).thenReturn(idEsperado);
		when(baseDatosService.findArticuloById(anyInt())).thenReturn(artAux);
		assertEquals(idEsperado, carritoCompraService.insertarEnDbYLista(idEsperado, artAux));
		assertTrue(carritoCompraService.getArticulos().contains(artAux));
		verify(baseDatosService,times(1)).insertarArticulo(anyInt(), any(Articulo.class));
	}

}
