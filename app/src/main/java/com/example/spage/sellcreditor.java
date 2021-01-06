package com.example.spage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sellcreditor extends AppCompatActivity {
    Button updateCustomer,addNew;
    CustomerDatabase db;
    EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellcreditor);

        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.adhar);
        updateCustomer=(Button)findViewById(R.id.UpdateCustomer);
        addNew=(Button)findViewById(R.id.addCustomer);
        db= new CustomerDatabase(sellcreditor.this);
        amount=(EditText)findViewById(R.id.e2);
        Bundle bundle=getIntent().getExtras();
        String total=bundle.getString("total");
        amount.setText(total);
        amount.setEnabled(false);
        autoCompleteTextView.setThreshold(2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, db.search());
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // When textview lost focus check the textview data valid or not
                if (!hasFocus) {
                    if (!db.search().contains(autoCompleteTextView.getText().toString())) {
                        autoCompleteTextView.setText(""); // clear your TextView
                    }
                }
            }
        });

        updateCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=autoCompleteTextView.getText().toString();
                float amt= Float.parseFloat(amount.getText().toString());
                db.update(a,amt);
                Toast.makeText(sellcreditor.this,"Customer details updated",Toast.LENGTH_SHORT).show();
                showmainscreen();
            }
        });
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showaddcustomer();
            }
        });
    }
    void showmainscreen(){
        Intent intent= new Intent(sellcreditor.this, Homepage.class);
        startActivity(intent);
    }
    void showaddcustomer(){
        Bundle bundle=getIntent().getExtras();
        String total=bundle.getString("total");
        Bundle bundle2= new Bundle();
        bundle.putString("total",total);
        Intent intent= new Intent(sellcreditor.this, newcustomer.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
