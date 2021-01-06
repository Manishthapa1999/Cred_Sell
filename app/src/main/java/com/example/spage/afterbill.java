package com.example.spage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class afterbill extends AppCompatActivity {
    Button cash,credit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterbill);
        credit=(Button)findViewById(R.id.credit);
        cash=(Button)findViewById(R.id.cash);
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(afterbill.this, "Item inventory updated successfully",Toast.LENGTH_SHORT).show();
                mainpage();
            }
        });
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             credit();
            }
        });
    }
public void mainpage(){
        Intent intent = new Intent (this,Homepage.class);
        startActivity(intent);
}
    public void credit(){
        Bundle bundle=getIntent().getExtras();
        String total=bundle.getString("total");
        Bundle bundle2= new Bundle();
        bundle.putString("total",total);
        Intent intent= new Intent(this,sellcreditor.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
