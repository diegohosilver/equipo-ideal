package test.personas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;

import main.negocio.personas.Miembro;
import main.negocio.personas.MiembrosDisponibles;
import main.negocio.personas.MiembrosIncompatibles;
import main.negocio.personas.Roles;
import main.util.Utilidad;

public class MiembrosIncompatiblesTest {
	private MiembrosDisponibles miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
	private MiembrosIncompatibles miembrosIncompatibles = MiembrosIncompatibles.obtenerInstancia();
	private Miembro miembro1;
	private Miembro miembro2;

	@Test (expected = IllegalArgumentException.class)
	public void agregarMiembrosSinIdsTest() {
		MiembrosIncompatibles instancia = MiembrosIncompatibles.obtenerInstancia();
		
		instancia.agregar(null, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarMiembrosIdsVaciosTest() {
		MiembrosIncompatibles instancia = MiembrosIncompatibles.obtenerInstancia();
		
		instancia.agregar("", "");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void agregarMiembrosIdsInvalidosTest() {
		MiembrosIncompatibles instancia = MiembrosIncompatibles.obtenerInstancia();
		
		instancia.agregar("id-1", "id-2");
	}
	
	@Test (expected = NoSuchElementException.class)
	public void agregarMiembrosInexistentesTest() {
		MiembrosIncompatibles instancia = MiembrosIncompatibles.obtenerInstancia();
		
		String id = Utilidad.Guid.generar();
		
		instancia.agregar(id, id);
	}
	
	@Test
	public void listarIdsTest() {
		establecerIncompatibles();
		
		Optional<Entry<String, String>> incompatible = miembrosIncompatibles.listarIds().entrySet().stream().findFirst(); 
		
		assertEquals(incompatible.get().getKey(), miembro1.obtenerId());
		assertEquals(incompatible.get().getValue(), miembro2.obtenerId());
	}
	
	@Test
	public void tieneIncompatibleTest() {
		establecerIncompatibles();
		
		assertTrue(miembrosIncompatibles.tieneIncompatible(miembro1.obtenerId()));
	}
	
	@Test
	public void yaSonIncompatiblesTest() {
		establecerIncompatibles();
		
		assertTrue(miembrosIncompatibles.yaSonIncompatibles(miembro1.obtenerId(), miembro2.obtenerId()));
	}
	
	@Test
	public void agregarRepetidosTest() {
		// Este método ya los establece como incompatibles por primera vez
		establecerIncompatibles();
		
		miembrosIncompatibles.agregar(miembro1.obtenerId(), miembro2.obtenerId());
		
		assertEquals(1, miembrosIncompatibles.listar().size());
	}

	private void establecerIncompatibles() {
		miembrosDisponibles.vaciar();
		miembrosIncompatibles.vaciar();
		
		miembro1 = new Miembro(Roles.PROGRAMADOR, "Diego Silvera");
		miembro2 = new Miembro(Roles.PROGRAMADOR, "Fabrizio Procida");
		
		miembrosDisponibles.agregar(miembro1);
		miembrosDisponibles.agregar(miembro2);
		
		miembrosIncompatibles.agregar(miembro1.obtenerId(), miembro2.obtenerId());
	}
	
	
}
