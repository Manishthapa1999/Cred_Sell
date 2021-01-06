package com.example.spage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Sell_page extends AppCompatActivity {
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> data1 = new ArrayList<String>();
    private ArrayList<String> data2 = new ArrayList<String>();
    private ArrayList<String> data3 = new ArrayList<String>();

    private TableLayout table;


    AutoCompleteTextView product;
    EditText qty;
    TextView Total, amt, damount;
    Button add_item, Sell, clear;


    ItemDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_page);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.search_bar);
        Total = findViewById(R.id.total);
        amt = findViewById(R.id.num);
        damount = findViewById(R.id.damt);
        product = findViewById(R.id.search_bar);

        add_item = (Button) findViewById(R.id.add_item);
        Sell = (Button) findViewById(R.id.Sell);

        qty = (EditText) findViewById(R.id.qty);
        mydb = new ItemDatabase(Sell_page.this);
        autoCompleteTextView.setThreshold(1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mydb.search());
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // When textview lost focus check the textview data valid or not
                if (!hasFocus) {
                    if (!mydb.search().contains(autoCompleteTextView.getText().toString())) {
                        autoCompleteTextView.setText(""); // clear your TextView
                    }
                }
            }
        });

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = autoCompleteTextView.getText().toString().trim();
                String st = mydb.price(item);
                String qt=qty.getText().toString().trim();
                damount.setText(st);
                if (item.equals("")|qt.equals("")){
                    Toast.makeText(Sell_page.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }else
                addproduct();

            }
        });

        Sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sell();
            }
        });

    }


    public void sell() {
        Intent intent = new Intent(this, afterbill.class);
        String tot=amt.getText().toString().trim();
        Bundle bundle= new Bundle();
        bundle.putString("total",tot);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void addproduct() {
        float tot;
        String productname = product.getText().toString();
        Float amount = Float.parseFloat(damount.getText().toString());
        int quentity = Integer.parseInt(qty.getText().toString());
        tot = amount * quentity;


        data.add(productname);
        data1.add(String.valueOf(amount));
        data2.add(String.valueOf(quentity));
        data3.add(String.valueOf(tot));

        TableLayout table = (TableLayout) findViewById(R.id.table);
        TableRow row = new TableRow(this);
        TextView t1 = new TextView(this);
        TextView t2 = new TextView(this);
        TextView t3 = new TextView(this);
        TextView t4 = new TextView(this);


        String ta;
        float sum = 0;
        for (int i = 0; i < data.size(); i++) {
            String pn = data.get(i);
            String pa = data1.get(i);
            String pq = data2.get(i);
            ta = data3.get(i);

            t1.setText(pn);
            t2.setText(pa);
            t3.setText(pq);
            t4.setText(ta);


            sum = sum + Float.parseFloat(data3.get(i));

        }
        row.addView(t1);
        row.addView(t2);
        row.addView(t3);
        row.addView(t4);
        table.addView(row);


        amt.setText(String.valueOf(sum));
        product.setText("");
        qty.setText("");


    }

}


