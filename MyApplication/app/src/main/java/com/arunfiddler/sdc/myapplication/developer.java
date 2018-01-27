package com.arunfiddler.sdc.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class developer extends AppCompatActivity {
 Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        Button b=(Button)findViewById(R.id.dev);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.arunfiddler.tk";
                Intent iii = new Intent(Intent.ACTION_VIEW);
                iii.setData(Uri.parse(url));
                startActivity(iii);
            }
        });
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
