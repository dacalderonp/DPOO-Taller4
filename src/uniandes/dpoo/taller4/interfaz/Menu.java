package uniandes.dpoo.taller4.interfaz;


import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Menu extends JPanel {
	
    private JButton nuevoButton;
    private JButton reiniciarButton;
    private JButton top10Button;
    private JButton cambiarJugadorButton;
    
    private Top top;
    //private LightsGrid tablero;
    private Bottom bottom;
	private LightsGrid board;
	private int hardest=1;
	private int matriz=7;
	
    
    
    public Menu(Bottom bottom, Top top, LightsGrid board) {
    	
    	this.bottom=bottom;
    	this.top=top;
    	this.board=board;
    	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    	
        // Crea e inicializa los botones
        nuevoButton = new JButton("Nuevo");
        reiniciarButton = new JButton("Reiniciar");
        top10Button = new JButton("Top-10");
        cambiarJugadorButton = new JButton("Cambiar Jugador");
        

        // Agrega los botones al panel
        add(nuevoButton);
        add(reiniciarButton);
        add(top10Button);
        add(cambiarJugadorButton);

        // Asigna ActionListener a cada botón
        nuevoButton.addActionListener(new ActionListener() {
            int numTamano;
            int numDificultad;
        	@Override
            public void actionPerformed(ActionEvent e) {
                String[] dificultadOptions = { "Fácil", "Medio", "Difícil" };
                String dificultad = (String) JOptionPane.showInputDialog(
                        null, "Elige la dificultad:",
                        "Configuración",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        dificultadOptions,
                        dificultadOptions[0]
                );

                if (dificultad != null) {
                	
                    String[] tamanoOptions = { "3x3", "5x5", "7x7" };
                    String tamano = (String) JOptionPane.showInputDialog(
                            null, "Elige el tamaño del tablero:",
                            "Configuración",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tamanoOptions,
                            tamanoOptions[0]
                    );

                    if (tamano != null) {
                        int numeroDificultad;
                        if (dificultad.equals("Facil")) {
                            numeroDificultad = 1;
                        } else if (dificultad.equals("Medio")) {
                            numeroDificultad = 3;
                        } else {
                            numeroDificultad = 5;
                        }
                        int numeroTamano = Integer.parseInt(tamano.split("x")[0]);
                        numTamano=numeroTamano;
                        numDificultad=numeroDificultad;
                        
                        hardest=numeroDificultad;
                        matriz=numeroTamano;
                    }

                    top.actualizarJuego(tamano, dificultad);
                    board.setNuevoJuego(numTamano,numDificultad,bottom);
                	int jugadasReiniciar=0;
                	bottom.actualizarJugadas(jugadasReiniciar);
                	board.reiniciarTablero();
                
                    
                }
                
            }
        });

        reiniciarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				board.setNuevoJuego(matriz,hardest,bottom);
				board.reiniciarTablero();
			}
        	
        });

        top10Button.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String[]> top10Data = readCSV("./data/top10.csv", ",");
                //System.out.println(top10Data);
                
                if (!top10Data.isEmpty()) {
                    // Crear y mostrar la ventana emergente con JList personalizado
                    // Utiliza los datos de top10 para construir tu JList personalizado.
                	
                	DefaultListModel<String> listModel = new DefaultListModel<>();
                	int order = 1;
                	for (String[] row : top10Data) {
                	    String[] rowData = row[0].split(";");
                	    StringBuilder formattedRow = new StringBuilder();
                	    
                	    formattedRow.append(order).append(". ");
                	    
                	    for (String element : rowData) {
                	        formattedRow.append(element).append(" - ");
                	    }
                	    
                	    formattedRow.setLength(formattedRow.length() - 3);  // Elimina el último " - "
                	    listModel.addElement(formattedRow.toString());
                	    order++;
                	}

                	JList<String> top10List = new JList<>(listModel);
                	top10List.setCellRenderer(new CustomListCellRenderer());
                    JOptionPane.showMessageDialog(null, new JScrollPane(top10List), "Top 10", JOptionPane.INFORMATION_MESSAGE);
                    
                    
                    
                    
                } else {
                	System.out.println("ERROR");
                    // No hay datos en top10 para mostrar.
                    // Puedes mostrar un mensaje de error o hacer algo apropiado.
                }
                
            }
        });

        cambiarJugadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String jugador = JOptionPane.showInputDialog("Cambie el nombre del jugador:");
            	bottom.actualizarJugador(jugador);
            	int jugadasReiniciar=0;
            	bottom.actualizarJugadas(jugadasReiniciar);
                //String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del jugador:");
                // Aquí debes implementar la lógica para cambiar el nombre del jugador
                // utilizando la clase RegistroTop10.
            }
        });
    }
    
    /*
     * *CARGA DATOS TOP 10
    */
    
    public static List<String[]> readCSV(String filePath, String delimiter) {
        List<String[]> data = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] row = line.split(delimiter);
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
    
    /*
     * *
     * *Getters
     * *
     */
    

}
