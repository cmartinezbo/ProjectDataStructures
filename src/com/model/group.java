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
            if(aDays[i].trim().equals("lunes")){
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
                dofarray[i]= DayOfWeek.SATURDAY;
            }
        }
        return dofarray;
    }

    public String getInformation(){
        int j = 0;
        String d="";
        for(int i=0; i<this.days.length-1;i++){
            //System.out.println(this.number+" "+this.teacher);
            d+=this.days[i].toString()+"-";
            j=i;
        }
        if(this.days.length==1){
            d+=this.days[0].toString();
        }
        else{
            d+=this.days[j+1].toString();

        }
        String information="Grupo número: "+this.number+", Profesor: "+this.teacher+", Facultad: "+this.faculty+", Hora de inicio: "+startTime.toString()+", Hora de finalización: "+endTime.toString()+", Ubicación: "+this.location+", Días: "+d;
        return information;
    }

    public static void main(String[] args){
        String information="4,RICARDO ISAZA RUGET,FACULTAD DE INGENIERÍA,9,11,(Magistral) SALON DE VIDEO CONFERENCIAS 454-302. 454-302. 454 - Luis Carlos Sarmiento Angulo. SALON.,miércoles";
        int number=4;
        String profesor="RICARDO ISAZA RUGET";
        String facultad="FACULTAD DE INGENIERÍA";
        int horaI=9;
        int horaF=11;
        String salon="(Magistral) SALON DE VIDEO CONFERENCIAS 454-302. 454-302. 454 - Luis Carlos Sarmiento Angulo. SALON.";
        String dias="miércoles";
        group grupo=new group(number,profesor,facultad,horaI,horaF,salon,dias);
        System.out.println(grupo.getInformation());

    }

}