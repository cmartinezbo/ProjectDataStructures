package com.model;
import com.model.group;
import com.datastructures.sequential.SinglyLinkedList;


public class subject {

    public int code;
    public String name;
    public int credits;
    public String typology;
    public SinglyLinkedList<group> groups;

    public subject(int code, String name, int credits, String typology) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.typology = typology;
        this.groups=new SinglyLinkedList<>();
    }

    public int getCode(){
        return this.code;
    }

    public void setCode(int code){
        this.code=code;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getCredits(){
        return this.credits;
    }

    public void setCredits(int credits){
        this.credits=credits;
    }

    public String getTypology(){
        return this.typology;
    }

    public void setTypology(String typology){
        this.typology=typology;
    }

    public void addGroup(group group){
        groups.pushBack(group);
    }

    public SinglyLinkedList<group> getGroups(){
        return this.groups;
    }

    public void getAttributesInformation(){
        String information="Codigo: "+this.code+", Nombre: "+this.name+", Créditos: "+this.credits+", Tipología: "+this.typology;
        System.out.println(information);
    }

    public String getAttributesToString(){
        return "Codigo: "+this.code+", Nombre: "+this.name+", Créditos: "+this.credits+", Tipología: "+this.typology;
    }

    public group getGroupByIndex(int index){
        int i=0;
        for(group group: groups){
            i++;
            if(i==index){
                return group;
            }
        }
        return null;
    }

    public void getGroupsInformation() {
        for (group g : this.groups) {
            System.out.println(g.getInformation());
        }
    }














}
