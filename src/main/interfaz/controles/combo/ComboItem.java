package main.interfaz.controles.combo;

public class ComboItem<T> {
	private T _id;
	private String _descripcion;

	public ComboItem(T id, String descripcion) {
		this._id = id;
		this._descripcion = descripcion;
	}

	public T obtenerId() {
		return _id;
	}

	public String obtenerDescripcion() {
		return _descripcion;
	}

	@Override
	public String toString() {
		return _descripcion;
	}
}
