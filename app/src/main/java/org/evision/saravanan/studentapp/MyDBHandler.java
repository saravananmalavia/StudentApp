package org.evision.saravanan.studentapp;

/**
 * Created by saravanan on 18/08/18.
 *  MyDBHandler class to handle the Database table
 *  supports CRUD operation
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database and atble
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "student_details";
    public static final String TABLE_NAME = "Student";
    public static final String COLUMN_ID = "StudentID";
    public static final String COLUMN_NAME = "StudentName";
    public static final String COLUMN_DEPT = "Depatrment";

    //initialize the database

    public MyDBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override

    // create the table

    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID +
                " INTEGER PRIMARYKEY," + COLUMN_NAME + " TEXT," + COLUMN_DEPT + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

//method to retrive all the records from the table

    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);

            result += String.valueOf(result_0) + " " + result_1 + " " + result_2 +

                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    //method to insert the record

    public long addHandler(Student student) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, student.getID());
        values.put(COLUMN_NAME, student.getStudentName());
        values.put(COLUMN_DEPT, student.getDepartment());

        SQLiteDatabase db = this.getWritableDatabase();
        long result =0;
        result      = db.insert(TABLE_NAME, null, values);

        db.close();

        return result;
    }

    //method to select a particular record

    public Student findHandler(String studentname) {

        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + studentname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setID(Integer.parseInt(cursor.getString(0)));
            student.setStudentName(cursor.getString(1));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        return student;
    }

    // method to delete a particular record

    public boolean deleteHandler(int ID) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        result = db.delete(TABLE_NAME, COLUMN_ID +  " = " +  String.valueOf(ID), null) > 0;
        db.close();

        return result;

    }

    // method to update a record

    public boolean updateHandler(int ID, String name,String Department) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        args.put(COLUMN_DEPT, Department);

        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}