package main.interfaz;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import main.interfaz.controles.Alerta;
import main.interfaz.controles.Boton;
import main.interfaz.controles.Etiqueta;
import main.interfaz.controles.general.Dimensiones;
import main.negocio.equipo.EquipoIdeal;
import main.negocio.personas.Miembro;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class GenerarEquipo extends JInternalFrame implements Observer {
	
	private JPanel _panel;
	private JList _listaEquipo;
	private EquipoIdeal _equipoIdeal;

	public GenerarEquipo() {
		super("Generar equipo");
		
		_equipoIdeal = EquipoIdeal.obtenerInstancia();
		_equipoIdeal.addObserver(this);
		
		inicializarLayout();
		inicializarControles();
	}
	
	private void inicializarLayout() {
		_panel = new JPanel();
		getContentPane().add(_panel, BorderLayout.CENTER);
		_panel.setLayout(new FormLayout(new ColumnSpec[] {
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
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
	}
	
	private void inicializarControles() {
		_panel.add(Etiqueta.generar("Equipo generado", new Dimensiones(0, 0, 0, 0)), "2, 2");
		
		JScrollPane scrollPane = new JScrollPane();
		_panel.add(scrollPane, "2, 4, fill, fill");
		
		_listaEquipo = new JList();
		scrollPane.setViewportView(_listaEquipo);
		
		_panel.add(Boton.generar("Cancelar", new Dimensiones(0, 0, 0, 0), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);			
			}
			
		}), "4, 6");

		_panel.add(Boton.generar("Generar", new Dimensiones(0, 0, 0, 0), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!_equipoIdeal.sePuedeEjecutar()) {
					Alerta.mostrar("No se puede generar el equipo. Compruebe si cargó las personas y los requisitos correctamente.");
					return;
				}
				
				_equipoIdeal.ejecutarAlgoritmoEnThread();
			
			}
		}), "6, 6");
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void update(Observable o, Object listaMiembros) {
		List<Miembro> miembros = (List<Miembro>) listaMiembros;
		
		_listaEquipo.setListData(miembros.toArray());
		
		String mensaje = _equipoIdeal.cumpleConRequisitos() ? "El mismo cumple" : "El mismo no cumple";
		
		Alerta.mostrar("Se generó un equipo. " + mensaje + " con los requisitos");
	}
}
