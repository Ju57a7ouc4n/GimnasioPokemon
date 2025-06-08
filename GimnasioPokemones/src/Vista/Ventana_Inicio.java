package Vista;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import modeloMundo.Mundo;
import modeloTorneo.Torneo;

public class Ventana_Inicio extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel panel_Imagen;
	private JPanel panel_Titulo;
	private JPanel panel_Boton_Inicia_Juego;
	private JLabel labelPokemonGym;
	private JButton botonIniciarGimnasio;

	private Mundo controlador;

	public Ventana_Inicio(Mundo controlador) {
		this.controlador = controlador;

		setTitle("Pokémon Gym");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new GridLayout(3, 1));

		// Título
		this.panel_Titulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 70));
		this.labelPokemonGym = new JLabel("POKÉMON GYM");
		this.labelPokemonGym.setFont(new Font("Arial Black", Font.BOLD, 30));
		this.panel_Titulo.add(this.labelPokemonGym);
		getContentPane().add(this.panel_Titulo);

		//Icono
		/*
		ImageIcon icono = new ImageIcon(getClass().getResource("Images/Imagen_Ventana_Inicio.png"));
		Image imagenEscalada = icono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel imagenLabel = new JLabel(new ImageIcon(imagenEscalada));
		this.panel_Imagen.add(imagenLabel);
		getContentPane().add(this.panel_Imagen);

		// Icono de la ventana
		setIconImage(icono.getImage());
		*/
		// Botón
		this.panel_Boton_Inicia_Juego = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 70));
		this.botonIniciarGimnasio = new JButton("INICIAR GIMNASIO");
		this.botonIniciarGimnasio.setFont(new Font("Arial Black", Font.PLAIN, 16));
		this.botonIniciarGimnasio.setEnabled(true);
		this.botonIniciarGimnasio.addActionListener(this);
		this.panel_Boton_Inicia_Juego.add(this.botonIniciarGimnasio);
		getContentPane().add(this.panel_Boton_Inicia_Juego);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.botonIniciarGimnasio) {
			this.controlador.iniciarGimnasio(); // delega en el controlador
			dispose(); // cierra la ventana actual
		}
	}
}