package main.interfaz.controles;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.interfaz.controles.general.Dimensiones;
import main.util.Utilidad.CadenaTexto;
import main.util.Utilidad.Control;

public class Boton {

	private static JButton generarBotonConPropiedades(String texto, Dimensiones dimensiones, int fuente, float tamanio) {
		CadenaTexto.validarVacia(texto);	
		Control.validarDimensiones(dimensiones);
		
		JButton boton = new JButton(texto);
		
		boton.setFont(boton.getFont().deriveFont(boton.getFont().getStyle() | fuente, tamanio));
		boton.setBounds(dimensiones.obtenerX(), dimensiones.obtenerY(), dimensiones.obtenerAncho(), dimensiones.obtenerAlto());
		
		return boton;
	}
	
	public static JButton generar(String texto, Dimensiones dimensiones, ActionListener evento) {
		Control.validarEvento(evento);
		
		JButton boton = generarBotonConPropiedades(texto, dimensiones, Font.BOLD, 12F);
		boton.addActionListener(evento);
		return boton;
	}

}
