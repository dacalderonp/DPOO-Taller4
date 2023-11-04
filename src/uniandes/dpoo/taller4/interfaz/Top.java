package uniandes.dpoo.taller4.interfaz;

import javax.swing.*;
import java.awt.*;


public class Top extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel tamanoTablero;
    private JLabel nivelDificultad;
    private Menu menu;

    public Top(Menu menu) {
    	
    	this.menu = menu;
        //setLayout(new FlowLayout(FlowLayout.LEFT));
    	tamanoTablero = new JLabel("Tamaño del Tablero: ");
    	nivelDificultad = new JLabel("Dificultad: ");
        
        add(tamanoTablero);
        add(nivelDificultad);
    }

    public void actualizarJuego(String tamano, String dificultad) {
    	
    	tamanoTablero.setText("Tamaño del Tablero: " + tamano);
    	nivelDificultad.setText("Dificultad: " + dificultad);
    }
    
}
