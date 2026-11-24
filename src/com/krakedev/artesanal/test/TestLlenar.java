package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestLlenar {

	public static void main(String[] args) {
		 Maquina rubia = new Maquina("P001","Pilsener","La cerveza del Ecuador", 0.02,8000);
		 
		 rubia.imprimir();
		 
		 rubia.llenarMaquina();
		 
		 rubia.imprimir();
		 
		 Maquina negra = new Maquina("C001","Club", "La cerveza mas fina", 0.03);
		 
		 negra.llenarMaquina();
		 negra.imprimir();

	}

}
