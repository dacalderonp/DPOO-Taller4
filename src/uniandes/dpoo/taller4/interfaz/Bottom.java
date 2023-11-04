package uniandes.dpoo.taller4.interfaz;

import javax.swing.*;
import java.awt.*;


public class Bottom extends JPanel {
	
    private static JLabel jugadorLabel;
    private static JLabel jugadasLabel;
    private LightsGrid lightsGrid;
    private int jugadas;

    public Bottom(String jugador, int jugadas, LightsGrid lightsGrid) {
    	
    	this.lightsGrid = lightsGrid;
        //setLayout(new FlowLayout(FlowLayout.LEFT));

        jugadorLabel = new JLabel("Jugador: ");
        jugadasLabel = new JLabel("Jugadas: ");

        add(jugadorLabel);
        add(jugadasLabel);
        
    }
    
    public static void actualizarJugador(String jugador) {
    	jugadorLabel.setText("Jugadas: " + jugador);
    }
    
    
    public static void actualizarJugadas(int jugadas) {
        jugadasLabel.setText("Jugadas: " + jugadas);
    }
    


    


}
