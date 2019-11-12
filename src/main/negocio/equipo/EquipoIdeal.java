package main.negocio.equipo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import main.negocio.personas.Miembro;
import main.negocio.personas.MiembrosDisponibles;
import main.negocio.personas.MiembrosIncompatibles;
import main.negocio.personas.Roles;
import main.util.Utilidad.Objeto;

public class EquipoIdeal {
	private static EquipoIdeal _instancia = null;
	
	private Requisitos _requisitos;
	private List<Miembro> _miembrosDelEquipo;
	private MiembrosDisponibles _miembrosDisponibles;
	private MiembrosIncompatibles _miembrosIncompatibles;
	
	private boolean _existenIncompatibles;
	private boolean _equipoCumpleConRequisitos;
	
	private EquipoIdeal() {
		_miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		_miembrosIncompatibles = MiembrosIncompatibles.obtenerInstancia();
		
		_existenIncompatibles = _miembrosIncompatibles.listar().size() > 0;
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
	
	public List<Miembro> obtenerEquipo() {
		armarEquipo();
		
		// Verificar si los requisitos fueron satisfechos
		_equipoCumpleConRequisitos = true;
		
		for (Roles rol : Roles.values()) {
			long cantidadEnRol = cantidadMiembrosEnRol(rol);
			_equipoCumpleConRequisitos = _equipoCumpleConRequisitos && 
										(
												cantidadEnRol >= _requisitos.obtenerCantidadSegunRol(rol, Cantidad.MINIMA) &&
												cantidadEnRol <= _requisitos.obtenerCantidadSegunRol(rol, Cantidad.MAXIMA)
										);
		}
		
		return _miembrosDelEquipo;
	}
	
	public boolean equipoCumpleConRequisitos() {
		return _equipoCumpleConRequisitos;
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
				
				if (!miembroExisteEnEquipo(miembro)) {
					
					if (_existenIncompatibles) {
						if (!_miembrosIncompatibles.esIncompatibleConAlguno(miembro.obtenerId(), obtenerIdsDelEquipo())) {
							_miembrosDelEquipo.add(miembro);
							break;
						}
					}
					else {
						_miembrosDelEquipo.add(miembro);
						break;
					}
				}
			}
		}
	}
	
	private boolean miembroExisteEnEquipo(Miembro miembro) {
		Optional<Miembro> busqueda = _miembrosDelEquipo.stream().filter(x -> x.obtenerId() == miembro.obtenerId()).findFirst();
		
		return busqueda.isPresent();
	}
	
	private List<String> obtenerIdsDelEquipo() {
		return _miembrosDelEquipo.stream().map(x -> x.obtenerId()).collect(Collectors.toList());
	}
	
	private long cantidadMiembrosEnRol(Roles rol) {
		return _miembrosDelEquipo.stream().filter(x -> x.obtenerRol() == rol).count();
	}
}