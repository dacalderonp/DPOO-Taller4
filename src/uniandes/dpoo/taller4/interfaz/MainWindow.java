package uniandes.dpoo.taller4.interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
//import javax.swing.Box;
import javax.swing.*;

//import java.awt.Color;
//import java.awt.GridBagLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import uniandes.dpoo.taller4.modelo.Tablero;


public class MainWindow extends JFrame{

    
    private Top topPanel;
    private LightsGrid boardPanel;
    private Menu menuPanel;
    private Bottom bottomPanel;

    public MainWindow() {
    	
        // Configura la ventana principal
        setTitle("LightsOut");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Tamaño personalizable
        //setLayout(new BorderLayout());
        
        //Tablero tablero = new Tablero(tamano);
        int tamano = 7;
        int dificultad = 1;
        String jugador = "Invitado";
        int jugadas=0;
        
        
        
        // Crea instancias de los paneles
        topPanel = new Top(menuPanel);
        topPanel.actualizarJuego("7x7", "Facil");
        boardPanel = new LightsGrid(tamano, dificultad, bottomPanel);
        menuPanel = new Menu(bottomPanel, topPanel, boardPanel);
        bottomPanel = new Bottom(jugador, jugadas, boardPanel);
        
        //Metodos retorno dificultad y tamano

        // Agrega los paneles a la ventana principal en la ubicación deseada
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        
        
        
        add(topPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(menuPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        
        
        // Configura y muestra la ventana
        pack();
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);
    }
    
	
	
    public static void main(String[] args) {
    	//int tamano=7;
    	//int dificultad=1;
    	//String jugador="Invitado";
    	//int jugadas=0;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	MainWindow game = new MainWindow();
                game.setVisible(true);
            }
        });
    }
}
