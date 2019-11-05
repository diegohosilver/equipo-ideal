package main.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.interfaz.controles.Panel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class PantallPrincipal extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 842, 593);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPersonas = new JMenu("Personas");
		menuBar.add(mnPersonas);
		
		JMenuItem mntmAgregar = new JMenuItem("Agregar");
		mnPersonas.add(mntmAgregar);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mnPersonas.add(mntmListar);
		
		JMenu mnEquipo = new JMenu("Equipo");
		menuBar.add(mnEquipo);
		
		JMenu mnRequisitos = new JMenu("Establecer requisitos");
		mnEquipo.add(mnRequisitos);
		
		JMenu mnGestionar = new JMenu("Gestionar");
		mnEquipo.add(mnGestionar);
		contentPane = new Panel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
	}

}
