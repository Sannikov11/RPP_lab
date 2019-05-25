package com.example.rpp_lab3.models;


import java.util.Date;

public class Student  {
    private int id;
    private String FIO;
    private String date;


    public Student(int id, String FIO, String  date){
        this.id = id;
        this.FIO = FIO;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "\nID = " + id + " | FIO = " + FIO;
    }
}
