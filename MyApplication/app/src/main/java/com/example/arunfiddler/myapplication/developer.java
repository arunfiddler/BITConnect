package com.example.arunfiddler.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


public class developer extends AppCompatActivity {
 Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        b=(Button)findViewById(R.id.dev);
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
