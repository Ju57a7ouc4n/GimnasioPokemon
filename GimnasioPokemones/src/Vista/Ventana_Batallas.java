package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modeloArenas.Arena;
import modeloArenas.IArena;
import modeloMundo.Mundo;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Ventana_Batallas extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton ContinuarButton;
	private JButton AbrirTiendaButton;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JTextArea textArea_3;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private List<JTextArea> areasTexto;
	private JTextArea textArea_2;
	private static Mundo controlador;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana_Batallas frame = new Ventana_Batallas(controlador);
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
	public Ventana_Batallas(Mundo control) {
		controlador = control;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1081, 685);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.SOUTH);
		
		this.ContinuarButton = new JButton("Iniciar Proxima Ronda");
		this.panel.add(this.ContinuarButton);
		
		this.AbrirTiendaButton = new JButton("Abrir Tienda");
		this.panel.add(this.AbrirTiendaButton);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1, BorderLayout.CENTER);
		this.panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		this.panel_5 = new JPanel();
		this.panel_1.add(this.panel_5);
		this.panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		this.textArea = new JTextArea();
		this.panel_5.add(this.textArea);
		
		this.panel_4 = new JPanel();
		this.panel_1.add(this.panel_4);
		this.panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		this.textArea_1 = new JTextArea();
		this.panel_4.add(this.textArea_1);
		
		this.panel_3 = new JPanel();
		this.panel_1.add(this.panel_3);
		this.panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		this.textArea_2 = new JTextArea();
		this.panel_3.add(this.textArea_2);
		
		this.panel_2 = new JPanel();
		this.panel_1.add(this.panel_2);
		this.panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		this.textArea_3 = new JTextArea();
		this.panel_2.add(this.textArea_3);
		
		controlador.iniciarBatallas(this);
		areasTexto = List.of(textArea, textArea_1, textArea_2, textArea_3);

	    controlador.iniciarBatallas(this);

	    actualizarPanelesConArenas();
	}

	@Override
	public void update(Observable o, Object arg) {
	    actualizarPanelesConArenas();
	}
	
	private void actualizarPanelesConArenas() {
	    List<IArena> arenas = controlador.getArenas();

	    for (int i = 0; i < areasTexto.size(); i++) {
	        JTextArea area = areasTexto.get(i);

	        if (i < arenas.size()) {
	            IArena arena = arenas.get(i);

	            // Ejemplo: mostrar nombre y algún detalle
	            String texto = "Nombre: " + arena.getNombre() + "\n";

	            area.setText(texto);
	        } else {
	            area.setText("Sin arena asignada");
	        }
	    }
	}

}
