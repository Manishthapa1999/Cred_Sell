package com.example.spage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class newcustomer extends AppCompatActivity {
    EditText adhar ,name, phone, amount ;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcustomer);
        adhar=(EditText)findViewById(R.id.adhar);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        amount=(EditText)findViewById(R.id.amount);
        add=(Button)findViewById(R.id.b1);
        Bundle bundle=getIntent().getExtras();
        String total=bundle.getString("total");
        amount.setText(total);
        amount.setEnabled(false);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerDatabase db=new CustomerDatabase(newcustomer.this);
                int adr;
                float amt;
                String cus_name,ph;
                cus_name= name.getText().toString().trim();
                adr=ConvertIntoNumeric(adhar.getText().toString().trim());
                amt=ConvertIntofloat(amount.getText().toString().trim());
                ph=phone.getText().toString().trim();
                db.addcustomer(adr,cus_name,amt,ph);
                home();
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

    public void home(){
        Intent intent= new Intent(newcustomer.this,Homepage.class);
        startActivity(intent);
    }
}
