package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestServirCervezaAI {

	private static final double DELTA = 0.0001;

	@Test
	public void testServirCervezaConCantidadSuficiente() {
		// Valida que al servir una cantidad menor a la disponible (usando constructor de 5 parametros),
		// se reste la cantidad servida de la cantidad actual y se retorne el valor exacto a pagar.
		Maquina maquina = new Maquina("M01", "IPA", "Cerveza artesanal lupulada", 0.01, 5000);
		maquina.recargarCerveza(2000);

		double totalPagar = maquina.servirCerveza(500);

		assertEquals("M01", maquina.getCodigo());
		assertEquals(5.0, totalPagar, DELTA);
		assertEquals(1500.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	public void testServirCervezaExactaConConstructorPorDefecto() {
		// Valida que al servir exactamente toda la cerveza disponible tras llenarMaquina
		// (10000 - 200 = 9800 ml), la cantidad actual quede en 0 y el cobro sea el esperado.
		Maquina maquina = new Maquina("M02", "Stout", "Cerveza negra tostada", 0.02);
		maquina.llenarMaquina(); // 10000 - 200 = 9800 ml disponibles

		double totalPagar = maquina.servirCerveza(9800);

		assertEquals("M02", maquina.getCodigo());
		assertEquals(196.0, totalPagar, DELTA);
		assertEquals(0.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	public void testServirCervezaSinStockSuficiente() {
		// Valida que si la cantidad solicitada supera la cerveza disponible, no se sirva nada,
		// se retorne 0 y la cantidad actual permanezca sin cambios.
		Maquina maquina = new Maquina("M03", "Amber", "Cerveza ambar suave", 0.015, 3000);
		maquina.recargarCerveza(1000);

		double totalPagar = maquina.servirCerveza(1500);

		assertEquals(0.0, totalPagar, DELTA);
		assertEquals(1000.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	public void testServirCervezaEnMaquinaVacia() {
		// Valida que si la maquina esta vacia (cantidadActual en 0), al intentar servir
		// se retorne 0 y la cantidad actual siga en 0.
		Maquina maquina = new Maquina("M04", "Golden", "Cerveza rubia ligera", 0.01);

		double totalPagar = maquina.servirCerveza(250);

		assertEquals(0.0, totalPagar, DELTA);
		assertEquals(0.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	public void testServirCervezaCantidadCero() {
		// Valida el comportamiento cuando se solicita servir 0 ml: retorna 0 y no altera
		// la cantidad actual disponible.
		Maquina maquina = new Maquina("M05", "Porter", "Cerveza oscura con notas a cafe", 0.02, 4000);
		maquina.recargarCerveza(1200);

		double totalPagar = maquina.servirCerveza(0);

		assertEquals(0.0, totalPagar, DELTA);
		assertEquals(1200.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	public void testServirCervezaVariasVecesConsecutivas() {
		// Valida multiples servicios consecutivos: cada servicio valido descuenta su cantidad,
		// calcula su propio valor y deja el saldo correcto para el siguiente pedido,
		// mientras que un pedido posterior que excede el saldo restante es rechazado.
		Maquina maquina = new Maquina("M06", "Pale Ale", "Cerveza equilibrada", 0.005);
		maquina.recargarCerveza(1000);

		double primerCobro = maquina.servirCerveza(400);
		assertEquals(2.0, primerCobro, DELTA);
		assertEquals(600.0, maquina.getCantidadActual(), DELTA);

		double segundoCobro = maquina.servirCerveza(300);
		assertEquals(1.5, segundoCobro, DELTA);
		assertEquals(300.0, maquina.getCantidadActual(), DELTA);

		double tercerCobro = maquina.servirCerveza(500);
		assertEquals(0.0, tercerCobro, DELTA);
		assertEquals(300.0, maquina.getCantidadActual(), DELTA);
	}
}