package main.interfaz.controles;

import java.awt.Font;

import javax.swing.JLabel;

import main.interfaz.controles.general.Dimensiones;
import main.util.Utilidad.CadenaTexto;
import main.util.Utilidad.Control;

public class Etiqueta {

	private static JLabel generarEtiquetaConPropiedades(String texto, Dimensiones dimensiones, int fuente, float tamanio) {		
		JLabel etiqueta = new JLabel(texto);
		
		etiqueta.setFont(etiqueta.getFont().deriveFont(etiqueta.getFont().getStyle() | fuente, tamanio));
		etiqueta.setBounds(dimensiones.obtenerX(), dimensiones.obtenerY(), dimensiones.obtenerAncho(), dimensiones.obtenerAlto());
		
		return etiqueta;
	}
	
	public static JLabel generar(String texto, Dimensiones dimensiones) {	
		CadenaTexto.validarVacia(texto);	
		Control.validarDimensiones(dimensiones);
		
		return generarEtiquetaConPropiedades(texto, dimensiones, Font.PLAIN, 12F);
	}
	
}
