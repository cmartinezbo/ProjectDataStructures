package com.model;
import java.time.LocalTime;
import com.datastructures.sequential.SinglyLinkedList;
import java.time.DayOfWeek;

public class group {

    private int number;
    private String teacher;
    private String faculty;

    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private DayOfWeek[] days;

    public group(int number, String teacher, String faculty, int startHour, int finishHour, String location, String day) {
        this.number = number;
        this.teacher = teacher;
        this.faculty = faculty;
        this.startTime = LocalTime.of(startHour, 0);
        this.endTime = LocalTime.of(finishHour, 0);
        this.location = location;
        this.days = convertDaysOfWeek(day);
    }

    private DayOfWeek[] convertDaysOfWeek(String days){
        String [] aDays=days.split("-");
        DayOfWeek [] dofarray = new DayOfWeek[aDays.length];
        for(int i=0; i<aDays.length; i++){
            if(aDays[i].equals("lunes")){
                dofarray[i]= DayOfWeek.MONDAY;
            }
            else if(aDays[i].equals("martes")){
                dofarray[i]= DayOfWeek.TUESDAY;
            }
            else if(aDays[i].equals("miércoles")){
                dofarray[i]= DayOfWeek.WEDNESDAY;
            }
            else if(aDays[i].equals("jueves")){
                dofarray[i]= DayOfWeek.THURSDAY;
            }
            else if(aDays[i].equals("viernes")){
                dofarray[i]= DayOfWeek.FRIDAY;
            }
            else if(aDays[i].equals("sábado")){
                dofarray[i]= DayOfWeek.MONDAY;
            }
        }
        return dofarray;
    }

    public String getInformation(){
        int j = 0;
        String d="";
        for(int i=0; i<this.days.length-1;i++){
            d+=this.days[i].toString()+"-";
            j=i;
        }
        d+=this.days[j+1].toString();
        String information="Grupo número: "+this.number+", Profesor: "+this.teacher+", Facultad: "+this.faculty+", Hora de inicio: "+startTime.toString()+", Hora de finalización: "+endTime.toString()+", Ubicación: "+this.location+", Días: "+d;
        return information;
    }

}
