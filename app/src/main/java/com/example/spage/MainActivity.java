package com.example.spage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public final class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
     //variables
    LoginDatabase db;
    Animation top,bottom;
    ImageView image;
    TextView Logo,Creator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Hook this Animations
        db=new LoginDatabase(this);
        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //hooks
        image = findViewById(R.id.imageView);
        Logo = findViewById(R.id.textView);
        Creator = findViewById(R.id.textView2);

        //asign
        image.setAnimation(top);
        Logo.setAnimation(bottom);
        Creator.setAnimation(bottom);

        //splash screan
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean first_time = db.is_empty();
                if (first_time == false) {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, welcome.class);
                    startActivity(intent);
                    finish();
                }
            }

    },SPLASH_SCREEN);



    }
}
