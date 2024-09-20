package com.model;
import com.datastructures.sequential.SinglyLinkedList;
import com.model.subject;
import com.model.group;
import java.time.LocalDate;

public class user {
    private String name;

    private String lastName;
    private String idType;
    private int id;
    private String placeOfIssue;
    private String personalEmail;
    private String institutionalEmail;
    private int contactPhoneNumber;
    private int studentCode;
    private LocalDate birthdate;

    private String address;
    private int stratus;
    private SinglyLinkedList<subject> subjects;
    private String schedule; //Change for schedule object;

    public user(String name, String lastName, String personalEmail){
        this.name=name;
        this.lastName=lastName;
        this.personalEmail=personalEmail;
    }

    public int getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public int getId() {
        return id;
    }

    public int getStratus() {
        return stratus;
    }

    public int getStudentCode() {
        return studentCode;
    }

    public LocalDate getBirthdate() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setContactPhoneNumber(int contactPhoneNumber) {
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

    public void setStratus(int stratus) {
        this.stratus = stratus;
    }

    public void setStudentCode(int studentCode) {
        this.studentCode = studentCode;
    }

}
