package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class CustomListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Cambia el fondo de las celdas
        setBackground(Color.BLACK);

        // Cambia el borde redondeado
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Cambia el color del texto
        setForeground(Color.GREEN);

        return this;
    }
}