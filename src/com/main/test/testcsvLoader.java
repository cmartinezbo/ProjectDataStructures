package com.main.test;
import com.controller.csvLoader;

public class testcsvLoader {

    public static void main(String [] args){
        String csvFile="C:\\Users\\cruzg\\Desktop\\java\\proyectos_intelliJ\\ProjectDataStructures\\docs\\materias.txt";
        csvLoader.load(csvFile);
        csvLoader.getInformation();
    }
}
