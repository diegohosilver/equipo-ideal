package test.equipo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import javafx.util.Pair;
import main.negocio.equipo.Cantidad;
import main.negocio.equipo.Requisitos;
import main.negocio.personas.Roles;

public class RequisitosTest {

	@Test (expected = IllegalArgumentException.class) 
	public void requisitosVaciosTest(){
		Requisitos requisitos = new Requisitos(null, null, null, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void requisitosConParesVaciosTest() {
		Requisitos requisitos = new Requisitos(new Pair<Integer, Integer>(null, null), null, null, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void requisitosConValoresInvertidos() {
		// Los minimos son mayores a los maximos
		Requisitos requisitos = new Requisitos(new Pair<Integer, Integer>(5, 2), null, null, null);
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void requisitosConParesEnCeroTest() {
		Requisitos requisitos = new Requisitos(new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(0, 0), new Pair<Integer, Integer>(0, 0));
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void requisitosConParesNegativosTest() {
		Requisitos requisitos = new Requisitos(new Pair<Integer, Integer>(-1, -1), null, null, null);
	}
	
	@Test
	public void obtenerCantidadTotalTest() {
		Requisitos requisitos = new Requisitos(new Pair<Integer, Integer>(1, 2), new Pair<Integer, Integer>(1, 2), new Pair<Integer, Integer>(1, 2), new Pair<Integer, Integer>(1, 2));
		
		assertEquals(8, requisitos.obtenerCantidadTotal(Cantidad.MAXIMA));
		assertEquals(4, requisitos.obtenerCantidadTotal(Cantidad.MINIMA));
	}
	
	@Test
	public void obtenerCantidadSegunRolTest() {
		Requisitos requisitos = new Requisitos(new Pair<Integer, Integer>(2, 5), new Pair<Integer, Integer>(5, 10), new Pair<Integer, Integer>(4, 9), new Pair<Integer, Integer>(0, 1));
	
		assertEquals(5, requisitos.obtenerCantidadSegunRol(Roles.TESTER, Cantidad.MAXIMA));
		assertEquals(2, requisitos.obtenerCantidadSegunRol(Roles.TESTER, Cantidad.MINIMA));
		
		assertEquals(10, requisitos.obtenerCantidadSegunRol(Roles.PROGRAMADOR, Cantidad.MAXIMA));
		assertEquals(5, requisitos.obtenerCantidadSegunRol(Roles.PROGRAMADOR, Cantidad.MINIMA));
		
		assertEquals(9, requisitos.obtenerCantidadSegunRol(Roles.ARQUITECTO, Cantidad.MAXIMA));
		assertEquals(4, requisitos.obtenerCantidadSegunRol(Roles.ARQUITECTO, Cantidad.MINIMA));
		
		assertEquals(1, requisitos.obtenerCantidadSegunRol(Roles.LIDER_PROYECTO, Cantidad.MAXIMA));
		assertEquals(0, requisitos.obtenerCantidadSegunRol(Roles.LIDER_PROYECTO, Cantidad.MINIMA));
	}
}
