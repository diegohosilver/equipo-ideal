package main.negocio.personas;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import main.util.Utilidad.*;

public class MiembrosIncompatibles {

	private static MiembrosIncompatibles _instancia = null;
	private Map<String, String> _miembros;
	
	public static MiembrosIncompatibles obtenerInstancia() {
		if (Objeto.esNulo(_instancia)) {
			_instancia = new MiembrosIncompatibles();
		}
		
		return _instancia;
	}
	
	private MiembrosIncompatibles() {
		_miembros = new HashMap<String, String>();
	}
	
	public void agregar(String idMiembro1, String idMiembro2) {
		validarIds(idMiembro1, idMiembro2);
		
		_miembros.put(idMiembro1, idMiembro2);
	}
	
	private void validarIds(String id1, String id2) {
		if (CadenaTexto.esVaciaONula(id1) || CadenaTexto.esVaciaONula(id2)) {
			throw new IllegalArgumentException("Los ids no pueden estar vacios");
		}
		
		if (!Guid.esValido(id1) || !Guid.esValido(id2)) {
			throw new IllegalArgumentException("Los ids no tienen un formato valido");
		}
		
		MiembrosDisponibles disponibles = MiembrosDisponibles.obtenerInstancia();
		
		if (!disponibles.existe(id1) || !disponibles.existe(id2)) {
			throw new NoSuchElementException("No existen miembros con los ids provistos");
		}
	}
	
	public Map<String, String> listar() {
		return _miembros;
	}
	
	public boolean tieneIncompatible(String id) {
		return _miembros.entrySet().stream().anyMatch(x -> x.getKey() == id || x.getValue() == id);
	}
}
