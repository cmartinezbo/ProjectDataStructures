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
import com.model.schedule;

public class PanelAsignaturas extends JPanel {

    private JTextField searchField;
    private JButton searchButton;
    private JButton addToScheduleButton; // Botón para agregar al horario
    private JList<String> subjectList;
    private JList<String> groupList;
    private DefaultListModel<String> subjectListModel;
    private DefaultListModel<String> groupListModel;
    private SubjectSearchEngine searchEngine;
    private schedule scheduleInstance; // Instancia de schedule para verificar conflictos

    public PanelAsignaturas(SubjectSearchEngine searchEngine, schedule scheduleInstance) {
        this.searchEngine = searchEngine;
        this.scheduleInstance = scheduleInstance;
        initComponents();
    }

    private void initComponents() {
        // Crear los componentes
        searchField = new JTextField(20);
        searchButton = new JButton("Buscar");
        addToScheduleButton = new JButton("Agregar al Horario"); // Botón para agregar al horario
        subjectListModel = new DefaultListModel<>();
        subjectList = new JList<>(subjectListModel);
        groupListModel = new DefaultListModel<>();
        groupList = new JList<>(groupListModel);

        // Configurar el panel con un GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio entre componentes

        // Agregar campo de búsqueda
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Buscar Asignatura:"), gbc);

        // Agregar campo de texto de búsqueda
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

        // Agregar lista de asignaturas
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.2;
        add(new JScrollPane(subjectList), gbc);

        // Agregar etiqueta de grupos
        gbc.gridy = 4;
        gbc.weighty = 0;
        add(new JLabel("Grupos Disponibles:"), gbc);

        // Agregar lista de grupos
        gbc.gridy = 5;
        gbc.weighty = 0.8;
        add(new JScrollPane(groupList), gbc);

        // Agregar botón para agregar al horario
        gbc.gridy = 6;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(addToScheduleButton, gbc); // Botón para agregar asignatura al horario

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

        // Acción del botón "Agregar al Horario"
        addToScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAlHorario();
            }
        });
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
                Set<String> uniqueGroups = new LinkedHashSet<>();
                for (group grp : groups) {
                    uniqueGroups.add(grp.getInformation());  // Filtrar duplicados
                }
                for (String groupInfo : uniqueGroups) {
                    groupListModel.addElement(groupInfo);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron grupos", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void agregarAlHorario() {
        int selectedSubjectIndex = subjectList.getSelectedIndex();
        int selectedGroupIndex = groupList.getSelectedIndex();
        System.out.println(selectedSubjectIndex);
        System.out.println(selectedGroupIndex);

        // Verificar si hay selección válida
        if (selectedSubjectIndex != -1 && selectedGroupIndex != -1) {
            System.out.println(subjectListModel);
            System.out.println(groupListModel);
            String selectedSubjectName = subjectListModel.get(selectedSubjectIndex);  // Nombre de la asignatura
            //String selectedGroupInfo = groupListModel.get(selectedGroupIndex);// Información del grupo

            System.out.println(selectedSubjectName);
            //System.out.println(selectedGroupInfo);
            // Buscar la asignatura y el grupo seleccionados usando el motor de búsqueda
            subject selectedSubject = searchEngine.searchSubject(selectedSubjectName).get(0);
            group selectedGroup =selectedSubject.getGroupByIndex(selectedGroupIndex+1);

            // Intentar agregar la asignatura y el grupo al horario, verificando conflictos de horario
            if (!scheduleInstance.hasConflict(selectedGroup)) {
                // Si no hay conflicto, agregar la asignatura y el grupo al horario
                scheduleInstance.addSubject(selectedSubject, selectedGroup);
                JOptionPane.showMessageDialog(this, "Asignatura agregada al horario.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Si hay conflicto de horario, mostrar un mensaje de advertencia
                JOptionPane.showMessageDialog(this, "Conflicto de horario detectado para la asignatura: " + selectedSubject.getName(), "Conflicto de horario", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            // Si no se seleccionaron asignatura y grupo, mostrar un mensaje de error
            JOptionPane.showMessageDialog(this, "Por favor selecciona una asignatura y un grupo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}