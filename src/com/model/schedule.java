package com.model;
import com.datastructures.sequential.SinglyLinkedList;
import com.model.subject;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.TreeMap;

public class schedule {

    private SinglyLinkedList<subject> subjects=new SinglyLinkedList<>();

    private Map<DayOfWeek,subject> schedule=new TreeMap<>(); //Also could be a treemap with keys-value=DayOfWeek-groups
    private String scheduleName;

    public schedule(String name){
        this.scheduleName=name;
    }







    

}
