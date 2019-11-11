package test.personas;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.negocio.personas.Miembro;
import main.negocio.personas.MiembrosDisponibles;
import main.negocio.personas.Roles;
import main.util.Utilidad;

public class MiembrosDisponiblesTest {
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarMiembroNuloTest() {
		MiembrosDisponibles miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		
		miembrosDisponibles.agregar(null);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void obtenerMiembroInexistenteTest() {
		MiembrosDisponibles miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		
		miembrosDisponibles.obtener(Utilidad.Guid.generar());
	}
	
	@Test
	public void existeMiembroListaVaciaTest() {
		MiembrosDisponibles miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		
		assertFalse(miembrosDisponibles.existe("a1234"));
	}
	
	@Test
	public void existeMiembroPorIdTest() {
		Miembro m = new Miembro(Roles.PROGRAMADOR, "Diego Silvera");
		MiembrosDisponibles miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		miembrosDisponibles.agregar(m);
		
		// Enviamos el id del miembro a buscar
		assertTrue(miembrosDisponibles.existe(m.obtenerId()));
	}
	
	@Test
	public void existeMiembroPorObjetoTest() {
		Miembro m = new Miembro(Roles.PROGRAMADOR, "Diego Silvera");
		MiembrosDisponibles miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		miembrosDisponibles.agregar(m);
		
		// Enviamos el objeto entero
		assertTrue(miembrosDisponibles.existe(m));
	}
	
	@Test
	public void listarMiembrosTest() {		
		MiembrosDisponibles miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		
		// Asegurarnos que comenzamos con una lista vacia
		miembrosDisponibles.vaciar();
		miembrosDisponibles.agregar(new Miembro(Roles.PROGRAMADOR, "Diego Silvera"));
		miembrosDisponibles.agregar(new Miembro(Roles.PROGRAMADOR, "Fabrizio Procida"));
		
		assertEquals(2, miembrosDisponibles.listar().size());
	}

}
