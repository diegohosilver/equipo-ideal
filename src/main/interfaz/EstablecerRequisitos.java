package main.interfaz;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javafx.util.Pair;
import main.interfaz.controles.Alerta;
import main.interfaz.controles.Boton;
import main.interfaz.controles.Etiqueta;
import main.interfaz.controles.general.Dimensiones;
import main.negocio.equipo.Cantidad;
import main.negocio.equipo.EquipoIdeal;
import main.negocio.equipo.Requisitos;
import main.util.Utilidad;
import main.util.Utilidad.Objeto;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;

public class EstablecerRequisitos extends JInternalFrame {
	private JTextField _minimoTester;
	private JTextField _maximoTester;
	private JTextField _minimoProgramador;
	private JTextField _maximoProgramador;
	private JTextField _minimoArquitecto;
	private JTextField _maximoArquitecto;
	private JTextField _minimoLider;
	private JTextField _maximoLider;
	
	private JPanel _panel;
	
	private EquipoIdeal _equipoIdeal;

	public EstablecerRequisitos() {
		super("Establecer requisitos");
		
		_equipoIdeal = EquipoIdeal.obtenerInstancia();
		
		inicializarLayout();
		inicializarControles();
		cargarControles();
	}
	
	private void inicializarLayout() {
		_panel = new JPanel();
		getContentPane().add(_panel, BorderLayout.CENTER);
		_panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
	}
	
