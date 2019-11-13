package test.equipo;

import org.junit.Test;

import javafx.util.Pair;
import main.negocio.equipo.Requisitos;

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
}
