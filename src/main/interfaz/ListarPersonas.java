package main.interfaz;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.swing.JInternalFrame;
import javax.swing.JList;

import main.interfaz.controles.Alerta;
import main.interfaz.controles.Boton;
import main.interfaz.controles.Etiqueta;
import main.interfaz.controles.general.Dimensiones;
import main.interfaz.controles.lista.ListaSelectionModel;
import main.negocio.personas.Miembro;
import main.negocio.personas.MiembrosDisponibles;
import main.negocio.personas.MiembrosIncompatibles;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class ListarPersonas extends JInternalFrame implements Observer {
	private JPanel _panel;
	private JScrollPane _scrollPaneLeft;
	private JScrollPane _scrollPaneRight;
	private JList _listaPersonas;
	private JList _listaIncompatibles;
	
	private MiembrosDisponibles _miembros;
	private MiembrosIncompatibles _miembrosIncompatibles;
	
	public ListarPersonas() {
		super("Listar Personas");
		
		_miembros = MiembrosDisponibles.obtenerInstancia();
		_miembros.addObserver(this);
		
		_miembrosIncompatibles = MiembrosIncompatibles.obtenerInstancia();
		
		inicializarLayout();
		cargarControles();
	}
	
	private void inicializarLayout() {
		_panel = new JPanel();
		getContentPane().add(_panel, BorderLayout.CENTER);
		_panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(169dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(213dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(101dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void cargarControles() {
		_panel.add(Etiqueta.generar("Seleccione dos miembros para establecer incompatibilidad:", new Dimensiones(0, 0, 0, 0)), "2, 2");
		
		_panel.add(Boton.generar("OK", new Dimensiones(0, 0, 0, 0), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);	
			}
			
		}), "4, 6");
		
		_scrollPaneLeft = new JScrollPane();
		_panel.add(_scrollPaneLeft, "2, 4, fill, fill");
		
		_listaPersonas = new JList(_miembros.listar().toArray());
		_listaPersonas.setSelectionModel(new ListaSelectionModel(_listaPersonas, 2));
		_scrollPaneLeft.setViewportView(_listaPersonas);
		
		_panel.add(Boton.generar("Incompatibles", new Dimensiones(0, 0, 0, 0), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Miembro> miembrosSeleccionados = _listaPersonas.getSelectedValuesList();
				if (miembrosSeleccionados.size() < 2) {
					Alerta.mostrar("Debe seleccionar dos miembros para establecer incompatibilidad!");
					return;
				}
				
				_miembrosIncompatibles.agregar(miembrosSeleccionados.get(0).obtenerId(), miembrosSeleccionados.get(1).obtenerId());
				_listaIncompatibles.setListData(_miembrosIncompatibles.listar().toArray());
			}
			
		}), "4, 4");
		
		_scrollPaneRight = new JScrollPane();
		_panel.add(_scrollPaneRight, "6, 4, fill, fill");
		
		_listaIncompatibles = new JList();
		_scrollPaneRight.setViewportView(_listaIncompatibles);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void update(Observable o, Object listaMiembros) {
		List<Miembro> miembros = (List<Miembro>) listaMiembros;
		
		_listaPersonas.setListData(miembros.toArray());
	}
}
