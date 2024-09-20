package com.controller;
import com.map.HashMap;
import com.model.group;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import com.model.subject;
import java.io.BufferedReader;
import java.util.List;

import javax.security.auth.Subject;
import javax.swing.*;
public class csvLoader {

    String csvFile;
    static com.map.HashMap<Integer,subject> sh=new com.map.HashMap<>(0.6);
    //static Map<Integer, subject> subjectHash=new HashMap<>(); //Change it for our own hash data structure
    public csvLoader(String csvFile){
        this.csvFile=csvFile;
    }
    public static void load(String csvFile) {
        String line;
        try (BufferedReader bf= new BufferedReader(new FileReader(csvFile))) {
            while ((line = bf.readLine()) != null) {
                String[] nextline=line.split(",");
                int code =Integer.parseInt(nextline[0]);
                String name = nextline[1];
                int credits = Integer.parseInt(nextline[2]);
                String typology = nextline[3];
                int numberGroup = Integer.parseInt(nextline[4]);
                String teacher = nextline[5];
                String faculty = nextline[6];
                int startTime = Integer.parseInt(nextline[7]);
                int endTime = Integer.parseInt(nextline[8]);
                String location = nextline[9];
                String days = nextline[10];

                group group = new group(numberGroup, teacher, faculty, startTime, endTime, location, days);

                if (sh.containsKey(code)) {
                    subject subject = sh.get(code);
                    subject.addGroup(group);
                } else {
                    subject subject = new subject(code, name, credits, typology);
                    subject.addGroup(group);
                    sh.add(code,subject);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer,subject> getSubjects(){
        return sh;
    }

    public static void getInformation(){
        for(subject subject: sh.values()){
            subject.getAttributesInformation();
            for(group group: subject.groups){
                System.out.println(group.getInformation());
                System.out.println();
            }
        }
    }

}
