package main.interfaz.controles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JDesktopPane;

import javax.imageio.ImageIO;

public class Desktop extends JDesktopPane {
	private Image image;
	
	public Desktop() {
		getImage();
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
