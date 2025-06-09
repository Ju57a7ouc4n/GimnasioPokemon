package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modeloEntrenador.Entrenador;
import modeloMundo.Mundo;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class Ventana_Gimnasio extends JFrame implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;
	private JPanel Ventana_Gimnasio_Principal;
	private JPanel panel_Titulo;
	private JLabel lblPokmonGym;
	private JPanel panel_Principal;
	private JPanel panel_Entrenadores;
	private JPanel panel_Acciones_Principales;
	private JPanel panel_Compras;
	private JPanel panel_Entrenador;
	private JPanel panel_Mercado;
	private JPanel panel_Entra_Torneo;
	private JPanel panel_Datos_Entrenador;
	private JPanel panel_Crea_Elimina_Buttons;
	private JLabel Nombre_Label;
	private JTextField Nombre_Entrenador_TextField;
	private JButton Crea_Entrenador_Button;
	private JButton Elimina_Entrenador_Button;
	private JButton confirmaCompraButton;
	private JButton Entra_Torneo_Button; 
	private JLabel Nombre_Pokemon_Label;
	private static Mundo controlador;
	private JLabel EntrenadoresFaltantes_Label;
	private JList Entrenadores_List;
	private DefaultListModel<String> entrenadoresModel;
	private JButton GuardarPartidaButton;
	private JButton CargarPartidaButton;
	private JPanel panel;
	private JPanel panel_1;
	private JComboBox<String> entrenadorCombo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Gimnasio frame = new Ventana_Gimnasio(controlador);
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
	public Ventana_Gimnasio(Mundo control) {
		
		controlador = control; 
		
		setSize(1000,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setIconImage(new ImageIcon("/Images/Imagen_Ventana_Inicio.PNG").getImage());
		//setIconImage(Icono_Ventana.getIconoVentana());
		
		getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		
		this.Ventana_Gimnasio_Principal = new JPanel();
		this.Ventana_Gimnasio_Principal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.Ventana_Gimnasio_Principal);
		this.Ventana_Gimnasio_Principal.setLayout(new BorderLayout(0, 0));
		
		this.panel_Titulo = new JPanel();
		this.Ventana_Gimnasio_Principal.add(this.panel_Titulo, BorderLayout.NORTH);
		
		//inicio bloque titulo vetnana
		this.lblPokmonGym = new JLabel("Pokémon Gym");
		this.lblPokmonGym.setFont(new Font("Arial Black", Font.BOLD, 16));
		this.panel_Titulo.add(this.lblPokmonGym);
		//fin bloque titulo ventana
		
		////inicio bloque panel principal centro
		this.panel_Principal = new JPanel();
		this.Ventana_Gimnasio_Principal.add(this.panel_Principal, BorderLayout.CENTER);
		this.panel_Principal.setLayout(new GridLayout(1, 3, 0, 0));
		
		//inicio columna 1 lista de entrenadores
		this.panel_Entrenadores = new JPanel();
		this.panel_Principal.add(this.panel_Entrenadores);
		this.entrenadoresModel = new DefaultListModel<>();
		this.panel_Entrenadores.setLayout(new GridLayout(0, 1, 0, 0));
		this.Entrenadores_List = new JList<>(entrenadoresModel);
		this.Entrenadores_List.setPreferredSize(new Dimension(200, 400)); // opcional
		this.panel_Entrenadores.add(this.Entrenadores_List);
		
		this.panel = new JPanel();
		this.panel_Entrenadores.add(this.panel);
		this.panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1);
		
		this.GuardarPartidaButton = new JButton("Guardar Partida");
		this.panel_1.add(this.GuardarPartidaButton);
		
		this.CargarPartidaButton = new JButton("Cargar Partida");
		this.panel_1.add(this.CargarPartidaButton);
		CargarPartidaButton.addActionListener(e -> {
		    JFileChooser fileChooser = new JFileChooser("saves");
		    fileChooser.setDialogTitle("Seleccionar partida guardada");
		    int resultado = fileChooser.showOpenDialog(null);

		    if (resultado == JFileChooser.APPROVE_OPTION) {
		        File archivoSeleccionado = fileChooser.getSelectedFile();
		        controlador.cargarPartida(archivoSeleccionado);
		        controlador.reiniciarGimnasio(this);
		    }
		});
		GuardarPartidaButton.addActionListener(e -> {
		    String nombre = JOptionPane.showInputDialog("Ingrese un nombre para guardar la partida:");
		    if (nombre != null && !nombre.isEmpty()) {
		        controlador.guardarPartida(nombre);
		    }
		});
		
		//fin columna 1 lista de entrenadores
		
		//inicio columna 2 panel de acciones
		this.panel_Acciones_Principales = new JPanel();
		this.panel_Principal.add(this.panel_Acciones_Principales);
		this.panel_Acciones_Principales.setLayout(new BoxLayout(this.panel_Acciones_Principales, BoxLayout.Y_AXIS));
		//fin columna 2 panel de acciones
		
		//inicio columna 2
		this.panel_Entrenador = new JPanel();
		this.panel_Acciones_Principales.add(this.panel_Entrenador);
		this.panel_Acciones_Principales.add(Box.createVerticalStrut(20));
		this.panel_Entrenador.setLayout(new GridLayout(2, 1, 0, 0));
		//this.panel_Entrenador.setLayout(new GridLayout(2, 1, 0, 0));		
		
		//inicio panel columna 2 con JLabel y TextField
		this.panel_Datos_Entrenador = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panel_Datos_Entrenador.getLayout();
		flowLayout.setVgap(10);
		this.panel_Entrenador.add(this.panel_Datos_Entrenador);
		
		this.Nombre_Label = new JLabel("Nombre Entrenador");
		this.panel_Datos_Entrenador.add(this.Nombre_Label);
		
		this.Nombre_Entrenador_TextField = new JTextField();
		this.panel_Datos_Entrenador.add(this.Nombre_Entrenador_TextField);
		this.Nombre_Entrenador_TextField.setColumns(10);
		

		// DocumentListener para habilitar/deshabilitar el botón
		this.Nombre_Entrenador_TextField.getDocument().addDocumentListener(new DocumentListener() {
		    public void insertUpdate(DocumentEvent e) {
		        actualizarEstadoBotonCrear();
		    }

		    public void removeUpdate(DocumentEvent e) {
		        actualizarEstadoBotonCrear();
		    }

		    public void changedUpdate(DocumentEvent e) {
		        actualizarEstadoBotonCrear();
		    }

		    private void actualizarEstadoBotonCrear() {
		        String texto = Nombre_Entrenador_TextField.getText().trim();
		        Crea_Entrenador_Button.setEnabled(!texto.isEmpty());
		    }
		});
		//fin panel columna 2 con JLabel y TextField
		
		//inicio panel columna 2 con botones de crear y eliminar entrenadores
		this.panel_Crea_Elimina_Buttons = new JPanel();
		this.panel_Entrenador.add(this.panel_Crea_Elimina_Buttons);
		this.panel_Crea_Elimina_Buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		this.Crea_Entrenador_Button = new JButton("Crea Entrenador");
		this.Crea_Entrenador_Button.addActionListener(this);
		this.panel_Crea_Elimina_Buttons.add(this.Crea_Entrenador_Button);
		this.Crea_Entrenador_Button.setEnabled(false);
		
		this.Elimina_Entrenador_Button = new JButton("Elimina Entrenador");
		this.panel_Crea_Elimina_Buttons.add(this.Elimina_Entrenador_Button);
		this.Elimina_Entrenador_Button.addActionListener(e -> {
		    int selectedIndex = this.Entrenadores_List.getSelectedIndex();
		    if (selectedIndex != -1) {
		        controlador.eliminarEntrenador(selectedIndex);
		        }
		});
		this.Crea_Entrenador_Button.setPreferredSize(new Dimension(150, 30));
		this.Elimina_Entrenador_Button.setPreferredSize(new Dimension(150, 30));
		this.panel_Entrenador.setBorder(BorderFactory.createTitledBorder("Gestión de Entrenadores"));

		
		//fin panel columna 2 con botones de crear y eliminar entrenadores
		
		//inicio panel de botones mercado
		this.panel_Mercado = new JPanel();
		this.panel_Acciones_Principales.add(this.panel_Mercado);
		this.panel_Acciones_Principales.add(Box.createVerticalStrut(20));
		this.panel_Mercado.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.panel_Compras = new JPanel();
		this.panel_Principal.add(this.panel_Compras);
		JButton Mercado_Button = new JButton("Mercado");
		this.panel_Mercado.add(Mercado_Button);
		Mercado_Button.addActionListener(e -> {
		    // Limpiar el panel de compras
		    panel_Compras.removeAll();
		    panel_Compras.setLayout(new BoxLayout(panel_Compras, BoxLayout.Y_AXIS));

		    // ======= SELECCIÓN DE ENTRENADOR =======
		    this.entrenadorCombo = new JComboBox<>(new DefaultComboBoxModel<>());
		    entrenadorCombo.setMaximumSize(new Dimension(200, 30));
		    JLabel labelSeleccion = new JLabel("Seleccioná un Entrenador");
		    labelSeleccion.setAlignmentX(Component.CENTER_ALIGNMENT);
		    this.panel.add(labelSeleccion);
		    this.panel.add(entrenadorCombo);
		    
		    
		 // ======= COMPRA DE ARMAS =======
		    JLabel tituloArma = new JLabel("Compra de Armas");
		    tituloArma.setAlignmentX(Component.CENTER_ALIGNMENT);

		    JComboBox<String> armaCombo = new JComboBox<>(new String[]{"Sin Arma","Espada", "Hacha"});
		    armaCombo.setMaximumSize(new Dimension(200, 30));

		    JButton comprarArmaButton = new JButton("Comprar Arma");
		    comprarArmaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		    armaCombo.setEnabled(false);
	    	comprarArmaButton.setEnabled(false);
		    comprarArmaButton.addActionListener(ev -> {
		        String entrenadorNombre = (String) entrenadorCombo.getSelectedItem();
		        if (entrenadorNombre.equals("-- Seleccionar --")) {
		            JOptionPane.showMessageDialog(this, "Primero debés seleccionar un entrenador.");
		            return;
		        }

		        String arma = (String) armaCombo.getSelectedItem();
		        if(arma.equals("Espada")| arma.equals("Hacha")) {
		        	try {
		        		controlador.comprarArma(entrenadorNombre, arma); // ← lógica real
		        		JOptionPane.showMessageDialog(this, entrenadorNombre + " compró un arma: " + arma);

		        		armaCombo.setEnabled(false);
		        		comprarArmaButton.setEnabled(false);
		        	} catch (Exception ex) {
		        		JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
		        	}
		        }
		    });
		    
		    // ======= COMPRA DE POKÉMON =======
		    JLabel tituloPokemon = new JLabel("Compra de Pokémon");
		    tituloPokemon.setAlignmentX(Component.CENTER_ALIGNMENT);

		    JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Agua", "Fuego", "Hielo", "Piedra"});
		    tipoCombo.setMaximumSize(new Dimension(200, 30));
		    tipoCombo.addItemListener(u -> {
		        if (u.getStateChange() == ItemEvent.SELECTED) {
		            String tipoSeleccionado = (String) tipoCombo.getSelectedItem();
		            if (tipoSeleccionado.equalsIgnoreCase("Piedra")) {
		                armaCombo.setEnabled(true);
		                comprarArmaButton.setEnabled(true);
		            } else {
		                armaCombo.setEnabled(false);
		                comprarArmaButton.setEnabled(false);
		            }
		        }
		    });
		    JLabel nombreLabel = new JLabel("Nombre Pokémon");
		    nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		    JTextField nombrePokemon = new JTextField();
		    nombrePokemon.setMaximumSize(new Dimension(200, 30));

		    JButton comprarPokemonButton = new JButton("Comprar Pokémon");
		    comprarPokemonButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		    comprarPokemonButton.addActionListener(ev -> {
		        String entrenadorNombre = (String) entrenadorCombo.getSelectedItem();
		        if (entrenadorNombre.equals("-- Seleccionar --")) {
		            JOptionPane.showMessageDialog(this, "Primero debés seleccionar un entrenador.");
		            return;
		        }

		        String tipo = (String) tipoCombo.getSelectedItem();
		        String nombre = nombrePokemon.getText().trim();

		        if (nombre.isEmpty()) {
		            JOptionPane.showMessageDialog(this, "Debés ingresar un nombre para el Pokémon.");
		            return;
		        }

		        try {
		            controlador.comprarPokemon(entrenadorNombre, tipo, nombre); // ← lógica real
		            JOptionPane.showMessageDialog(this, entrenadorNombre + " compró un Pokémon de tipo " + tipo + " llamado " + nombre);

		            nombrePokemon.setText(""); // Limpiar campo
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
		        }
		    });
		    
		    // ======= Agregar todo al panel =======
		    panel_Compras.add(Box.createVerticalStrut(10));
		    panel_Compras.add(labelSeleccion);
		    panel_Compras.add(entrenadorCombo);
		    panel_Compras.add(Box.createVerticalStrut(20));

		    panel_Compras.add(tituloPokemon);
		    panel_Compras.add(tipoCombo);
		    panel_Compras.add(nombreLabel);
		    panel_Compras.add(nombrePokemon);
		    panel_Compras.add(comprarPokemonButton);
		    panel_Compras.add(Box.createVerticalStrut(20));

		    panel_Compras.add(tituloArma);
		    panel_Compras.add(armaCombo);
		    panel_Compras.add(comprarArmaButton);
		    panel_Compras.add(Box.createVerticalGlue());

		    // Actualizar interfaz
		    panel_Compras.revalidate();
		    panel_Compras.repaint();
		});
		this.panel_Mercado.setBorder(BorderFactory.createTitledBorder("Mercado"));


		//fin panel de botones mercado
		
		
		
		//inicio panel de boton de entrar al torneo
		this.panel_Entra_Torneo = new JPanel();
		this.panel_Acciones_Principales.add(this.panel_Entra_Torneo);
		this.panel_Entra_Torneo.setLayout(new GridLayout(2, 1, 0, 0));
		this.Entra_Torneo_Button = new JButton("Entrar Torneo");
		this.panel_Entra_Torneo.add(this.Entra_Torneo_Button);
		this.panel_Entra_Torneo.setBorder(BorderFactory.createTitledBorder("Torneo"));
		this.Entra_Torneo_Button.addActionListener(this);
		this.Entra_Torneo_Button.setEnabled(false);
		
		if(!this.Entra_Torneo_Button.isEnabled()) {
			this.EntrenadoresFaltantes_Label = new JLabel("Se deben crear 8 entrenadores para acceder al torneo!");
			this.EntrenadoresFaltantes_Label.setHorizontalAlignment(SwingConstants.CENTER);
			this.panel_Entra_Torneo.add(this.EntrenadoresFaltantes_Label);
		}
		//fin panel de boton de entrar al torneo
		
	
		
		
		
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == this.Crea_Entrenador_Button) {
	        String nombre = this.Nombre_Entrenador_TextField.getText().trim();
	        controlador.agregarEntrenador(nombre);
	        JOptionPane.showMessageDialog(this, "Entrenador '" + nombre + "' creado.");
	        this.Nombre_Entrenador_TextField.setText("");
	    }

	    if (e.getSource() == this.Entra_Torneo_Button) {
	        new Ventana_Preparar_Entrenadores(controlador).setVisible(true);;
	        dispose();
	    }
	}

	@Override
	public void update(Observable o, Object arg) {
	    entrenadoresModel.clear();

	    for (Entrenador entrenador : controlador.obtenerListaEntrenadores()) {
	        entrenadoresModel.addElement(entrenador.getNombre());
	    }

	    // Actualiza combo del mercado también (si existe)
	    if (entrenadorCombo != null) {
	        entrenadorCombo.removeAllItems();
	        for (Entrenador entrenador : controlador.obtenerListaEntrenadores()) {
	            entrenadorCombo.addItem(entrenador.getNombre());
	        }
	    }

	    // Habilitar botón si hay 8 entrenadores
	    if (controlador.obtenerListaEntrenadores().size() == 8) {
	        Entra_Torneo_Button.setEnabled(true);
	        if (EntrenadoresFaltantes_Label != null)
	            EntrenadoresFaltantes_Label.setText("");
	    } else {
	        Entra_Torneo_Button.setEnabled(false);
	        if (EntrenadoresFaltantes_Label != null)
	            EntrenadoresFaltantes_Label.setText("Se deben crear 8 entrenadores para acceder al torneo!");
	    }
	}
}
