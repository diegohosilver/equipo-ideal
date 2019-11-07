package main.interfaz;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.swing.JInternalFrame;
import javax.swing.JList;

import main.interfaz.controles.Boton;
import main.interfaz.controles.general.Dimensiones;
import main.negocio.personas.Miembro;
import main.negocio.personas.MiembrosDisponibles;
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

public class ListarPersonas extends JInternalFrame implements Observer {
	private JPanel _panel;
	private JScrollPane _scrollPane;
	private JList _lista;
	
	MiembrosDisponibles _miembros;
	
	public ListarPersonas() {
		super("Listar Personas");
		
		_miembros = MiembrosDisponibles.obtenerInstancia();
		_miembros.addObserver(this);
		
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
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(101dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void cargarControles() {
		JLabel lblListadoDePersonas = new JLabel("Listado de Personas:");
		_panel.add(lblListadoDePersonas, "2, 2");
		
		_panel.add(Boton.generar("OK", new Dimensiones(0, 0, 0, 0), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);	
			}
			
		}), "4, 6");
		
		_scrollPane = new JScrollPane();
		_panel.add(_scrollPane, "2, 4, fill, fill");
		
		_lista = new JList(obtenerNombres(_miembros.listar()).toArray());
		_scrollPane.setViewportView(_lista);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void update(Observable o, Object listaMiembros) {
		List<String> nombres = obtenerNombres((List<Miembro>) listaMiembros);
		
		_lista.setListData(nombres.toArray());
	}
	
	private List<String> obtenerNombres(List<Miembro> miembros) {
		return ((List<Miembro>) miembros).stream().map(x -> x.obtenerNombre() + " - " + x.obtenerRol().toString()).collect(Collectors.toList());
	}

}
