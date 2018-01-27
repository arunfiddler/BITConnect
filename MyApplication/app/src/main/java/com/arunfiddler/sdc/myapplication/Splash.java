package com.arunfiddler.sdc.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Typeface tp1 = Typeface.createFromAsset(getAssets() , "Roboto.ttf");
        TextView mytv1 = (TextView)findViewById(R.id.tv2);
        ImageView im1= (ImageView)findViewById(R.id.load);
        mytv1.setTypeface(tp1);
        Thread splash = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splash.start();

    }
}
