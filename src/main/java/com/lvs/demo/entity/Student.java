package com.lvs.demo.entity;

public class Student {
    private String fisrstName;
    private String lastName;

    public Student(){

    }

    public Student(String fisrstName, String lastName) {
        this.fisrstName = fisrstName;
        this.lastName = lastName;
    }

    public String getFisrstName() {
        return fisrstName;
    }

    public void setFisrstName(String fisrstName) {
        this.fisrstName = fisrstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
