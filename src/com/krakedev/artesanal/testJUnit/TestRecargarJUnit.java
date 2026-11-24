package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestRecargarJUnit {
	
	@Test

	public void TestRecargaExitosa() {
		
		Maquina rubia = new Maquina("P001","Pilsener", "cerveza", 0.02, 8000);

		boolean resultado = rubia.recargarCerveza(3000);
		
		assertTrue(resultado);
		assertEquals(3000,rubia.getCantidadActual(), 0.0001);
	}
	
	@Test

	public void TestRecargaFallidaPorDesborde() {
		
		Maquina negra = new Maquina("C001","Club", "cerveza fria", 0.03, 8000);
		
		//negra.recargarCerveza(3000);
		
		boolean resultado = negra.recargarCerveza(3000);
		
		assertTrue(resultado);
		assertEquals(3000,negra.getCantidadActual(), 0.0001);
	}	
}
