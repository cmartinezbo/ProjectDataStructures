package com.controller;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.model.group;
import java.util.HashMap;
import java.util.regex.Pattern;

import com.model.subject;

public class SubjectSearchEngine {

    com.map.HashMap<Integer,subject> mapSubjects;
    public SubjectSearchEngine(com.map.HashMap<Integer,subject> mapSubjects){
        this.mapSubjects=mapSubjects;
    }

    public List<subject> searchSubject(String query) {
        query=normalizeString(query);
        List<subject> filter= new ArrayList<>();

        for (subject subj : mapSubjects.values()) {
            // Filtro por nombre de materia
            String name=normalizeString(subj.getName());
            if (!name.toLowerCase().contains(query.toLowerCase())) {
                continue;
            }

            filter.add(subj);
        }

        return filter;
    }

    public List<group> searchGroup(String teacherName, subject subject){
        subject.getAttributesInformation();
        List<group> filter=new ArrayList<>();
        teacherName=normalizeString(teacherName);
        for(group group: subject.getGroups()) {
            String name = normalizeString(group.getTeacher());
            if (!name.toLowerCase().contains(teacherName.toLowerCase())) {
                //System.out.println(name+" "+teacherName);
                continue;
            }
            filter.add(group);
            //System.out.println(filter);
        }
        return filter;
    }



    private String normalizeString(String input) {
        if (input == null) {
            return null;
        }

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").toLowerCase();
    }

    public void getSubjects(List<subject> subjects){
        if(!subjects.isEmpty()){
            for(subject subject: subjects){
                subject.getAttributesInformation();
                subject.getGroupsInformation();
            }
        }
        else{
            System.out.println("No hay concidencias con esta busqueda");
        }
    }

    public void getGroups(List<group> groups){
        if(!groups.isEmpty()){
            System.out.println("no vacio");
            for(group group: groups){
                String information=group.getInformation();
                System.out.println(information);
            }
        }
        else{
            System.out.println("No hay concidencias con esta busqueda");
        }
    }

    public static void main(String [] args){
        String csvFile="C:\\Users\\cruzg\\Desktop\\java\\proyectos_intelliJ\\ProjectDataStructures\\docs\\libro1.csv";
        csvLoader.load(csvFile);
        com.map.HashMap<Integer, subject> map=csvLoader.getSubjects();
        SubjectSearchEngine searche=new SubjectSearchEngine(map);
        List <subject> subjects=searche.searchSubject("calculo");
        //searche.getSubjects(subjects);
        List <group> groups=searche.searchGroup("Gabriel Ignacio Padilla Leon", subjects.get(1));
        searche.getGroups(groups);

    }




}
