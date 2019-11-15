package main.util;

import java.awt.event.ActionListener;
import java.util.UUID;

import main.interfaz.controles.general.Dimensiones;

public final class Utilidad {
	
	public static class Control {
		public static void validarEvento(ActionListener evento) {
			if (Objeto.esNulo(evento)) {
				throw new IllegalArgumentException("Evento no puede estar vacio");
			}
		}
		
		public static void validarDimensiones(Dimensiones dimensiones) {
			if (Objeto.esNulo(dimensiones)) {
				throw new IllegalArgumentException("Dimensiones no puede estar vacio");
			}
		}	
	}
	
	public static class CadenaTexto {
		public static boolean esVaciaONula(String cadena) {
			return !(cadena != null && cadena.length() > 0);
		}
		
		public static double aDouble(String valor) {
			try {
				return Double.parseDouble(valor);
			}
			catch(Exception ex) {
				return 0;
			}
		}
		
		public static int aInt(String valor) {
			try {
				return Integer.parseInt(valor);
			}
			catch(Exception ex) {
				return 0;
			}
		}
		
		public static void validarVacia(String texto) {
			if (CadenaTexto.esVaciaONula(texto)) {
				throw new IllegalArgumentException("Texto no puede estar vacio");
			}
		}
	}
	
	public static class Objeto {
		public static boolean esNulo(Object instancia) {
			return instancia == null;
		}
	}
	
	public static class Guid {
		public static String generar() {
			UUID uuid = UUID.randomUUID();
			
			return uuid.toString();
		}
		
		public static boolean esValido(String guid) {	
			try {
			    UUID uuid = UUID.fromString(guid);
			    return true;
			} catch (IllegalArgumentException exception){
			    return false;
			}
		}
	}

}
