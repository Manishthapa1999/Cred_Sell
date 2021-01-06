package com.example.spage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {
    Button Item_inv,Cus,Sell,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Item_inv=(Button)findViewById(R.id.Item_in);
        Cus=(Button)findViewById(R.id.Customer);
        Sell=(Button)findViewById(R.id.sell);
        register=(Button)findViewById(R.id.lol);
        Item_inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open_first();
            }
        });
        Sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sell_view();
            }
        });
        Cus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }
    public void Open_first(){
        Intent intent= new Intent(this,first.class);
        startActivity(intent);
    }
    public void Sell_view(){
        Intent intent= new Intent(this,Sell_page.class);
        startActivity(intent);
    }
    public void Customer(){
        Intent intent= new Intent(this,Creditors.class);
        startActivity(intent);
    }

    public void register(){
        Intent intent= new Intent(this,welcome.class);
        startActivity(intent);
    }
}
