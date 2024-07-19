package com.main.test;
import com.model.group;
import com.model.subject;
import com.datastructures.sequential.SinglyLinkedList;

public class testSubject {
    public static void main(String [] args){
        group test=new group(1,"Alejandro","FACULTAD DE CIENCIAS",9,11,"Edificio nuevo para las artes244","lunes-miércoles");
        group test1=new group(6,"Daniel","FACULTAD DE INGENERÍA",7,19,"Agronomía","lunes-miércoles");
        group test2=new group(9,"Camilo","FACULTAD DE INGENIERÍA",11,13,"CYT 404 223","lunes-miércoles");
        SinglyLinkedList<group> groups=new SinglyLinkedList<>();
        groups.pushBack(test);
        groups.pushBack(test1);
        groups.pushBack(test2);
        subject subjects=new subject(1009,"Calculo diferencial",4,"Fundamentación obligatoria");
        subjects.getGroupsInformation();
    }
}
