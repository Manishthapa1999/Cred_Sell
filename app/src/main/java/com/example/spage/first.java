package com.example.spage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class first extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton button ;
    ItemDatabase mydb;
    ArrayList<String> pid,pname,pamount,pstock;
    CustomAdopter customAdopter;

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(first.this,Homepage.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        recyclerView=findViewById(R.id.recycleview);
        button =findViewById(R.id.b1);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showinventory();
            }
        });
        mydb = new ItemDatabase(first.this);
        pid = new ArrayList<>();
        pname = new ArrayList<>();
        pamount = new ArrayList<>();
        pstock = new ArrayList<>();

        StoreDatainArray();
        customAdopter = new CustomAdopter(first.this,this, pid,pname,pamount,pstock);
        recyclerView.setAdapter(customAdopter);
        recyclerView.setLayoutManager(new LinearLayoutManager(first.this));

    }



    void StoreDatainArray()
    {
        Cursor cursor = mydb.readalldata();
        if (cursor.getCount()==0)
        {
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }else
        {
            while (cursor.moveToNext())
            {
                pid.add(cursor.getString(0));
                pname.add(cursor.getString(1));
                pamount.add(cursor.getString(2));
                pstock.add(cursor.getString(3));

            }
        }
    }
void showinventory(){
    Intent intent = new Intent(first.this , inventory.class);
    startActivity(intent);
}

}
