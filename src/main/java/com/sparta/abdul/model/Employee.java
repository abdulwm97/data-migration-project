package com.sparta.abdul.model;

import java.util.Date;

//filter out duplicate data
//store it in a collection. one collection has unique data and second collection has duplicate data.
public class Employee {
    private int empId;
    private String namePrefix;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private char gender;
    private String eMail;
    private Date dateOfBirth;
    private Date dateOfJoining;
    private int salary;

    public Employee(int empId, String namePrefix, String firstName, char middleInitial, String lastName, char gender, String eMail, Date dateOfBirth, Date dateOfJoining, int salary){
        this.empId = empId;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.eMail = eMail;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public char getGender() {
        return gender;
    }

    public String geteMail() {
        return eMail;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public int getSalary() {
        return salary;
    }



}
