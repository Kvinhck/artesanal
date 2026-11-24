package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestAtributos {

	public static void main(String[] args) {
		Maquina rubia = new Maquina("P001","Pilsener","cerveza cool",0.02, 100000);
		rubia.imprimir();
		rubia.setNombreCerveza("Golden Ale");
		rubia.setDescripcion("Cerveza con un mejor aroma");
		rubia.imprimir();
	}

}
