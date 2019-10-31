package main.negocio.personas;

import java.util.HashMap;
import java.util.Map;

import main.util.Utilidad.*;

public class Incompatibles {

	private static Incompatibles _instancia = null;
	private Map<String, String> _miembros;
	
	public static Incompatibles obtenerInstancia() {
		if (Objeto.esNulo(_instancia)) {
			_instancia = new Incompatibles();
		}
		
		return _instancia;
	}
	
	private Incompatibles() {
		_miembros = new HashMap<String, String>();
	}
	
	public void agregarMiembros(String idMiembro1, String idMiembro2) {
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
		
		// TODO: validar que el id exista
	}
	
	public Map<String, String> listarMiembros() {
		return _miembros;
	}
}
