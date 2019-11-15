package main.negocio.personas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import main.util.Utilidad.*;

public class MiembrosIncompatibles {

	private static MiembrosIncompatibles _instancia = null;
	private Map<String, String> _miembros;
	private MiembrosDisponibles _miembrosDisponibles;
	
	public static MiembrosIncompatibles obtenerInstancia() {
		if (Objeto.esNulo(_instancia)) {
			_instancia = new MiembrosIncompatibles();
		}
		
		return _instancia;
	}
	
	private MiembrosIncompatibles() {
		_miembros = new HashMap<String, String>();
		
		_miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
	}
	
	public void agregar(String idMiembro1, String idMiembro2) {
		validarIds(idMiembro1, idMiembro2);
		
		if (!yaSonIncompatibles(idMiembro1, idMiembro2))
			_miembros.put(idMiembro1, idMiembro2);
	}
	
	private void validarIds(String id1, String id2) {
		if (CadenaTexto.esVaciaONula(id1) || CadenaTexto.esVaciaONula(id2)) {
			throw new IllegalArgumentException("Los ids no pueden estar vacios");
		}
		
		if (!Guid.esValido(id1) || !Guid.esValido(id2)) {
			throw new IllegalArgumentException("Los ids no tienen un formato valido");
		}
		
		if (!_miembrosDisponibles.existe(id1) || !_miembrosDisponibles.existe(id2)) {
			throw new NoSuchElementException("No existen miembros con los ids provistos");
		}
	}
	
	public Map<String, String> listarIds() {
		return _miembros;
	}
	
	public List<String> listar() {
		List<String> incompatibles = _miembros.entrySet().stream().map(x -> 
										_miembrosDisponibles.obtener(x.getKey()).obtenerNombre() + " - " + _miembrosDisponibles.obtener(x.getValue()).obtenerNombre())
										.collect(Collectors.toList());
		
		return incompatibles;
	}
	
	public boolean tieneIncompatible(String id) {
		return _miembros.entrySet().stream().anyMatch(x -> x.getKey() == id || x.getValue() == id);
	}
	
	public boolean yaSonIncompatibles(String id1, String id2) {
		return _miembros.entrySet().stream().anyMatch(x -> (x.getKey() == id1 && x.getValue() == id2) || (x.getKey() == id2 && x.getValue() == id1));
	}
	
	public boolean esIncompatibleConAlguno(String id, List<String> ids) {
		for (String otroId : ids) {
			if (yaSonIncompatibles(id, otroId)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void vaciar() {
		_miembros.clear();
	}
}
