package main.negocio.equipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import main.negocio.personas.Miembro;
import main.negocio.personas.Roles;
import main.util.Utilidad.Objeto;

public class Equipo {
	private List<Miembro> _miembros;
	
	public Equipo() {
		_miembros = new ArrayList<Miembro>();
	}
	
	public void agregarMiembro(Miembro miembro) {
		if (Objeto.esNulo(miembro)) {
			throw new IllegalArgumentException("Miembro no puede estar vacio");
		}
		
		_miembros.add(miembro);
	}
	
	public boolean existeMiembro(Miembro miembro) {
		Optional<Miembro> busqueda = _miembros.stream().filter(x -> x.obtenerId() == miembro.obtenerId()).findFirst();
		
		return busqueda.isPresent();
	}
	
	public List<Miembro> listar() {
		return _miembros;
	}
	
	public List<String> obtenerIdsDelEquipo() {
		return _miembros.stream().map(x -> x.obtenerId()).collect(Collectors.toList());
	}
	
	public long obtenerCantidadMiembrosEnRol(Roles rol) {
		return _miembros.stream().filter(x -> x.obtenerRol() == rol).count();
	}
	
	public void vaciar() {
		_miembros.clear();
	}
}
