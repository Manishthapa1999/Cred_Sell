package com.example.spage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginDatabase extends SQLiteOpenHelper {
    public LoginDatabase(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(name text,email text primary key, phone_no varchar(10), password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }


    public boolean insert(String name, String email, String phone_no, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("phone_no",phone_no);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long ins = db.insert("user", null, contentValues);
        if (ins == -1) return false;
        else return true;
    }
    public boolean is_empty(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from user",new String[]{});
        if ((cursor.getCount())>0)return false;
        else return true;

    }
    public boolean check_mail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from user where email=?", new String[]{email});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    public boolean emails(String email, String password)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where email=? and password=?",new String[]{email,password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
