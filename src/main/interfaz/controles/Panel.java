package main.interfaz.controles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.interfaz.controles.general.Bordes;
import main.interfaz.controles.general.Dimensiones;
import main.util.Utilidad.Objeto;

public class Panel extends JPanel {
	private Image image;
	
	public Panel() {
		getImage();
	}
	
	private static void validarDimensiones(Dimensiones dimensiones) {
		if (Objeto.esNulo(dimensiones)) {
			throw new IllegalArgumentException("Dimensiones no puede estar vacio");
		}
	}
	
	private static void validarBordes(Bordes bordes) {
		if (Objeto.esNulo(bordes)) {
			throw new IllegalArgumentException("Bordes no puede estar vacio");
		}
	}
	
	private static JPanel generarConPropiedades(Dimensiones dimensiones) {
		validarDimensiones(dimensiones);
		
		JPanel panel = new JPanel();

		panel.setLayout(null);		
		panel.setBounds(dimensiones.obtenerX(), dimensiones.obtenerY(), dimensiones.obtenerAncho(), dimensiones.obtenerAlto());
		
		return panel;
	}
	
	public static JPanel generar(Dimensiones dimensiones) {
		return generarConPropiedades(dimensiones);
	}
	
	public static JPanel generar(Dimensiones dimensiones, Bordes bordes) {
		validarBordes(bordes);
		
		JPanel panel = generarConPropiedades(dimensiones);
		
		panel.setBorder(bordes.obtenerBorde());
		
		return panel;
	}

	private void getImage() {
		try {
			image = ImageIO.read(new File("logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      int x = (this.getWidth() - image.getWidth(null)) / 2;
      int y = (this.getHeight() - image.getHeight(null)) / 2;
      g2d.drawImage(image, x, y, null);
    }
}