	private void inicializarControles() {
		_panel.add(Etiqueta.generar("Cantidad de testers:", new Dimensiones(0, 0, 0, 0)), "2, 2");
		
		_panel.add(Etiqueta.generar("Minimo", new Dimensiones(0, 0, 0, 0)), "2, 4, right, default");
		
		_minimoTester = new JTextField();
		_panel.add(_minimoTester, "4, 4, fill, default");
		_minimoTester.setColumns(10);
		
		_panel.add(Etiqueta.generar("Maximo", new Dimensiones(0, 0, 0, 0)), "6, 4, right, default");
		
		_maximoTester = new JTextField();
		_panel.add(_maximoTester, "8, 4, fill, default");
		_maximoTester.setColumns(10);
		
		_panel.add(Etiqueta.generar("Cantidad de programadores:", new Dimensiones(0, 0, 0, 0)), "2, 6");
		
		_panel.add(Etiqueta.generar("Minimo", new Dimensiones(0, 0, 0, 0)), "2, 8, right, default");
		
		_minimoProgramador = new JTextField();
		_panel.add(_minimoProgramador, "4, 8, fill, default");
		_minimoProgramador.setColumns(10);
		
		_panel.add(Etiqueta.generar("Maximo", new Dimensiones(0, 0, 0, 0)), "6, 8, right, default");
		
		_maximoProgramador = new JTextField();
		_panel.add(_maximoProgramador, "8, 8, fill, default");
		_maximoProgramador.setColumns(10);
		
		_panel.add(Etiqueta.generar("Cantidad de arquitectos:", new Dimensiones(0, 0, 0, 0)), "2, 10");
		
		_panel.add(Etiqueta.generar("Minimo", new Dimensiones(0, 0, 0, 0)), "2, 12, right, default");
		
		_minimoArquitecto = new JTextField();
		_panel.add(_minimoArquitecto, "4, 12, fill, default");
		_minimoArquitecto.setColumns(10);
		
		_panel.add(Etiqueta.generar("Maximo", new Dimensiones(0, 0, 0, 0)), "6, 12, right, default");
		
		_maximoArquitecto = new JTextField();
		_panel.add(_maximoArquitecto, "8, 12, fill, default");
		_maximoArquitecto.setColumns(10);
		
		_panel.add(Etiqueta.generar("Cantidad de lideres:", new Dimensiones(0, 0, 0, 0)), "2, 14");
		
		_panel.add(Etiqueta.generar("Minimo", new Dimensiones(0, 0, 0, 0)), "2, 16, right, default");
		
		_minimoLider = new JTextField();
		_panel.add(_minimoLider, "4, 16, fill, default");
		_minimoLider.setColumns(10);
		
		_panel.add(Etiqueta.generar("Maximo", new Dimensiones(0, 0, 0, 0)), "6, 16, right, default");
		
		_maximoLider = new JTextField();
		_panel.add(_maximoLider, "8, 16, fill, default");
		_maximoLider.setColumns(10);
		
		_panel.add(Boton.generar("Cancelar", new Dimensiones(0, 0, 0, 0), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);			
			}
			
		}), "10, 20");
		
		_panel.add(Boton.generar("OK", new Dimensiones(0, 0, 20, 0), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				guardarRestricciones();
			}
			
		}), "12, 20");
	}
	
	private void cargarControles() {
		Requisitos requisitos = _equipoIdeal.obtenerRequisitos();
		
		if (Objeto.esNulo(requisitos))
			return;
		
		_minimoTester.setText(Integer.toString(requisitos.obtenerCantidadTesters(Cantidad.MINIMA)));
		_maximoTester.setText(Integer.toString(requisitos.obtenerCantidadTesters(Cantidad.MAXIMA)));
		_minimoProgramador.setText(Integer.toString(requisitos.obtenerCantidadProgramadores(Cantidad.MINIMA)));
		_maximoProgramador.setText(Integer.toString(requisitos.obtenerCantidadProgramadores(Cantidad.MAXIMA)));
		_minimoArquitecto.setText(Integer.toString(requisitos.obtenerCantidadArquitectos(Cantidad.MINIMA)));
		_maximoArquitecto.setText(Integer.toString(requisitos.obtenerCantidadArquitectos(Cantidad.MAXIMA)));
		_minimoLider.setText(Integer.toString(requisitos.obtenerCantidadLideres(Cantidad.MINIMA)));
		_maximoLider.setText(Integer.toString(requisitos.obtenerCantidadLideres(Cantidad.MAXIMA)));
	}
	 
	private void guardarRestricciones() {
		if (entradasSonValidas()) {
			Requisitos requisitos = new Requisitos(
										generarPar(_minimoTester.getText(), _maximoTester.getText()),
										generarPar(_minimoProgramador.getText(), _maximoProgramador.getText()),
										generarPar(_minimoArquitecto.getText(), _maximoProgramador.getText()),
										generarPar(_minimoLider.getText(), _maximoLider.getText())
									);
			
			_equipoIdeal.cargarRequisitos(requisitos);
			
			Alerta.mostrar("Requisitos guardados con éxito");
		}
	}
	
	private Pair<Integer, Integer> generarPar(String uno, String otro) {
		return new Pair<Integer, Integer>(Utilidad.CadenaTexto.aInt(uno), Utilidad.CadenaTexto.aInt(otro));
	}
	
	private boolean entradasSonValidas() {
		String mensaje = "El valor debe ser un número entero.";
		
		if (!entradaEsValida(_minimoTester.getText())) {
			Alerta.mostrar("El minimo de testers es invalido. " + mensaje);
			return false;
		}
		
		if (!entradaEsValida(_maximoTester.getText())) {
			Alerta.mostrar("El maximo de testers es invalido. " + mensaje);
			return false;
		}
		
		if (!entradaEsValida(_minimoProgramador.getText())) {
			Alerta.mostrar("El minimo de programadores es invalido. " + mensaje);
			return false;
		}
		
		if (!entradaEsValida(_maximoProgramador.getText())) {
			Alerta.mostrar("El maximo de programadores es invalido. " + mensaje);
			return false;
		}
		
		if (!entradaEsValida(_minimoArquitecto.getText())) {
			Alerta.mostrar("El minimo de arquitectos es invalido. " + mensaje);
			return false;
		}
		
		if (!entradaEsValida(_maximoArquitecto.getText())) {
			Alerta.mostrar("El maximo de arquitectos es invalido. " + mensaje);
			return false;
		}
		
		if (!entradaEsValida(_minimoLider.getText())) {
			Alerta.mostrar("El minimo de lideres es invalido. " + mensaje);
			return false;
		}
		
		if (!entradaEsValida(_maximoLider.getText())) {
			Alerta.mostrar("El maximo de lideres es invalido. " + mensaje);
			return false;
		}
		
		return true;
	}
	
	private boolean entradaEsValida(String entrada) {
		String pattern = "[0-9]+";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(entrada);
		
		return m.matches();
	}
}
