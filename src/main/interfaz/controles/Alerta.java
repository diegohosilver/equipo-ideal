package main.interfaz.controles;

import javax.swing.JOptionPane;

import main.util.Utilidad.CadenaTexto;

public class Alerta {

	public static void mostrar(String mensaje) {
		if (CadenaTexto.esVaciaONula(mensaje)) {
			throw new IllegalArgumentException("Mensaje no puede estar vacio");
		}
		
		JOptionPane.showMessageDialog(null, mensaje);
	}

}
