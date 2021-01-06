package com.example.spage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class update extends AppCompatActivity {
    ItemDatabase mydb;
    EditText e1,e2,e3,e4;
    Button b1;
    String id,name,amount,qty;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(update.this, first.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        e3=(EditText)findViewById(R.id.e3);
        e4=(EditText)findViewById(R.id.e4);
        b1 = (Button) findViewById(R.id.b1);
        e1.setEnabled(false);
        getandsetintentdata();    
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb=new ItemDatabase(update.this);
                int id, stock;
                float amount;
                String product;
                product = e2.getText().toString().trim();
                id = ConvertIntoNumeric(e1.getText().toString().trim());
                amount = ConvertIntofloat(e3.getText().toString().trim());
                stock = ConvertIntoNumeric(e4.getText().toString().trim());
                boolean ck=mydb.update(id, product, amount, stock);
                if (ck==true){
                    Toast.makeText(update.this,"updated successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(update.this,"failed to update",Toast.LENGTH_SHORT).show();
                }
            }
        });
    
    }
    void getandsetintentdata(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("amount") && getIntent().hasExtra("qty")){
            id=getIntent().getStringExtra("id");
            name=getIntent().getStringExtra("name");
            amount=getIntent().getStringExtra("amount");
            qty=getIntent().getStringExtra("qty");
            e1.setText(id);
            e2.setText(name);
            e3.setText(amount);
            e4.setText(qty);
        }
            else
        {
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }




    }
    public int ConvertIntoNumeric(String xVal)
    {
        try
        {
            return Integer.parseInt(xVal);
        }
        catch(Exception ex)
        {
            return 0;
        }
    }public float ConvertIntofloat(String xVal)
    {
        try
        {
            return Float.parseFloat(xVal);
        }
        catch(Exception ex)
        {
            return 0;
        }
    }
}