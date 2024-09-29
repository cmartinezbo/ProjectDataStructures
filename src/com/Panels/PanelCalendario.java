/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author Acer
 */
package com.Panels;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.model.*;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalTime;
import java.time.Duration;
import java.time.DayOfWeek;
import java.util.Random;
import java.awt.Color;
import com.model.schedule;
import com.model.subject;





public class PanelCalendario extends javax.swing.JPanel {
        schedule scheduleInstance = FramePrincipal.scheduleI;
        private static final Random random = new Random();
        private String selectedCellValue;

    /**
     * Creates new form Panel1
     */
    public PanelCalendario() {
        initComponents();
        //configurarTablaCalendarioCompleto();
        configurarTablaCalendarioDetalles();

        int i = 0; // Inicializa el índice
        CustomCellRenderer customRenderer = (CustomCellRenderer) calendarioMes.getDefaultRenderer(Object.class);


        for (Map.Entry<subject, group> entry : scheduleInstance.getMapSubjectGroup().entrySet()) {
            subject subj = entry.getKey();      // Obtener la asignatura (clave)
            group grp = entry.getValue();       // Obtener el grupo (valor)

            String subjName = subj.getName();
            int groupNumber = grp.getNumber();

            int dayInt;

            // Generar valores RGB aleatorios entre 128 y 255 para obtener colores claros
            int red = random.nextInt(128) + (128/2);   // Rango de 128 a 255
            int green = random.nextInt(128) + (128/2); // Rango de 128 a 255
            int blue = random.nextInt(128) + (128/2);  // Rango de 128 a 255

            Color color = new Color(red, green, blue);


            for (DayOfWeek dia : grp.getDays()) {
                dayInt = obtenerIndiceDia(dia); // Usa dia directamente sin volver a declarar el tipo
                LocalTime groupStart = grp.getStartTime();
                LocalTime groupEnd = grp.getEndTime();
                Duration duration = Duration.between(groupStart, groupEnd);
                long horas = duration.toHours();
                int horasInt = (int) horas; // Casting explícito
                int horaInicio = groupStart.getHour();

                for (int j = 0; j < horasInt; j++){
                    customRenderer.setCellColor(horaInicio + j, dayInt + 1, color);
                    if (j == 0){
                        calendarioMes.setValueAt(subjName, horaInicio + j, dayInt + 1);
                    }
                }
            }
            i++; // Incrementa el índice
        }

    }


    private void configurarTablaCalendarioDetalles() {
        // Crear el modelo de tabla personalizado
        CustomTableModel2 modeloCalendarioDetalles = new CustomTableModel2(
            new Object[] {"", "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"}, // Nombres de las columnas
            24 // Número inicial de filas
        );
        calendarioMes.setModel(modeloCalendarioDetalles);

        // Configurar la fuente y altura de las filas
        calendarioMes.setFont(new Font("SansSerif", Font.PLAIN, 14));
        calendarioMes.setDefaultRenderer(Object.class, new CustomCellRenderer());

        // Configurar los valores en la tabla
        calendarioMes.getColumnModel().getColumn(0).setPreferredWidth(15);
        calendarioMes.getColumnModel().getColumn(1).setPreferredWidth(15);


        for (int row = 0; row < 24; row++) {
                calendarioMes.setValueAt(row + ":00", row, 0);
        }

        calendarioMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Obtener la fila y columna clickeadas
                int row = calendarioMes.rowAtPoint(evt.getPoint());
                int column = calendarioMes.columnAtPoint(evt.getPoint());

                // Asegurarse de que la celda no esté vacía
                Object cellValue = calendarioMes.getValueAt(row, column);
                if (cellValue != null) {
                    subject currentSubject = scheduleInstance.findSubjectByName(cellValue.toString());
                    group currentGroup = scheduleInstance.getGroupBySubject(currentSubject);
                    JOptionPane.showMessageDialog(calendarioMes,
                            currentSubject.getAttributesToString() + "\n" + currentGroup.getInformation(), // Texto a mostrar
                            "Información de la asignatura", // Título
                            JOptionPane.INFORMATION_MESSAGE); // Tipo de mensaje
                }
            }
        });

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
               //método para que la tabla inicie en la fila 7:00
               int middleRow = 7;
               calendarioMes.scrollRectToVisible(calendarioMes.getCellRect(middleRow, 0, true));
           }
        });

    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        calendarioMes = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1000, 675));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 675));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(978, 675));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 50)); // NOI18N
        jLabel1.setText("CALENDARIO");
        jLabel1.setPreferredSize(new java.awt.Dimension(52, 16));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(910, 500));

        calendarioMes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null, null},
                        //... (otras filas)
                },
                new String[]{
                        "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
                }
        ));
        calendarioMes.setPreferredSize(new java.awt.Dimension(900, 1440));
        calendarioMes.setRowHeight(60);
        jScrollPane2.setViewportView(calendarioMes);


        // Configura el layout del panel
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(242, 242, 242)) // Ajusta si es necesario
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(46, 46, 46)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        // .addGroup(...) // Se eliminó la parte del botón
                                )
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(358, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }


    private static int obtenerIndiceDia(DayOfWeek dia) {
        return (dia.getValue() % 7); // Asegura que SUNDAY sea 0
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable calendarioMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}

class CustomTableModel1 extends DefaultTableModel {
    public CustomTableModel1(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Hacer las filas impares no editables
        return ((row % 2) - 1) == 0; // Las filas pares (índices 0, 2, 4, ...) son editables
    }
}

class CustomTableModel2 extends DefaultTableModel {
    public CustomTableModel2(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Hacer las filas impares no editables
        return false;
    }
}

class CustomCellRenderer extends DefaultTableCellRenderer {

    // Mapa para almacenar colores personalizados por coordenadas de celda
    private final Map<Point, Color> cellColors = new HashMap<>();

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {

        // Llamamos al método de la superclase para mantener el renderizado predeterminado
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Restaurar el borde predeterminado (líneas divisorias)
        if (cell instanceof JComponent) {
            ((JComponent) cell).setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        }

        // Comprobar si hay un color personalizado para esta celda
        if (cellColors.containsKey(new Point(column, row))) {
            cell.setBackground(cellColors.get(new Point(column, row))); // Color personalizado
            cell.setForeground(Color.BLACK);  // Cambiar el color del texto a negro
        } else {
            // Restaurar los colores predeterminados si la celda no tiene un color personalizado
            cell.setBackground(Color.WHITE);   // Color de fondo predeterminado
            cell.setForeground(Color.BLACK);   // Color de texto predeterminado
        }

        return cell;
    }

    // Método para establecer un color en una celda específica
    public void setCellColor(int row, int column, Color color) {
        cellColors.put(new Point(column, row), color);
    }
}