package Vista;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modeloEntrenador.Entrenador;
import modeloExcepciones.CompraImposibleException;
import modeloMundo.Mundo;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class Ventana_Tienda extends JDialog implements Observer {

    private JComboBox<String> comboEntrenadores;
    private JTextField nombrePokemonField;
    private JComboBox<String> comboTipos;
    private JComboBox<String> comboArmas;
    private JButton botonComprar;
    private JButton botonCerrar;
    private Mundo controlador;
    
    public Ventana_Tienda(JFrame parent, Mundo control) {
        super(parent, "Tienda", true); // true = modal
        controlador=control;
        controlador.iniciarTienda(this);
        this.setSize(400, 300);
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(7, 2, 5, 5));

        // ComboBox de Entrenadores
        getContentPane().add(new JLabel("Entrenador:"));
        comboEntrenadores = new JComboBox<>();
        actualizarComboEntrenadores();
        getContentPane().add(comboEntrenadores);

        // Campo de nombre de Pokémon
        getContentPane().add(new JLabel("Nombre del Pokémon:"));
        nombrePokemonField = new JTextField();
        getContentPane().add(nombrePokemonField);
        nombrePokemonField.getDocument().addDocumentListener(new DocumentListener() {
            private void actualizarEstadoBoton() {
                String texto = nombrePokemonField.getText().trim();
                botonComprar.setEnabled(!texto.isEmpty());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarEstadoBoton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarEstadoBoton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarEstadoBoton();
            }
        });

        // ComboBox de Tipos
        getContentPane().add(new JLabel("Tipo de Pokémon:"));
        comboTipos = new JComboBox<>(new String[]{"Hielo", "Agua", "Piedra","Fuego"});
        getContentPane().add(comboTipos);
        comboTipos.addActionListener(e -> {
            String tipoSeleccionado = (String) comboTipos.getSelectedItem();
            if ("Piedra".equalsIgnoreCase(tipoSeleccionado)) {
                comboArmas.setEnabled(true);
            } else {
                comboArmas.setEnabled(false);
                comboArmas.setSelectedIndex(0); // opcional: reinicia a "Sin Arma"
            }
        });

        // ComboBox de Armas (solo para tipo PIEDRA)
        getContentPane().add(new JLabel("Arma"));
        comboArmas = new JComboBox<>(new String[]{"Sin Arma", "Espada", "Hacha"});
        comboArmas.setEnabled(false);
        getContentPane().add(comboArmas);

        // Botón de comprar
        botonComprar = new JButton("Comprar Pokémon");
        getContentPane().add(botonComprar);
        botonComprar.setEnabled(false);
        // Acción del botón Comprar (todavía pendiente de implementación)
        botonComprar.addActionListener(e -> {
            String entrenadorNombre = (String) comboEntrenadores.getSelectedItem();
            String nombrePokemon = nombrePokemonField.getText().trim();
            String tipo = (String) comboTipos.getSelectedItem();
            String arma = comboArmas.isEnabled() ? (String) comboArmas.getSelectedItem() : null;

            if (nombrePokemon.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del Pokémon no puede estar vacío.");
                return;
            }

            try {
                // Comprar Pokémon básico
                controlador.comprarPokemon(entrenadorNombre, tipo, nombrePokemon);

                // Si es tipo PIEDRA, se compra un arma adicional
                if ("PIEDRA".equals(tipo) && arma != null) {
                    controlador.comprarArma(entrenadorNombre, arma);
                }

                JOptionPane.showMessageDialog(this, "Compra realizada con éxito.");
                nombrePokemonField.setText("");
            } catch (CompraImposibleException ex) {
                JOptionPane.showMessageDialog(this, "No se pudo realizar la compra: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Botón de cerrar
        botonCerrar = new JButton("Cerrar Tienda");
        getContentPane().add(botonCerrar);
        botonCerrar.addActionListener(e -> dispose());
    }

    // Método para actualizar la lista de entrenadores (en base al modelo)
    private void actualizarComboEntrenadores() {
        comboEntrenadores.removeAllItems();
        for (String e : controlador.getNombresEntrenadores()) {
            comboEntrenadores.addItem(e);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        actualizarComboEntrenadores();
    }
}