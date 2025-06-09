package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloMundo.Mundo;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class Ventana_Preparar_Entrenadores extends JFrame implements ActionListener, Observer  {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Mundo controlador;
	private JPanel PanelEntrenadores;
	private JPanel PanelPokemonesDisponibles;
	private JPanel PanelPokemonesCombate;
	private JList<String> ListaDeEntrenadores;
	private JList<String> ListaDePokemonesDisponibles;
	private JList<String> ListaDePokemonesParaCombate;
	private JButton AnadirACombateButton;
	private JButton QuitarDelCombateButton;
	private JButton IniciarCombatesButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Preparar_Entrenadores frame = new Ventana_Preparar_Entrenadores(controlador);
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
	public Ventana_Preparar_Entrenadores(Mundo control) {
		controlador = control;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 752);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(1, 3, 0, 0));
		
		this.PanelEntrenadores = new JPanel();
		this.contentPane.add(this.PanelEntrenadores);
		this.PanelEntrenadores.setBorder(BorderFactory.createTitledBorder("Entrenador"));
		this.PanelEntrenadores.setLayout(new BorderLayout(0, 0));
		
		this.ListaDeEntrenadores = new JList();
		this.PanelEntrenadores.add(this.ListaDeEntrenadores, BorderLayout.CENTER);
		
		this.IniciarCombatesButton = new JButton("Iniciar Combates");
		this.PanelEntrenadores.add(this.IniciarCombatesButton, BorderLayout.SOUTH);
		IniciarCombatesButton.addActionListener(this);
		
		
		
		this.PanelPokemonesDisponibles = new JPanel();
		this.contentPane.add(this.PanelPokemonesDisponibles);
		this.PanelPokemonesDisponibles.setBorder(BorderFactory.createTitledBorder("Pokemones Disponibles"));
		this.PanelPokemonesDisponibles.setLayout(new BorderLayout(0, 0));
		
		this.ListaDePokemonesDisponibles = new JList();
		this.PanelPokemonesDisponibles.add(this.ListaDePokemonesDisponibles, BorderLayout.CENTER);
		this.ListaDeEntrenadores.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		        int selectedIndex = ListaDeEntrenadores.getSelectedIndex();
		        if (selectedIndex != -1) {
		            List<String> nombresPokemones = controlador.getPokemonesDelEntrenador(selectedIndex);
		            ListaDePokemonesDisponibles.setListData(nombresPokemones.toArray(new String[0]));

		            List<String> nombresCombate = controlador.getPokemonesParaCombateDelEntrenador(selectedIndex);
		            ListaDePokemonesParaCombate.setListData(nombresCombate.toArray(new String[0]));
		        }
		    }
		});
		
		this.AnadirACombateButton = new JButton("Añadir al Combate");
		this.PanelPokemonesDisponibles.add(this.AnadirACombateButton, BorderLayout.SOUTH);
		this.AnadirACombateButton.addActionListener(e -> {
		    int indexEntrenador = ListaDeEntrenadores.getSelectedIndex();
		    int indexPokemon = ListaDePokemonesDisponibles.getSelectedIndex();

		    if (indexEntrenador != -1 && indexPokemon != -1) {
		        controlador.anadirPokemonACombate(indexEntrenador, indexPokemon);
		    }
		});
		
		
		this.PanelPokemonesCombate = new JPanel();
		this.contentPane.add(this.PanelPokemonesCombate);
		this.PanelPokemonesCombate.setBorder(BorderFactory.createTitledBorder("Pokemones Para Combatir"));
		this.PanelPokemonesCombate.setLayout(new BorderLayout(0, 0));
		
		this.ListaDePokemonesParaCombate = new JList();
		this.PanelPokemonesCombate.add(this.ListaDePokemonesParaCombate, BorderLayout.CENTER);
		
		this.QuitarDelCombateButton = new JButton("Quitar del Combate");
		this.QuitarDelCombateButton.addActionListener(this);
		this.PanelPokemonesCombate.add(this.QuitarDelCombateButton, BorderLayout.SOUTH);
		this.QuitarDelCombateButton.addActionListener(e -> {
		    int indexEntrenador = ListaDeEntrenadores.getSelectedIndex();
		    int indexPokemon = ListaDePokemonesParaCombate.getSelectedIndex();

		    if (indexEntrenador != -1 && indexPokemon != -1) {
		        controlador.quitarPokemonDeCombate(indexEntrenador, indexPokemon);
		    }
		});
		controlador.iniciarPreparar(this);
	}

	public void update(Observable o, Object arg) {
	    // Actualizar la lista de entrenadores
	    List<String> nombresEntrenadores = controlador.getNombresEntrenadores();
	    this.ListaDeEntrenadores.setListData(nombresEntrenadores.toArray(new String[0]));
	    
	    // Si hay un entrenador seleccionado, actualizar sus Pokémon
	    int selectedIndex = this.ListaDeEntrenadores.getSelectedIndex();
	    if (selectedIndex != -1) {
	        List<String> nombresPokemones = controlador.getPokemonesDelEntrenador(selectedIndex);
	        this.ListaDePokemonesDisponibles.setListData(nombresPokemones.toArray(new String[0]));

	        List<String> nombresParaCombate = controlador.getPokemonesParaCombateDelEntrenador(selectedIndex);
	        this.ListaDePokemonesParaCombate.setListData(nombresParaCombate.toArray(new String[0]));
	    } else {
	        this.ListaDePokemonesDisponibles.setListData(new String[0]);
	        this.ListaDePokemonesParaCombate.setListData(new String[0]);
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e){
	    Object source = e.getSource();

	    int indexEntrenador = this.ListaDeEntrenadores.getSelectedIndex();

	    if (indexEntrenador == -1) {
	        return; // No hay entrenador seleccionado
	    }

	    if (source == this.AnadirACombateButton) {
	        int indexPokemon = this.ListaDePokemonesDisponibles.getSelectedIndex();
	        if (indexPokemon != -1) {
	            controlador.anadirPokemonACombate(indexEntrenador, indexPokemon);
	        }
	    }

	    if (source == this.QuitarDelCombateButton) {
	        int indexPokemon = this.ListaDePokemonesParaCombate.getSelectedIndex();
	        if (indexPokemon != -1) {
	            controlador.quitarPokemonDeCombate(indexEntrenador, indexPokemon);
	        }
	    }
	    if (source == this.IniciarCombatesButton) {
			new Ventana_Batallas(controlador).setVisible(true);
	        this.dispose();
	    }
	}

}
