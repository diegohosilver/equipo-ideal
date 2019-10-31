package main.negocio.personas;

public class Miembro extends Persona {
	
	Roles _rol;
	
	public Miembro(Roles rol, String nombre) {
		super(nombre);
		
		_rol = rol;
	}
	
	public Roles obtenerRol() {
		return _rol;
	}

}