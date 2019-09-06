package test.logic;

import static org.junit.Assert.*;
import model.logic.MVCModelo;

import org.junit.Before;
import org.junit.Test;

public class TestMVCModelo {
	
	private MVCModelo modelo;
	private static int CAPACIDAD=100;
	
	@Before
	public void setUp1() {
		modelo= new MVCModelo(CAPACIDAD);
	}

	public void setUp2() {
		for(int i =0; i< CAPACIDAD;i++){
			modelo.agregar(""+i);
		}
	}

	@Test
	public void testMVCModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.darTamano());  // Modelo con 0 elementos presentes.
	}

	@Test
	public void testDarTamano() {
		assertEquals(0, modelo.darTamano());  // Modelo con 0 elementos presentes.
	}

	@Test
	public void testAgregar() {
<<<<<<< HEAD
		modelo.agregar("holi");
		System.out.println(modelo.darTamano());
		assertEquals("esto no deberia pasar", ""+101, ""+ modelo.darTamano());
		// DONE Completar la prueba
=======
		// TODO Completar la prueba
>>>>>>> 0aa8aa2a0dcb8ef0a26b1bf9254318a971fe539c
		
	}

	@Test
	public void testBuscar() {
		setUp2();
<<<<<<< HEAD
		assertEquals(""+0, modelo.buscar(""+0));
		// DONE Completar la prueba
=======
		// TODO Completar la prueba
>>>>>>> 0aa8aa2a0dcb8ef0a26b1bf9254318a971fe539c
	}

	@Test
	public void testEliminar() {
		setUp2();
<<<<<<< HEAD
		assertEquals(""+1, modelo.buscar(""+1));
		// DONE Completar la prueba
=======
		// TODO Completar la prueba
>>>>>>> 0aa8aa2a0dcb8ef0a26b1bf9254318a971fe539c
		
	}

}
