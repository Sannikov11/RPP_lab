package com.example.rpp_lab3_v2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Student_v2  {
    private int id;
    private String surname, name, patronymic ;
    private String date;




    public Student_v2(int id, String surname, String name, String patronymic, String date){
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "id = " + id + " | " + surname + " " + name + " " + patronymic;
    }
}
