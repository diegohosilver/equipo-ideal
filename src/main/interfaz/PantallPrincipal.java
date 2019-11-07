package main.interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import main.interfaz.controles.Desktop;
import main.util.Utilidad;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PantallPrincipal extends JFrame {

	private JDesktopPane desktop;
	private AgregarPersona agregarPersona;

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
					if (Utilidad.Objeto.esNulo(agregarPersona)) {
						agregarPersona = new AgregarPersona();
						agregarPersona.setBounds(15, 15, 475, 180);
						desktop.add(agregarPersona);
					}
					
					agregarPersona.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		});
		mnPersonas.add(mntmAgregar);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mnPersonas.add(mntmListar);
		
		JMenu mnEquipo = new JMenu("Equipo");
		menuBar.add(mnEquipo);
		
		JMenu mnRequisitos = new JMenu("Establecer requisitos");
		mnEquipo.add(mnRequisitos);
		
		JMenu mnGestionar = new JMenu("Gestionar");
		mnEquipo.add(mnGestionar);
		
		desktop = new Desktop();	
		desktop.setBackground(Color.WHITE);
		setContentPane(desktop);
	}

}
