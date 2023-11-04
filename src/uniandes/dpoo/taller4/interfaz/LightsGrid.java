package uniandes.dpoo.taller4.interfaz;


import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JButton;
import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LightsGrid extends JPanel{
    /**
	 * 
	 */
    private Tablero tablero;
    private JButton[][] buttons;
	private int casillaSize;
	private int tamano;
	private ImageIcon[][] imagenes;
	private int jugadas;
	private Bottom bottom;
	private int dificultad;
	

    public LightsGrid(int tamano, int dificultad, Bottom bottom) {
        this.tamano = tamano;
        this.tablero = new Tablero(tamano);
        this.casillaSize = 50; // Tamaño de cada casilla en píxeles
        setPreferredSize(new Dimension(tamano * casillaSize, tamano * casillaSize));
        
        setBackground(Color.YELLOW);
        
        imagenes = new ImageIcon[tamano][tamano];
        for (int fila = 0; fila < tamano; fila++) {
            for (int columna = 0; columna < tamano; columna++) {
                // Ruta relativa a la ubicación del proyecto
                String rutaImagen = "./data/luz.png"; // Reemplaza con la ruta correcta
                imagenes[fila][columna] = new ImageIcon(rutaImagen);
            }
        }
        
        tablero.desordenar(dificultad);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = e.getY() / casillaSize;
                int columna = e.getX() / casillaSize;
                if (fila >= 0 && fila < tamano && columna >= 0 && columna < tamano) {
                    tablero.jugar(fila, columna);
                    jugadas++;
                    repaint();
                    
                    //Bottom.actualizarNombreJugador(getNombreJugador());
                    bottom.actualizarJugadas(getJugadas());
                    
                }
                
                //System.out.println(jugadas);
            }
        });
        

        
        
    }
    
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        boolean[][] estadoTablero = tablero.darTablero();

        for (int fila = 0; fila < tamano; fila++) {
            for (int columna = 0; columna < tamano; columna++) {
                if (estadoTablero[fila][columna]) {
                    // Pintar de negro si la casilla está apagada
                    g.setColor(Color.BLACK);
                } else {
                    // Pintar de amarillo si la casilla está encendida
                    g.setColor(Color.YELLOW);
                }
                int x = columna * casillaSize;
                int y = fila * casillaSize;
                g.fillRect(x, y, casillaSize, casillaSize);

                // Dibujar la imagen de fondo
                imagenes[fila][columna].paintIcon(this, g, x, y);
            }
        }
    }
    
    ////
    // Getters //
    ///
    
    public int getJugadas() {
        return jugadas;
    }
    
    public void setNuevoJuego(int tamano, int dificultad, Bottom bottom) {
    	repaint();
    	this.tamano=tamano;
    	//this.tablero = new Tablero(tamano);
    	this.dificultad=dificultad;
    	this.bottom=bottom;
    	
    	setBackground(Color.GRAY);
    }
    
    public void reiniciarTablero() {
    	tablero.reiniciar();
    }




    
    
    

}