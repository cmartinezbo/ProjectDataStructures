package com.Panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.*;

import com.controller.SubjectSearchEngine;
import com.model.subject;
import com.model.group;

public class PanelAsignaturas extends javax.swing.JPanel {

    private JTextField searchField;
    private JButton searchButton;
    private JList<String> subjectList;
    private JList<String> groupList;
    private DefaultListModel<String> subjectListModel;
    private DefaultListModel<String> groupListModel;
    private SubjectSearchEngine searchEngine;

    public PanelAsignaturas(SubjectSearchEngine searchEngine) {
        this.searchEngine = searchEngine;
        initComponents();
    }

    private void initComponents() {
        // Crear los componentes
        searchField = new JTextField(20);
        searchButton = new JButton("Buscar");
        subjectListModel = new DefaultListModel<>();
        subjectList = new JList<>(subjectListModel);
        groupListModel = new DefaultListModel<>();
        groupList = new JList<>(groupListModel);

        // Configurar el panel con un GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes

        // Agregar etiqueta de búsqueda
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Buscar Asignatura:"), gbc);

        // Agregar campo de texto de búsqueda (reducido en altura)
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(searchField, gbc);

        // Agregar botón de búsqueda
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        add(searchButton, gbc);

        // Agregar etiqueta de resultados de asignaturas
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Resultados de Asignaturas:"), gbc);

        // Agregar lista de asignaturas (con tamaño más reducido)
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.2;  // Tamaño reducido para la lista de asignaturas
        add(new JScrollPane(subjectList), gbc);

        // Agregar etiqueta de grupos
        gbc.gridy = 4;
        gbc.weighty = 0;
        add(new JLabel("Grupos Disponibles:"), gbc);

        // Agregar lista de grupos (con mayor tamaño)
        gbc.gridy = 5;
        gbc.weighty = 0.8;  // Mayor tamaño para la lista de grupos
        add(new JScrollPane(groupList), gbc);

        // Acción del botón buscar
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarBusqueda();
            }
        });

        // Acción cuando se selecciona una asignatura de la lista
        subjectList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                mostrarGrupos();
            }
        });
    }

    public void onEnterPanelAsignaturas() {
        // Limpiar cualquier dato previo
        groupListModel.clear();
        subjectListModel.clear();

        // Opcional: también puedes limpiar el campo de búsqueda
        searchField.setText("");
    }

    private void realizarBusqueda() {
        subjectListModel.clear();
        groupListModel.clear();
        String query = searchField.getText();
        List<subject> results = searchEngine.searchSubject(query);

        if (!results.isEmpty()) {
            for (subject subj : results) {
                subjectListModel.addElement(subj.getName());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron asignaturas", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void mostrarGrupos() {
        groupListModel.clear();
        int selectedIndex = subjectList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedSubjectName = subjectListModel.get(selectedIndex);
            subject selectedSubject = searchEngine.searchSubject(selectedSubjectName).get(0);

            List<group> groups = searchEngine.searchGroup("", selectedSubject);

            if (!groups.isEmpty()) {
                // Usar un Set para eliminar duplicados
                Set<String> uniqueGroups = new LinkedHashSet<>();
                for (group grp : groups) {
                    uniqueGroups.add(grp.getInformation());  // Filtrar duplicados
                }
                // Agregar grupos únicos al modelo de la lista
                for (String groupInfo : uniqueGroups) {
                    groupListModel.addElement(groupInfo);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron grupos", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
