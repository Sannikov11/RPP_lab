package com.example.rpp_lab3.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rpp_lab3.models.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DB extends SQLiteOpenHelper {

    public DB(Context context){
        super(context, "StudentsDB", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Students" +
                "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "FIO TEXT NOT NULL," +
                "Time REAL NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void changeStudent(){
        SQLiteDatabase db;
        try {
            db = getWritableDatabase();
        }
        catch (SQLiteException ex){
            db = getReadableDatabase();
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Cursor cursor = db.rawQuery("SELECT MAX(id) FROM Students", null);
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        String template = "Иванов Иван Иванович";
        String time = dateFormat.format(date.getTime());
        db.execSQL("UPDATE Students SET FIO = '" + template + "', Time = '" + time + "' WHERE id = " + id);
    }

    public void addStudent(){

        String arraySurname[] = {
                "Санников", "Пачкин", "Медянник", "Маилян", "Петров", "Николаев"
        };
        String arrayName[] = {
                "Денис", "Виктор", "Эдуард", "Александр", "Руслан", "Леонид", "Никита"
        };
        String arrayPatronymic [] = {
                "Игоревич", "Сергеевич", "Александрович", "Петрович", "Алексеевич", "Леонидович", "Дмитриевич"
        };


        SQLiteDatabase db;
        try {
            db = getWritableDatabase();
        }
        catch (SQLiteException ex){
            db = getReadableDatabase();
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Cursor cursor = db.rawQuery("SELECT MAX(id) FROM Students", null);
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        ContentValues cv = new ContentValues();
        int randSurname = (int)(Math.random() * arraySurname.length), randName = (int)(Math.random() * arrayName.length), randPatronymic = (int)(Math.random() * arrayPatronymic.length);
        String FIO = arraySurname[randSurname] + " " + arrayName[randName] + " " + arrayPatronymic[randPatronymic];
        cv.put("id", id + 1);
        cv.put("FIO", FIO);
        cv.put("Time", dateFormat.format(date.getTime()));
        db.insert("Students", null, cv);
        cursor.close();
        db.close();
    }

    public void addFiveStudents(){
        SQLiteDatabase db;
        try {
            db = getWritableDatabase();
        }
        catch (SQLiteException ex){
            db = getReadableDatabase();
        }
        db.execSQL("DELETE FROM Students");

        for (int i = 0; i < 5; i++)
            addStudent();
        db.close();
    }

    public ArrayList<Student> readAll(){
        ArrayList<Student> list = new ArrayList<>();
        SQLiteDatabase db;
        try {
            db = getWritableDatabase();
        }
        catch (SQLiteException ex){
            db = getReadableDatabase();
        }
        Cursor c = db.rawQuery("SELECT * FROM Students ORDER BY Time", null);
        if (c.moveToFirst()){
            do{
                list.add(new Student(c.getInt(0), c.getString(1), c.getString(2)));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }
}
