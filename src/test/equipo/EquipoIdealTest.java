package test.equipo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javafx.util.Pair;
import main.negocio.equipo.EquipoIdeal;
import main.negocio.equipo.Requisitos;
import main.negocio.personas.Miembro;
import main.negocio.personas.MiembrosDisponibles;
import main.negocio.personas.MiembrosIncompatibles;
import main.negocio.personas.Roles;

public class EquipoIdealTest {
	
	private Miembro miembroIncompatible1;
	private Miembro miembroIncompatible2;

	@Test (expected = IllegalArgumentException.class)
	public void cargarRequisitosVacioTest() {
		EquipoIdeal equipoIdeal = EquipoIdeal.obtenerInstancia();
		
		equipoIdeal.cargarRequisitos(null);
	}
	
	@Test
	public void sePuedeEjecutarTest() {
		MiembrosDisponibles miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		EquipoIdeal equipoIdeal = EquipoIdeal.obtenerInstancia();
		
		// Vaciar miembros
		miembrosDisponibles.vaciar();
		
		assertFalse(equipoIdeal.sePuedeEjecutar());
	}
	
	@Test
	public void armarEquipoIdealRequisitosCumplidosTest() {
		generarMiembros();
		establecerIncompatibles();
		generarRequisitosSatisfactorios();
		
		EquipoIdeal equipoIdeal = EquipoIdeal.obtenerInstancia();
		
		equipoIdeal.ejecutarAlgoritmo();
		
		assertTrue(equipoIdeal.cumpleConRequisitos());
	}
	
	@Test
	public void armarEquipoIdealRequisitosIncumplidosTest() {
		generarMiembros();
		establecerIncompatibles();
		generarRequisitosInsatisfactorios();
		
		EquipoIdeal equipoIdeal = EquipoIdeal.obtenerInstancia();
		
		equipoIdeal.ejecutarAlgoritmo();
		
		assertFalse(equipoIdeal.cumpleConRequisitos());
	}
	
	private void generarMiembros() {
		MiembrosDisponibles miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		
		miembrosDisponibles.vaciar();
		
		miembrosDisponibles.agregar(new Miembro(Roles.TESTER, "Lucas"));
		miembrosDisponibles.agregar(new Miembro(Roles.TESTER, "Javier"));
		miembrosDisponibles.agregar(new Miembro(Roles.PROGRAMADOR, "Gabriel"));
		
		miembroIncompatible1 = new Miembro(Roles.PROGRAMADOR, "Diego");
		
		miembrosDisponibles.agregar(miembroIncompatible1);
		miembrosDisponibles.agregar(new Miembro(Roles.ARQUITECTO, "Camila"));
		
		miembroIncompatible2 = new Miembro(Roles.ARQUITECTO, "Brenda");
		
		miembrosDisponibles.agregar(miembroIncompatible2);
		miembrosDisponibles.agregar(new Miembro(Roles.LIDER_PROYECTO, "Karen"));
		miembrosDisponibles.agregar(new Miembro(Roles.LIDER_PROYECTO, "Jimena"));
	}
	
	private void establecerIncompatibles() {
		MiembrosIncompatibles miembrosIncompatibles = MiembrosIncompatibles.obtenerInstancia();
		
		miembrosIncompatibles.vaciar();
		
		// 1 programador es incompatible con 1 arquitecto. Por lo tanto el programador entra y el arquitecto no
		miembrosIncompatibles.agregar(miembroIncompatible1.obtenerId(), miembroIncompatible2.obtenerId());
	}
	
	private void generarRequisitosSatisfactorios() {
		// Solicitamos que el equipo tenga 1 o 2 arquitectos; este requisito se cumple ya que al menos 1 de los miembros logra ingresar al equipo
		Requisitos requisitos = new Requisitos(new Pair<Integer, Integer>(1,2), new Pair<Integer, Integer>(1, 2), new Pair<Integer, Integer>(1, 2), new Pair<Integer, Integer>(1, 2));
		
		EquipoIdeal equipoIdeal = EquipoIdeal.obtenerInstancia();
		
		equipoIdeal.cargarRequisitos(requisitos);
	}
	
	private void generarRequisitosInsatisfactorios() {
		// Solicitamos que el equipo tenga 2 arquitectos, cosa que no es posible porque uno de ellos es incompatible con un programador
		Requisitos requisitos = new Requisitos(new Pair<Integer, Integer>(1,2), new Pair<Integer, Integer>(1, 2), new Pair<Integer, Integer>(2, 2), new Pair<Integer, Integer>(1, 2));
		
		EquipoIdeal equipoIdeal = EquipoIdeal.obtenerInstancia();
		
		equipoIdeal.cargarRequisitos(requisitos);
	}
	
}
