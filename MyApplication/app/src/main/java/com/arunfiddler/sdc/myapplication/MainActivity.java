package com.arunfiddler.sdc.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

 private static ImageButton imageButton2,imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView opt,textView2,textView3;
        opt=(TextView)findViewById(R.id.opt);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        Typeface tp2 = Typeface.createFromAsset(getAssets(),"Roboto.ttf");
        opt.setTypeface(tp2);
        textView2.setTypeface(tp2);
        textView3.setTypeface(tp2);
        onClickButtonListener();

    }
public void onClickButtonListener()
{
    imageButton = (ImageButton)findViewById(R.id.imageButton);
    imageButton2 = (ImageButton)findViewById(R.id.imageButton2);
    imageButton2.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i2 = new Intent("com.arunfiddler.sdc.myapplication.dept");
                    startActivity(i2);
                }
            }
    );
    imageButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i1 = new Intent("com.arunfiddler.sdc.myapplication.Camps");
                    startActivity(i1);

                }
            }

    );
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.Aboutmenu)
        {   Intent i2 = new Intent(this, developer.class);
            startActivity(i2);
            return true;

        }
        if (id==R.id.campsmenu)
        {   Intent intent = new Intent(this, Camps.class);
            startActivity(intent);
            return true;
        }
        if (id==R.id.staffmenu)
        {   Intent i3 = new Intent(this, dept.class);
            startActivity(i3);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
