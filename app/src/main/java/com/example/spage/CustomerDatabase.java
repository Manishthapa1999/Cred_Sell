package com.example.spage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomerDatabase extends SQLiteOpenHelper {
    private Context context;
    private static final String TABLE_NAME = "customer";
    private static final String COLUMN_ADHAR = "adhar";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_PHONE = "phone";

    public CustomerDatabase(@Nullable Context context) {
        super(context, "customer",null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ADHAR + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT ," + COLUMN_AMOUNT + " FLOAT," +
                COLUMN_PHONE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addcustomer(int adhar, String name, float amount, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ADHAR, adhar);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_PHONE, phone);
        long reasult = db.insert(TABLE_NAME, null, cv);
        if (reasult == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successful!", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readalldata() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;

    }

    public List<String> search() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from  "+TABLE_NAME, new String[]{});
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return result;
    }
    void update(String adr,float price){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("Update customer set amount=amount+? where adhar=?", new String[]{String.valueOf(price),adr});
    }
}

