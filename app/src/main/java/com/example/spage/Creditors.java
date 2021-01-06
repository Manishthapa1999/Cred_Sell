package com.example.spage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Creditors extends AppCompatActivity {

    CustomerDatabase db;
    ArrayList<String> cus_name, cus_amount, cus_ph;
    CustomerAdapter customerAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditors);


        recyclerView=findViewById(R.id.recycle_view);
        db = new CustomerDatabase(Creditors.this);
        cus_name = new ArrayList<>();
        cus_amount = new ArrayList<>();
        cus_ph = new ArrayList<>();

        displaydata();

        customerAdapter = new CustomerAdapter(Creditors.this, cus_name, cus_amount, cus_ph);
        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Creditors.this));


    }

    void displaydata() {
        Cursor cursor = db.readalldata();
        if (cursor.getCount() == 0) {
            Toast.makeText(Creditors.this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                cus_name.add(cursor.getString(1));
                cus_ph.add(cursor.getString(2));
                cus_amount.add(cursor.getString(3));
            }
        }
    }
}
