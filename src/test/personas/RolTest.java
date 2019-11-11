package test.personas;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import main.negocio.personas.Roles;

public class RolTest {
	
	@Test
	public void obtenerRolTesterComoStringTest() {
		String rol = Roles.TESTER.toString();
		
		assertEquals("Tester", rol);
	}
	
	@Test
	public void obtenerRolProgramadorComoStringTest() {
		String rol = Roles.PROGRAMADOR.toString();
		
		assertEquals("Programador", rol);
	}
	
	@Test
	public void obtenerRolArquitectoComoStringTest() {
		String rol = Roles.ARQUITECTO.toString();
		
		assertEquals("Arquitecto", rol);
	}
	
	@Test
	public void obtenerRolLiderProyectoComoStringTest() {
		String rol = Roles.LIDER_PROYECTO.toString();
		
		assertEquals("Lider de Proyecto", rol);
	}

}
