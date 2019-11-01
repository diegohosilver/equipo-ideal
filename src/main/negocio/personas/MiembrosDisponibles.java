package main.negocio.personas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import main.util.Utilidad.Objeto;

public class MiembrosDisponibles {
	
	private static MiembrosDisponibles _instancia = null;
	private List<Miembro> _miembros;
	
	public static MiembrosDisponibles obtenerInstancia() {
		if (Objeto.esNulo(_instancia)) {
			_instancia = new MiembrosDisponibles();
		}
		
		return _instancia;
	}
	
	private MiembrosDisponibles() {
		_miembros = new ArrayList<Miembro>();
	}
	
	public boolean existe(Miembro miembro) {
		return existeMiembro(miembro.obtenerId());
	}
	
	public boolean existe(String id) {
		 return existeMiembro(id);
	}
	
	private boolean existeMiembro(String id) {
		Optional<Miembro> buscar = _miembros.stream().filter(x -> x.obtenerId() == id).findFirst();
		
		return buscar.isPresent();
	}
	
	public void agregar(Miembro miembro) {
		if (Objeto.esNulo(miembro)) {
			throw new IllegalArgumentException("Miembro no puede estar vacio");
		}
		
		_miembros.add(miembro);
	}
	
	public List<Miembro> listar() {
		return _miembros;
	}
	
	public void vaciar() {
		_miembros.clear();
	}

}
