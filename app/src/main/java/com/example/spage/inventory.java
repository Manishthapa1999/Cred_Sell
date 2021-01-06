package com.example.spage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class inventory extends AppCompatActivity {
    EditText id_input , name_input, amount_input,stock_input;
    Button add_button ;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(inventory.this, first.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        id_input= (EditText)findViewById(R.id.e1);
        name_input= (EditText)findViewById(R.id.e2);
        amount_input= (EditText)findViewById(R.id.e3);
        stock_input= (EditText)findViewById(R.id.e4);
        add_button= (Button) findViewById(R.id.b1);



        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ItemDatabase mydb = new ItemDatabase(inventory.this);
                int id,stock;
                float amount;
                String product;
                product= name_input.getText().toString().trim();
                id=ConvertIntoNumeric(id_input.getText().toString().trim());
                amount=ConvertIntofloat(amount_input.getText().toString().trim());
                stock=ConvertIntoNumeric(stock_input.getText().toString().trim());
                if (product.equals("")| id==0 | amount==0)
                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
                else {
                    boolean ck = mydb.addInventory(id, product, amount, stock);
                    if (ck==true) {
                        Toast.makeText(getApplicationContext(), "Added Successful!", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }clear();

            }
        });



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

    public void  clear ()
    {
        String a,b,c,d;
        a = id_input.getText().toString();
        id_input.setText("");
        b = name_input.getText().toString();
        name_input.setText("");
        c = amount_input.getText().toString();
        amount_input.setText("");
        d = stock_input.getText().toString();
        stock_input.setText("");

    }


}

