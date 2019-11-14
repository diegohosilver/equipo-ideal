package test.personas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.negocio.personas.GeneradorMiembros;
import main.negocio.personas.MiembrosDisponibles;
import main.negocio.personas.Roles;

public class GeneradorMiembrosTest {

	@Test
	public void generarMiembrosTest() {
		MiembrosDisponibles m = MiembrosDisponibles.obtenerInstancia();
		
		m.vaciar();
		
		GeneradorMiembros g = GeneradorMiembros.obtenerInstancia();
		
		g.generarMiembros();
		
		assertEquals(40, m.listar().size());
	}
	
	@Test
	public void generarMiembrosEnRolTest() {
		MiembrosDisponibles m = MiembrosDisponibles.obtenerInstancia();
		
		m.vaciar();
		
		GeneradorMiembros g = GeneradorMiembros.obtenerInstancia();
		
		g.generarMiembros();
		
		assertEquals(10, m.listar().stream().filter(x -> x.obtenerRol() == Roles.TESTER).count());
		assertEquals(10, m.listar().stream().filter(x -> x.obtenerRol() == Roles.PROGRAMADOR).count());
		assertEquals(10, m.listar().stream().filter(x -> x.obtenerRol() == Roles.ARQUITECTO).count());
		assertEquals(10, m.listar().stream().filter(x -> x.obtenerRol() == Roles.LIDER_PROYECTO).count());
	}
	
}
