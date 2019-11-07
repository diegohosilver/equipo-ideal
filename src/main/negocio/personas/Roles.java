package main.negocio.personas;

public enum Roles {
	
	LIDER_PROYECTO ("Lider de Proyecto"),
	
	ARQUITECTO ("Arquitecto"),
	
	PROGRAMADOR ("Programador"),
	
	TESTER ("Tester");

	private final String _nombreRol;
	
	private Roles(String s) {
		_nombreRol = s;
	}
	
	@Override
	public String toString() {
		return this._nombreRol;
	}
}
