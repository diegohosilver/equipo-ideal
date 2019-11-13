package test.equipo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.negocio.equipo.Equipo;
import main.negocio.personas.Miembro;
import main.negocio.personas.Roles;

public class EquipoTest {

	@Test (expected = IllegalArgumentException.class)
	public void agregarMiembroNullTest() {
		Equipo equipo = new Equipo();
		
		equipo.agregarMiembro(null);;
	}
	
	@Test
	public void existeMiembroTest() {
		Equipo equipo = new Equipo();
		
		Miembro miembro = new Miembro(Roles.PROGRAMADOR, "Diego Silvera");
		
		equipo.agregarMiembro(miembro);
		
		assertTrue(equipo.existeMiembro(miembro));
	}
	
	@Test
	public void noExisteMiembroTest() {
		Equipo equipo = new Equipo();
		
		Miembro miembro = new Miembro(Roles.PROGRAMADOR, "Diego Silvera");
		
		assertFalse(equipo.existeMiembro(miembro));
	}
	
	@Test
	public void obtenerIdsTest() {
		Equipo equipo = new Equipo();
		
		Miembro miembro = new Miembro(Roles.PROGRAMADOR, "Diego Silvera");
		
		equipo.agregarMiembro(miembro);
		
		assertTrue(equipo.obtenerIdsDelEquipo().contains(miembro.obtenerId()));
	}
	
	@Test
	public void obtenerCantidadMiembrosEnRolTest() {
		Equipo equipo = new Equipo();
		
		equipo.agregarMiembro(new Miembro(Roles.PROGRAMADOR, "Diego"));
		equipo.agregarMiembro(new Miembro(Roles.PROGRAMADOR, "Fabrizio"));
		equipo.agregarMiembro(new Miembro(Roles.TESTER, "Lucas"));
		equipo.agregarMiembro(new Miembro(Roles.LIDER_PROYECTO, "Javier"));
		
		assertEquals(2, equipo.obtenerCantidadMiembrosEnRol(Roles.PROGRAMADOR));
		assertEquals(1, equipo.obtenerCantidadMiembrosEnRol(Roles.TESTER));
		assertEquals(1, equipo.obtenerCantidadMiembrosEnRol(Roles.LIDER_PROYECTO));
	}
}
