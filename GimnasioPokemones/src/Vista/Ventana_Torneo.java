package Vista;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Ventana_Torneo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel_Entrenadores_Disponibles;
	private JPanel panel_Acciones_Torneo;

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
		//setIconImage(new ImageIcon("/Images/Imagen_Ventana_Inicio.PNG").getImage());
		
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		this.panel_Entrenadores_Disponibles = new JPanel();
		getContentPane().add(this.panel_Entrenadores_Disponibles);
		

		
		this.panel_Acciones_Torneo = new JPanel();
		getContentPane().add(this.panel_Acciones_Torneo);
		

		
		
		
		setVisible(true);
	}

}
