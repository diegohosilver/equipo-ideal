package test.personas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.negocio.personas.Miembro;
import main.negocio.personas.Roles;

import main.util.Utilidad.Guid;

public class MiembroTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void miembroSinNombreTest() {
		new Miembro(Roles.ARQUITECTO, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void miembroNombreVacioTest() {
		new Miembro(Roles.ARQUITECTO, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void miembroSinRolTest() {
		new Miembro(null, "Ricardo Mora");
	}
	
	@Test
	public void nuevoMiembroTest() {
		Miembro m = new Miembro(Roles.PROGRAMADOR, "Diego Silvera");
		
		assertEquals("Diego Silvera", m.obtenerNombre());
		assertEquals(Roles.PROGRAMADOR, m.obtenerRol());
		assertTrue(Guid.esValido(m.obtenerId()));
	}

}
