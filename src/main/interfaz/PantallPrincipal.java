package main.interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import main.interfaz.controles.Desktop;
import main.util.Utilidad;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PantallPrincipal extends JFrame {

	private JDesktopPane _desktop;
	private AgregarPersona _agregarPersona;
	private ListarPersonas _listarPersonas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallPrincipal frame = new PantallPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallPrincipal() {
		super("Team ideal");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 593);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPersonas = new JMenu("Personas");
		menuBar.add(mnPersonas);
		
		JMenuItem mntmAgregar = new JMenuItem("Agregar");
		mntmAgregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Utilidad.Objeto.esNulo(_agregarPersona)) {
						_agregarPersona = new AgregarPersona();
						_agregarPersona.setBounds(15, 15, 475, 180);
						_desktop.add(_agregarPersona);
					}
					
					_agregarPersona.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		mnPersonas.add(mntmAgregar);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (Utilidad.Objeto.esNulo(_listarPersonas)) {
						_listarPersonas = new ListarPersonas();
						_listarPersonas.setBounds(15, 15, 385, 270);
						_desktop.add(_listarPersonas);
					}
					
					_listarPersonas.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		mnPersonas.add(mntmListar);
		
		JMenu mnEquipo = new JMenu("Equipo");
		menuBar.add(mnEquipo);
		
		JMenuItem mnRequisitos = new JMenuItem("Establecer requisitos");
		mnEquipo.add(mnRequisitos);
		
		JMenuItem mnGestionar = new JMenuItem("Gestionar");
		mnEquipo.add(mnGestionar);
		
		_desktop = new Desktop();	
		_desktop.setBackground(Color.WHITE);
		setContentPane(_desktop);
	}

}
