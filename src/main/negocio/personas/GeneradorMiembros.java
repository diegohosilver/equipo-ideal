package main.negocio.personas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.util.Utilidad.Objeto;

public class GeneradorMiembros {
	
	private static GeneradorMiembros _instancia = null;
	
	private List<String> _nombres;
	
	private GeneradorMiembros() {
		cargarNombres();
	}
	
	public static GeneradorMiembros obtenerInstancia() {
		if (Objeto.esNulo(_instancia)) {
			_instancia = new GeneradorMiembros();
		}
		
		return _instancia;
	}

	public void generarMiembros() {
		MiembrosDisponibles miembrosDisponibles = MiembrosDisponibles.obtenerInstancia();
		
		// Arbitrariamente generamos 10 miembros para cada rol
		for (int i = 0; i < 10; i++) {
			miembrosDisponibles.agregar(new Miembro(Roles.TESTER, _nombres.get(generarRandomEnRango(0, 49))));
			miembrosDisponibles.agregar(new Miembro(Roles.PROGRAMADOR, _nombres.get(generarRandomEnRango(0, 49))));
			miembrosDisponibles.agregar(new Miembro(Roles.ARQUITECTO, _nombres.get(generarRandomEnRango(0, 49))));
			miembrosDisponibles.agregar(new Miembro(Roles.LIDER_PROYECTO, _nombres.get(generarRandomEnRango(0, 49))));
		}
	}
	
	private int generarRandomEnRango(int min, int max) {
		Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	}
	
	private void cargarNombres() {
		_nombres = new ArrayList<String>();
		
		_nombres.add("Mirna Wert  ");
		_nombres.add("Mable Farabaugh  ");
		_nombres.add("Darcy Gridley  ");
		_nombres.add("Elvis Birchfield  ");
		_nombres.add("Elsy Speegle  ");
		_nombres.add("Katy Durr  ");
		_nombres.add("Trudy Fleig  ");
		_nombres.add("Laverne Joiner  ");
		_nombres.add("Mildred Fiecke  ");
		_nombres.add("Launa Thigpen  ");
		_nombres.add("Warren Roldan  ");
		_nombres.add("Denisse Vandervort  ");
		_nombres.add("Rea Burch  ");
		_nombres.add("Stephani Hubble  ");
		_nombres.add("Leigh Lauffer  ");
		_nombres.add("Maris Camargo  ");
		_nombres.add("Kitty Galaz  ");
		_nombres.add("Nicolas Chuang  ");
		_nombres.add("Jeramy Kildow  ");
		_nombres.add("Blanch Sigmund  ");
		_nombres.add("Barton Vogan  ");
		_nombres.add("Regine Lawton  ");
		_nombres.add("Alexa Wolter  ");
		_nombres.add("Britany Frenkel  ");
		_nombres.add("Juliet Gartin  ");
		_nombres.add("Yoshiko Symons  ");
		_nombres.add("Vickie Ziegler  ");
		_nombres.add("Buck Charette  ");
		_nombres.add("Ellen Oliva  ");
		_nombres.add("Jolanda Maner  ");
		_nombres.add("Dolly Stager  ");
		_nombres.add("Faith Seaman  ");
		_nombres.add("Vince Alvis  ");
		_nombres.add("Leandro Pagan  ");
		_nombres.add("Tammi Stjames  ");
		_nombres.add("Maryetta Tomasini  ");
		_nombres.add("Rosann Hardy  ");
		_nombres.add("Kimberlee Belfield  ");
		_nombres.add("Terrilyn Lamoreaux  ");
		_nombres.add("Chin Dantin  ");
		_nombres.add("Rebbecca Villalvazo  ");
		_nombres.add("Zona Risch  ");
		_nombres.add("Clayton Langley  ");
		_nombres.add("Georgianne Breeden  ");
		_nombres.add("Kevin Petroff  ");
		_nombres.add("Emelda Leventhal  ");
		_nombres.add("Teofila Simes  ");
		_nombres.add("Brett Wasielewski  ");
		_nombres.add("Luis Holquin  ");
		_nombres.add("Katie Lehrer  ");
	}

}
