package com.codeanu.vaccinator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DBNAME = "register.db";

    public DBHelper(Context context){
        super(context, "register.db",  null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table person(name TEXT, dob TEXT, aadhar_number TEXT primary key, city TEXT, state TEXT, pincode TEXT, phone_number TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1 ) {

        MyDB.execSQL("drop Table if exists person");

    }

    public Boolean insertData(String name, String dob, String aadhar_number, String city, String state, String pincode, String phone_number){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("aadhar_number",aadhar_number);
        contentValues.put("dob",dob);
        contentValues.put("city",city);
        contentValues.put("state",state);
        contentValues.put("pincode",pincode);
        contentValues.put("phone_number",phone_number);
        long result = MyDB.insert("person",null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkaadhar(String aadhar_number){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from person where aadhar_number = ?",new String[]{aadhar_number});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else{
            return false;
        }
    }


}
