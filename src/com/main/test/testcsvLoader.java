package com.main.test;
import com.controller.csvLoader;

public class testcsvLoader {

    public static void main(String [] args){
        String csvFile="C:\\Users\\cruzg\\Desktop\\java\\proyectos_intelliJ\\ProjectDataStructures\\docs\\libro1.csv";
        csvLoader.load(csvFile);
        csvLoader.getInformation();
    }
}
