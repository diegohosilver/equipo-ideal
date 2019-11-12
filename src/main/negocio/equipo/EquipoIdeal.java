package main.negocio.equipo;

import java.util.List;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.swing.SwingWorker;

import main.negocio.personas.Miembro;
import main.negocio.personas.MiembrosDisponibles;
import main.negocio.personas.MiembrosIncompatibles;
import main.negocio.personas.Roles;
import main.util.Utilidad.Objeto;

public class EquipoIdeal extends SwingWorker<Boolean, Object> {
	private static EquipoIdeal _instancia = null;
	
	private Requisitos _requisitos;
	private MiembrosDisponibles _miembrosDisponibles;
	private MiembrosIncompatibles _miembrosIncompatibles;
	
	private boolean _existenIncompatibles;
	private boolean _equipoCumpleConRequisitos;
	
	private Equipo _equipo;
	
	private EquipoIdeal() {
		_miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		_miembrosIncompatibles = MiembrosIncompatibles.obtenerInstancia();
		
		_existenIncompatibles = _miembrosIncompatibles.listar().size() > 0;
		
		_equipo = new Equipo();
	}
	
	public static EquipoIdeal obtenerInstancia() {
		if (Objeto.esNulo(_instancia)) {
			_instancia = new EquipoIdeal();
		}
		
		return _instancia;
	}
	
	public void cargarRequisitos(Requisitos requisitos) {
		if (Objeto.esNulo(requisitos)) {
			throw new IllegalArgumentException("Requisitos no puede estar vacio");
		}
		
		_requisitos = requisitos;
	}
	
	public Requisitos obtenerRequisitos() {
		return _requisitos;
	}
	
	public boolean sePuedeEjecutar() {
		return (!Objeto.esNulo(_requisitos) && _miembrosDisponibles.listar().size() > 0);
	}
	
	public void agregarObservador(Observer observador) {
		_equipo.addObserver(observador);
	}
	
	private void armarEquipo() {
		buscarMiembros(Roles.TESTER, _requisitos.obtenerCantidadTesters(Cantidad.MAXIMA));
		buscarMiembros(Roles.PROGRAMADOR, _requisitos.obtenerCantidadProgramadores(Cantidad.MAXIMA));
		buscarMiembros(Roles.ARQUITECTO, _requisitos.obtenerCantidadArquitectos(Cantidad.MAXIMA));
		buscarMiembros(Roles.LIDER_PROYECTO, _requisitos.obtenerCantidadLideres(Cantidad.MAXIMA));
	}
	
	private void buscarMiembros(Roles rol, int cantidad) {
		List<Miembro> miembrosDelRol = _miembrosDisponibles.listar().stream().filter(x -> x.obtenerRol() == rol).collect(Collectors.toList());
		
		for (int i = 0; i < cantidad; i++) {
			
			for(Miembro miembro : miembrosDelRol) {
				
				if (!_equipo.existeMiembro(miembro)) {
					
					if (_existenIncompatibles) {
						if (!_miembrosIncompatibles.esIncompatibleConAlguno(miembro.obtenerId(), _equipo.obtenerIdsDelEquipo())) {
							_equipo.agregarMiembro(miembro);
							break;
						}
					}
					else {
						_equipo.agregarMiembro(miembro);
						break;
					}
				}
			}
		}
	}
	
	@Override
	protected Boolean doInBackground() throws Exception 
	{
		_equipo.vaciar();
		
		armarEquipo();
		
		// Verificar si los requisitos fueron satisfechos
		_equipoCumpleConRequisitos = true;
		
		for (Roles rol : Roles.values()) {
			long cantidadEnRol = _equipo.obtenerCantidadMiembrosEnRol(rol);
			_equipoCumpleConRequisitos = _equipoCumpleConRequisitos && 
										(
												cantidadEnRol >= _requisitos.obtenerCantidadSegunRol(rol, Cantidad.MINIMA) &&
												cantidadEnRol <= _requisitos.obtenerCantidadSegunRol(rol, Cantidad.MAXIMA)
										);
		}
		
		return _equipoCumpleConRequisitos;
	}
	
	@Override 
	protected void done( ) {
		_equipo.notificarObservadores();
	}
}