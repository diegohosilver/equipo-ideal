package main.interfaz;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import main.interfaz.controles.Alerta;
import main.interfaz.controles.Boton;
import main.interfaz.controles.Etiqueta;
import main.interfaz.controles.combo.Combo;
import main.interfaz.controles.combo.ComboItem;
import main.interfaz.controles.general.Dimensiones;
import main.negocio.personas.Miembro;
import main.negocio.personas.MiembrosDisponibles;
import main.negocio.personas.Roles;
import main.util.Utilidad;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AgregarPersona extends JInternalFrame {
	
	private JTextField _nombre;
	private Roles _rol;
	private JPanel _panel;
	private JComboBox _comboBox;
	
	public AgregarPersona() {
		super("Agregar Persona");
		
		_rol = Roles.TESTER;
		
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(21dlu;default)"),}));
	}
	
	private void cargarControles() {
		_panel.add(Etiqueta.generar("Nombre y apellido:", new Dimensiones(0, 0, 0, 0)), "2, 2");
		
		_nombre = new JTextField();
		_panel.add(_nombre, "2, 4, fill, default");
		_nombre.setColumns(10);
		
		_panel.add(Etiqueta.generar("Rol:", new Dimensiones(0, 0, 0, 0)), "2, 6");
		
		_comboBox = Combo.generar(new Dimensiones(5, 28, 130, 20), listarRoles(), new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {			
				JComboBox c = (JComboBox) e.getSource();
				ComboItem<Roles> item = (ComboItem<Roles>) c.getSelectedItem();
				
				_rol = item.obtenerId();
			}
		});
		
		_panel.add(_comboBox, "2, 8, fill, default");
		
		_panel.add(Boton.generar("OK", new Dimensiones(0, 0, 0, 0), new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				agregarMiembro();
			}
			
		}), "4, 10");
		
		_panel.add(Boton.generar("Cancelar", new Dimensiones(0, 0, 0, 0), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
			
		}), "6, 10");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<ComboItem> listarRoles() {
		List<ComboItem> items = Arrays.asList(
				new ComboItem(Roles.TESTER, "Tester"),
				new ComboItem(Roles.PROGRAMADOR, "Programador"),
				new ComboItem(Roles.ARQUITECTO, "Arquitecto"),
				new ComboItem(Roles.LIDER_PROYECTO, "Lider de Proyecto"));
		
		return items;
	}

	private void agregarMiembro() {
		if(nombreEsValido()) {
			MiembrosDisponibles miembros = MiembrosDisponibles.obtenerInstancia();
			
			miembros.agregar(new Miembro(_rol, _nombre.getText()));
			
			Alerta.mostrar("Miembro registrado con éxito");
			
			limpiar();
		}
	}
	
	private boolean nombreEsValido() {
		if (Utilidad.CadenaTexto.esVaciaONula(_nombre.getText())) {
			Alerta.mostrar("Nombre no puede estar vacío");
			return false;
		}
		
		String pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(_nombre.getText());
		
		if (!m.matches()) {
			Alerta.mostrar("Formato de nombre inválido");
			return false;
		}
		
		return true;
	}
	
	private void limpiar() {
		_nombre.setText("");
		_rol = Roles.TESTER;
		_comboBox.setSelectedIndex(0);
	}
}
