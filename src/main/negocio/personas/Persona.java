package main.negocio.personas;

import main.util.Utilidad.CadenaTexto;
import main.util.Utilidad.Guid;

public class Persona {
	
	private String _id;
	private String _nombre;
	
	public Persona(String nombre) {
		validarDatos(nombre);
		
		_id = Guid.generar();
		_nombre = nombre;
	}
	
	private void validarDatos(String nombre) {
		if (CadenaTexto.esVaciaONula(nombre)) {
			throw new IllegalArgumentException("Argumentos inválidos para el alta de la persona");
		}
	}
	
	public String obtenerId() {
		return _id;
	}
	
	public String obtenerNombre() {
		return _nombre;
	}

}
