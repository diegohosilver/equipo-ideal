package main.negocio.personas;

import main.util.Utilidad.Objeto;

public class Miembro extends Persona {
	
	Roles _rol;
	
	public Miembro(Roles rol, String nombre) {
		super(nombre);
		
		validarRol(rol);
		
		_rol = rol;
	}
	
	private void validarRol(Roles rol) {
		if (Objeto.esNulo(rol)) {
			throw new IllegalArgumentException("Rol no puede estar vacio");
		}
	}
	
	public Roles obtenerRol() {
		return _rol;
	}

}