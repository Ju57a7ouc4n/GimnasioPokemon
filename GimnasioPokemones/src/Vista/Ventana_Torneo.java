package Vista;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.JButton;


public class Ventana_Torneo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel_titulo_torneo;
	private JLabel bienvenido_torneo_label;
	private JPanel panel_torneo;
	private JPanel panel_lista_entrenadores_disponibles;
	private JPanel panel_acciones_torneo;
	private JPanel panel_lista_pokemones_seleccionar;
	private JPanel panel_carga_pokemones;
	private JPanel panel_inicia_torneo;
	private JPanel panel_boton_carga_pokemones;
	private JButton confirma_pokemones_Button;
	private JButton iniciar_ronda_Button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Torneo frame = new Ventana_Torneo();
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
	public Ventana_Torneo() {
		setSize(1000,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setIconImage(new ImageIcon("E:\\GimnasioPokemon-main\\Images\\Imagen_Ventana_Inicio.PNG").getImage());
		setIconImage(Icono_Ventana.getIconoVentana());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.panel_titulo_torneo = new JPanel();
		getContentPane().add(this.panel_titulo_torneo, BorderLayout.NORTH);
		
		this.bienvenido_torneo_label = new JLabel("Bienvenido al torneo");
		this.bienvenido_torneo_label.setFont(new Font("Arial Black", Font.BOLD, 24));
		this.panel_titulo_torneo.add(this.bienvenido_torneo_label);
		
		this.panel_torneo = new JPanel();
		getContentPane().add(this.panel_torneo, BorderLayout.CENTER);
		this.panel_torneo.setLayout(new GridLayout(1, 2, 0, 0));
		
		this.panel_lista_entrenadores_disponibles = new JPanel();
		this.panel_torneo.add(this.panel_lista_entrenadores_disponibles);
		this.panel_lista_entrenadores_disponibles.setLayout(new BorderLayout(0, 0));
		
		this.panel_lista_pokemones_seleccionar = new JPanel();
		this.panel_lista_entrenadores_disponibles.add(this.panel_lista_pokemones_seleccionar, BorderLayout.CENTER);
		this.panel_lista_pokemones_seleccionar.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.panel_lista_pokemones_seleccionar.setBorder(BorderFactory.createTitledBorder("Lista de pokemones"));
		
		
		this.panel_acciones_torneo = new JPanel();
		this.panel_torneo.add(this.panel_acciones_torneo);
		this.panel_acciones_torneo.setLayout(new GridLayout(3, 1, 0, 0));
		
		this.panel_carga_pokemones = new JPanel();
		this.panel_acciones_torneo.add(this.panel_carga_pokemones);
		this.panel_carga_pokemones.setLayout(new BorderLayout(0, 0));
		
		this.panel_boton_carga_pokemones = new JPanel();
		this.panel_boton_carga_pokemones.setBorder(BorderFactory.createTitledBorder("Seleccion de Pokémones"));
		this.panel_carga_pokemones.add(this.panel_boton_carga_pokemones, BorderLayout.CENTER);
		
		
		this.confirma_pokemones_Button = new JButton("Carga Pokemones");
		this.confirma_pokemones_Button.setPreferredSize(new Dimension(150,50));
		this.panel_boton_carga_pokemones.add(this.confirma_pokemones_Button);
		this.confirma_pokemones_Button.setEnabled(false);
		
		this.panel_inicia_torneo = new JPanel();
		this.panel_inicia_torneo.setBorder(BorderFactory.createTitledBorder("Inicia la Ronda"));
		this.panel_acciones_torneo.add(this.panel_inicia_torneo);
		
		this.iniciar_ronda_Button = new JButton("Iniciar Ronda");
		this.iniciar_ronda_Button.setFont(new Font("Arial Black", Font.BOLD, 14));
		this.iniciar_ronda_Button.setPreferredSize(new Dimension(150,50));
		this.panel_inicia_torneo.add(this.iniciar_ronda_Button);
		

		
		
		
		setVisible(true);
	}

}
