package main.negocio.personas;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Optional;

import main.util.Utilidad.Objeto;

public class MiembrosDisponibles extends Observable {
	
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
		Optional<Miembro> buscar = buscarPorId(id);
		
		return buscar.isPresent();
	}
	
	private Optional<Miembro> buscarPorId(String id) {
		return _miembros.stream().filter(x -> x.obtenerId() == id).findFirst();
	}
	
	public Miembro obtener(String id) {
		Optional<Miembro> buscar = buscarPorId(id);
		
		if (!buscar.isPresent()) {
			throw new NoSuchElementException("No se encontró ningún miembro con el id " + id);
		}
		
		return buscar.get();
	}
	
	public void agregar(Miembro miembro) {
		if (Objeto.esNulo(miembro)) {
			throw new IllegalArgumentException("Miembro no puede estar vacio");
		}
		
		_miembros.add(miembro);
		
		notificarObservadores();
	}
	
	public List<Miembro> listar() {
		return _miembros;
	}
	
	public void vaciar() {
		_miembros.clear();
		
		notificarObservadores();
	}
	
	private void notificarObservadores() {
		setChanged();
		notifyObservers(_miembros);
	}

}
