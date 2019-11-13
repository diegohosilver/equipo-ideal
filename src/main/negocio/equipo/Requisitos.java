package main.negocio.equipo;

import javafx.util.Pair;
import main.negocio.personas.Roles;
import main.util.Utilidad.Objeto;

public class Requisitos {
	private int _cantidadTotalMinima;
	private int _cantidadTotalMaxima;
	private Pair<Integer, Integer> _cantidadTesters;
	private Pair<Integer, Integer> _cantidadProgramadores;
	private Pair<Integer, Integer> _cantidadArquitectos;
	private Pair<Integer, Integer> _cantidadLideres;
	
	public Requisitos(Pair<Integer, Integer> testers, Pair<Integer, Integer> programadores, Pair<Integer, Integer> arquitectos, Pair<Integer, Integer> lideres) {
		validarPar(testers);
		validarPar(programadores);
		validarPar(arquitectos);
		validarPar(lideres);
		
		_cantidadTotalMinima = testers.getKey() + programadores.getKey() + arquitectos.getKey() + lideres.getKey();
		_cantidadTotalMaxima = testers.getValue() + programadores.getValue() + arquitectos.getValue() + lideres.getValue();

		_cantidadTesters = testers;	
		_cantidadProgramadores = programadores;
		_cantidadArquitectos = arquitectos;
		_cantidadLideres = lideres;
		
		validarRequisitos();
	}
	
	private void validarPar(Pair<Integer, Integer> par) {
		if (Objeto.esNulo(par) || Objeto.esNulo(par.getKey()) || Objeto.esNulo(par.getValue())) {
			throw new IllegalArgumentException("Par inválido");
		}
		
		if (par.getKey() > par.getValue()) {
			throw new IllegalArgumentException("El mínimo no puede ser mayor al máximo");
		}
		
		if (par.getKey() < 0 || par.getValue() < 0 ) {
			throw new IllegalArgumentException("El par no puede contener valores negativos");
		}
	}
	
	private void validarRequisitos() {
		if (_cantidadTotalMinima == 0 || _cantidadTotalMaxima == 0) {
			throw new IllegalArgumentException("El equipo no puede estar vacío");
		}
	}
	
	public int obtenerCantidadTotal(Cantidad cantidad) {
		return cantidad == Cantidad.MINIMA ? _cantidadTotalMinima : _cantidadTotalMaxima;
	}
	
	public int obtenerCantidadTesters(Cantidad cantidad) {
		return cantidad == Cantidad.MINIMA ? _cantidadTesters.getKey() : _cantidadTesters.getValue();
	}
	
	public int obtenerCantidadProgramadores(Cantidad cantidad) {
		return cantidad == Cantidad.MINIMA ? _cantidadProgramadores.getKey() : _cantidadTesters.getValue();
	}
	
	public int obtenerCantidadArquitectos(Cantidad cantidad) {
		return cantidad == Cantidad.MINIMA ? _cantidadArquitectos.getKey() : _cantidadArquitectos.getValue();
	}
	
	public int obtenerCantidadLideres(Cantidad cantidad) {
		return cantidad == Cantidad.MINIMA ? _cantidadLideres.getKey() : _cantidadLideres.getValue();
	}
	
	public int obtenerCantidadSegunRol(Roles rol, Cantidad cantidad) {
		switch(rol) {
			case TESTER:
				return obtenerCantidadTesters(cantidad);
			case PROGRAMADOR:
				return obtenerCantidadProgramadores(cantidad);
			case ARQUITECTO:
				return obtenerCantidadArquitectos(cantidad);
			default:
				return obtenerCantidadLideres(cantidad);
		}
	}
}
