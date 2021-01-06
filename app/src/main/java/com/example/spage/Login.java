package com.example.spage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button b4;
    LoginDatabase db;
    EditText ed,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db=new LoginDatabase(this);
        ed=(EditText)findViewById(R.id.e2);
        pass=(EditText)findViewById(R.id.pass);
        b4 =(Button)findViewById(R.id.b2);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String log= ed.getText().toString();
                String pas=pass.getText().toString();

                if (log.equals("")| pas.equals(""))
                    Toast.makeText(getApplicationContext(), "Field is empty", Toast.LENGTH_SHORT).show();
                else{

                     Boolean mail_pass= db.emails(log,pas);
                     if(mail_pass==true){
                         Toast.makeText(getApplicationContext(),"Welcome to Cred's sell",Toast.LENGTH_SHORT).show();
                       Homepage();
                         }
                     else {
                         Toast.makeText(getApplicationContext(),"Wrong email or password ",Toast.LENGTH_SHORT).show();
                     }
                    }

                }


        });


}

public void Homepage(){
        Intent intent= new Intent(this,Homepage.class);
        startActivity(intent);
}
}

