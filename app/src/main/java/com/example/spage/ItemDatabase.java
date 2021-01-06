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

public class ItemDatabase extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Credsell";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AMOUNT = "Amount";
    private static final String COLUMN_STOCK = "stock";



    public ItemDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " BIGINT PRIMARY KEY, " +
                COLUMN_NAME + " TEXT ," + COLUMN_AMOUNT + " FLOAT, " +
                COLUMN_STOCK + " INTEGER);";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    boolean addInventory(int id, String name, float amount, int qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AMOUNT, amount);
        cv.put(COLUMN_STOCK, qty);
        long result = db.insert(TABLE_NAME , null, cv);
        if (result == -1) {
            return false;

        } else {
            return true;

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

    boolean update(int id, String item_name, float amount, int stock) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_NAME, item_name);
        contentValues.put(COLUMN_AMOUNT, amount);
        contentValues.put(COLUMN_STOCK, stock);
        long result = db.update(TABLE_NAME, contentValues, "id=?", new String[]{String.valueOf(id)});
        if (result == -1) {
            return false;

        } else {
            return true;

        }
    }

    public List<String> search() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Items ", new String[]{});
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                result.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return result;
    }



    String price(String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Items where name=?", new String[]{name});
        cursor.moveToFirst();
        String k= cursor.getString(2);
        return k;
    }
    String Stock(String name){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Items where name=?", new String[]{name});
        cursor.moveToFirst();
        String k= cursor.getString(3);
        return k;
    }


}
