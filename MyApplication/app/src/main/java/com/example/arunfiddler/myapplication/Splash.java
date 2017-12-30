package com.example.arunfiddler.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Typeface tp1 = Typeface.createFromAsset(getAssets() , "Chunkfive.otf");
        TextView mytv = (TextView)findViewById(R.id.tv1);
        TextView mytv1 = (TextView)findViewById(R.id.tv2);
        ImageView im1= (ImageView)findViewById(R.id.load);
        mytv.setTypeface(tp1);
        Animation animFadein,animRotate;
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        mytv1.startAnimation(animFadein);
        im1.startAnimation(animRotate);
        Thread splash = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(4000);
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
