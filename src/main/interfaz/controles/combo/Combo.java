package main.interfaz.controles.combo;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import main.interfaz.controles.general.Dimensiones;
import main.util.Utilidad.Control;
import main.util.Utilidad.Objeto;

public class Combo {
	
	private static JComboBox generarConItems(Dimensiones dimensiones, List<ComboItem> items) {
		JComboBox combo = new JComboBox();
		
		for (ComboItem item : items) {
			combo.addItem(item);
		}
		
		combo.setBounds(dimensiones.obtenerX(), dimensiones.obtenerY(), dimensiones.obtenerAncho(), dimensiones.obtenerAlto());
		
		return combo;
	}
	
	public static JComboBox generar(Dimensiones dimensiones, List<ComboItem> items, ActionListener evento) {
		if (Objeto.esNulo(items) || items.size() == 0) {
			throw new IllegalArgumentException("Proporcione algun item para inicializar el combo");
		}
		
		Control.validarEvento(evento);
		Control.validarDimensiones(dimensiones);
		
		JComboBox combo = generarConItems(dimensiones, items);
		
		combo.addActionListener(evento);
		
		return combo;
	}

}
