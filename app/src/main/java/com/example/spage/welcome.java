package com.example.spage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class welcome extends AppCompatActivity {
    EditText name,email,phone,password;
    Button b1;
    LoginDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        db= new LoginDatabase(this);
        name = (EditText)findViewById(R.id.e1);
        email = (EditText)findViewById(R.id.e2);
        phone =(EditText) findViewById(R.id.e3);
        password = (EditText)findViewById(R.id.e4);
        b1=(Button)findViewById(R.id.b2);
    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String s1=name.getText().toString();
            String s2=email.getText().toString();
            String s3=phone.getText().toString();
            String s4=password.getText().toString();
            if(s1.equals("")|| s2.equals("") || s3.equals("") || s4.equals(""))
            {
                Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
             }
            else
            {
             Boolean check_mail= db.check_mail(s2);
             if(check_mail==true){
                 Boolean insert=db.insert(s1,s2,s3,s4);
                 if(insert==true){
                     Toast.makeText(getApplicationContext(),"Registered successfully",Toast.LENGTH_SHORT).show();
                     Open_Homepage();
                 }
             }
             else{
                 Toast.makeText(getApplicationContext(),"Email Already Exists",Toast.LENGTH_SHORT).show();
             }

            }
         }

    });
    }
    public void Open_Homepage(){
        Intent intent= new Intent(this,Homepage.class);
        startActivity(intent);
    }
}
