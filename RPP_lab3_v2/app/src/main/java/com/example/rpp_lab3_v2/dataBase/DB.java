package com.example.rpp_lab3_v2.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rpp_lab3_v2.models.Student_v2;
import com.example.rpp_lab3_v2.models.Student_v1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DB extends SQLiteOpenHelper {

    String arraySurname[] = {"Санников", "Пачкин", "Медянник", "Маилян", "Петров", "Николаев"};
    String arrayName[] = {"Денис", "Виктор", "Эдуард", "Александр", "Руслан", "Леонид", "Никита"};
    String arrayPatronymic [] = {"Игоревич", "Сергеевич", "Александрович", "Петрович", "Алексеевич", "Леонидович", "Дмитриевич"};
    public static int version = 1;
    public DB(Context context){
        super(context, "StudentsDB", null, version);
    }
    public DB(Context context, int newVersion){
        super(context, "StudentsDB", null, newVersion);
        this.version = newVersion;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (version == 1) {
            db.execSQL("CREATE TABLE IF NOT EXISTS Students" +
                    "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "FIO TEXT," +
                    "Time REAL  NOT NULL)"
            );
        }
        else
            db.execSQL("CREATE TABLE IF NOT EXISTS Students" +
                    "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "Surname TEXT," +
                    "Name TEXT," +
                    "Patronymic TEXT," +
                    "Time REAL NOT NULL)"
            );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion > oldVersion){
            oldVersion++;
            db.execSQL("CREATE TABLE IF NOT EXISTS Students2" +
                    "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "Surname TEXT," +
                    "Name TEXT," +
                    "Patronymic TEXT," +
                    "Time REAL NOT NULL)"
            );
            /*
             UPDATE Students2 SET surname = ..., ....
             */
            Cursor c = db.rawQuery("SELECT id, " +
                    "SUBSTR(FIO, 1, INSTR(FIO, ' ') - 1), " +
                    "SUBSTR(SUBSTR(FIO, INSTR(FIO, ' ')+1), 1, INSTR(SUBSTR(FIO, INSTR(FIO, ' ')+1), ' ') - 1), " +
                    "SUBSTR(SUBSTR(FIO, INSTR(FIO, ' ')+1), INSTR(SUBSTR(FIO, INSTR(FIO, ' ')+1), ' ')+1), " +
                    "Time FROM Students", null);
            ContentValues cv = new ContentValues();
            if (c.moveToFirst()){
                do {
                    cv.clear();
                    cv.put("id", c.getInt(0));
                    cv.put("Surname", c.getString(1));
                    cv.put("Name", c.getString(2));
                    cv.put("Patronymic", c.getString(3));
                    cv.put("Time", c.getString(4));
                    db.insert("Students2", null, cv);
                }while (c.moveToNext());
            }
            db.execSQL("DROP TABLE IF EXISTS Students");
            db.execSQL("ALTER TABLE Students2 RENAME TO Students");
            c.close();
        }
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 2 && newVersion == 1){
            oldVersion++;
            db.execSQL("CREATE TABLE IF NOT EXISTS Students2" +
                    "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "FIO TEXT," +
                    "Time REAL NOT NULL)"
            );
            Cursor c = db.rawQuery("SELECT id, Surname, Name, Patronymic, Time FROM Students", null);
            ContentValues cv = new ContentValues();
            if (c.moveToFirst()){
                do {
                    cv.clear();
                    cv.put("id", c.getInt(0));
                    String FIO = c.getString(1) + " " + c.getString(2)  + " " +  c.getString(3);
                    cv.put("FIO", FIO);
                    cv.put("Time", c.getString(4));
                    db.insert("Students2", null, cv);
                }while (c.moveToNext());
            }
            db.execSQL("DROP TABLE IF EXISTS Students");
            db.execSQL("ALTER TABLE Students2 RENAME TO Students");
            c.close();
        }
    }


    public ArrayList<Student_v2> readAll(){
        ArrayList<Student_v2> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT id, Surname, Name, Patronymic, Time FROM Students ORDER BY Time", null);
        if (c.moveToFirst()){
            do{
                list.add(new Student_v2(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }
    public void changeStudent_v2(){
        SQLiteDatabase db;
        try {
            db = getWritableDatabase();
        }
        catch (SQLiteException ex){
            db = getReadableDatabase();
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Cursor c = db.rawQuery("SELECT MAX(id) FROM Students", null);
        c.moveToFirst();
        int id = c.getInt(0);
        String surname = "Иванов", name = "Иван", patronymic = "Иванович";
        String time = dateFormat.format(date.getTime());
        db.execSQL("UPDATE Students SET Surname = '" + surname + "', Name = '" + name + "', Patronymic = '" + patronymic + "',Time = '" + time + "'  " + " WHERE id = " + id);
        db.close();
    }

    public void changeStudent_v1(){
        SQLiteDatabase db = getWritableDatabase();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Cursor c = db.rawQuery("SELECT MAX(id) FROM Students", null);
        c.moveToFirst();
        int id = c.getInt(0);
        String t = "Иванов Иван Иванович";
        String time = dateFormat.format(date.getTime());
        db.execSQL("UPDATE Students SET FIO = '" + t + "',  Time = '" + time + "'  WHERE id = " + id);
    }

    public void addStudent_v1(){
        SQLiteDatabase db;
        try {
            db = getWritableDatabase();
        }
        catch (SQLiteException ex){
            db = getReadableDatabase();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Date date = new Date();
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

    public void addStudent_v2(){

        SQLiteDatabase db = getWritableDatabase();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Date date = new Date();
        Cursor c = db.rawQuery("SELECT MAX(id) FROM Students", null);
        c.moveToFirst();
        int id = c.getInt(0);
        ContentValues cv = new ContentValues();
        int randSurname = (int)(Math.random() * arraySurname.length), randName = (int)(Math.random() * arrayName.length), randPatronymic = (int)(Math.random() * arrayPatronymic.length);
        cv.put("id", id + 1);
        cv.put("Surname", arraySurname[randSurname]);
        cv.put("Name", arrayName[randName]);
        cv.put("Patronymic", arrayPatronymic[randPatronymic]);
        cv.put("Time", dateFormat.format(date.getTime()));
        db.insert("Students", null, cv);
        c.close();
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
            addStudent_v1();
        db.close();
    }

    public ArrayList<Student_v1> readAll_v1(){
        ArrayList<Student_v1> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Students ORDER BY Time", null);
        if (c.moveToFirst()){
            do{
                list.add(new Student_v1(c.getInt(0), c.getString(1), c.getString(2)));

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }
    public int getNewVersion() {
        return version;
    }

    public void setNewVersion(int newVersion) {
        this.version = newVersion;
    }

}
