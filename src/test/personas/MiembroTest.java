package test.personas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.negocio.personas.Miembro;
import main.negocio.personas.Roles;

import main.util.Utilidad.Guid;

public class MiembroTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void MiembroSinNombreTest() {
		new Miembro(Roles.ARQUITECTO, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void MiembroNombreVacioTest() {
		new Miembro(Roles.ARQUITECTO, "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void MiembroSinRolTest() {
		new Miembro(null, "Ricardo Mora");
	}
	
	@Test
	public void NuevoMiembroTest() {
		Miembro m = new Miembro(Roles.PROGRAMADOR, "Diego Silvera");
		
		assertEquals("Diego Silvera", m.obtenerNombre());
		assertEquals(Roles.PROGRAMADOR, m.obtenerRol());
		assertTrue(Guid.esValido(m.obtenerId()));
	}

}
