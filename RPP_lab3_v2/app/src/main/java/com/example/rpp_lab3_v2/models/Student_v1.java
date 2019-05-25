package com.example.rpp_lab3_v2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Student_v1  {
    private int id;
    private String FIO;
    private String date;



    public Student_v1(int id, String FIO, String date){
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
