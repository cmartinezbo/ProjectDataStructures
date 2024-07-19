package com.model;
import java.time.LocalTime;
import com.datastructures.sequential.SinglyLinkedList;

public class group {

    private int number;
    private String teacher;
    private String faculty;

    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String[] days;

    public group(int number, String teacher, String faculty, int startHour, int finishHour, String location, String day) {
        this.number = number;
        this.teacher = teacher;
        this.faculty = faculty;
        this.startTime = LocalTime.of(startHour, 0);
        this.endTime = LocalTime.of(finishHour, 0);
        this.location = location;
        this.days = day.split("-");
    }

    public String getInformation(){
        String days=String.join("-",this.days);
        String information="Grupo número: "+this.number+", Profesor: "+this.teacher+", Facultad: "+this.faculty+", Hora de inicio: "+startTime.toString()+", Hora de finalización: "+endTime.toString()+", Ubicación: "+this.location+", Días: "+days;
        return information;
    }




}
