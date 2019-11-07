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
	
	private JTextField nombre;
	private Roles rol;
	private JPanel panel;
	private JLabel lblRol;
	
	public AgregarPersona() {
		rol = Roles.TESTER;
		
		inicializarLayout();
		
		cargarControles();		
	}
	
	private void inicializarLayout() {
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
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
		JLabel lblNewLabel = new JLabel("Nombre y apellido:");
		panel.add(lblNewLabel, "2, 2");
		
		nombre = new JTextField();
		panel.add(nombre, "2, 4, fill, default");
		nombre.setColumns(10);
		
		lblRol = new JLabel("Rol:");
		panel.add(lblRol, "2, 6");
		
		panel.add(Combo.generar(new Dimensiones(5, 28, 130, 20), listarRoles(), new ActionListener() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) {			
				JComboBox c = (JComboBox) e.getSource();
				ComboItem<Roles> item = (ComboItem<Roles>) c.getSelectedItem();
				
				rol = item.obtenerId();
			}
		}), "2, 8, fill, default");
		
		panel.add(Boton.generar("OK", new Dimensiones(0, 0, 0, 0), new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean exito = agregarMiembro();
				
				if (exito) ocultarFrame();
			}
			
		}), "4, 10");
		
		panel.add(Boton.generar("Cancelar", new Dimensiones(0, 0, 0, 0), new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ocultarFrame();
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

	private boolean agregarMiembro() {
		if(nombreEsValido()) {
			MiembrosDisponibles miembros = MiembrosDisponibles.obtenerInstancia();
			
			miembros.agregar(new Miembro(rol, nombre.getText()));
			
			Alerta.mostrar("Miembro registrado con éxito");
			
			return true;
		}
		
		return false;
	}
	
	private boolean nombreEsValido() {
		if (Utilidad.CadenaTexto.esVaciaONula(nombre.getText())) {
			Alerta.mostrar("Nombre no puede estar vacío");
			return false;
		}
		
		String pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(nombre.getText());
		
		if (!m.matches()) {
			Alerta.mostrar("Formato de nombre inválido");
			return false;
		}
		
		return true;
	}
	
	private void ocultarFrame() {
		setVisible(false);
	}
}
