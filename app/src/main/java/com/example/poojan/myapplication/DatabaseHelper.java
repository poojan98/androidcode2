package com.example.poojan.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="ID";
    public static final String COL_2="FirstName";
    public static final String COL_3="LastName";
    public static final String COL_4="Marks";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("create table %s(ID INTEGER PRIMARY KEY AUTO INCREMENT,FirstName TEXT,LastName TEXT ,Marks INTEGER);", TABLE_NAME));

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS%s", TABLE_NAME));
        onCreate(sqLiteDatabase);
    }
    public boolean insertdata(String FirstName, String LastName, String Marks)
    {SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,FirstName);
        contentValues.put(COL_2,LastName);
        contentValues.put(COL_3,Marks);
        long result=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result==-1){return false;}
        else{return true;}}

        public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

    public Integer deleteData(String ID){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"ID=?",new String[]{ID});


    }
}

