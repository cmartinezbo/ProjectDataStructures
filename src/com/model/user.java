package com.model;
import com.datastructures.sequential.SinglyLinkedList;
import com.model.subject;
import com.model.group;
import java.time.LocalDate;

public class user {
    private String name;

    private String lastName;
    private String idType;
    private String id;
    private String placeOfIssue;
    private String personalEmail;
    private String institutionalEmail;
    private String contactPhoneNumber;
    private String studentCode;
    private String birthdate;
    private String nationality;
    private String address;
    private String stratus;
    private SinglyLinkedList<subject> subjects;
    private String schedule; //Change for schedule object;


    public user(String name, String lastName, String institutionalEmail,
            String studentCode, String birthdate, String nationality){
        this.name=name;
        this.lastName=lastName;
        this.institutionalEmail=institutionalEmail;
        this.studentCode=studentCode;
        this.idType="";
        this.id="";
        this.placeOfIssue="";
        this.personalEmail="";
        this.contactPhoneNumber="";
        this.birthdate=birthdate;
        this.address="";
        this.stratus="";
        this.nationality=nationality;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getStratus() {
        return stratus;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getAddress() {
        return address;
    }

    public String getIdType() {
        return idType;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public String getPlaceOfIssue() {
        return placeOfIssue;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setInstitutionalEmail(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public void setPlaceOfIssue(String placeOfIssue) {
        this.placeOfIssue = placeOfIssue;
    }

    public void setStratus(String stratus) {
        this.stratus = stratus;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }


    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
