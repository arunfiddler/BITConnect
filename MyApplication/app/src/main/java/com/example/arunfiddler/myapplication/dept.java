package com.example.arunfiddler.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class dept extends AppCompatActivity {
    GridView androidGridView;
    int[] gridViewImageId = new int[]{R.drawable.aero, R.drawable.agri, R.drawable.auto, R.drawable.bt, R.drawable.chem, R.drawable.civil, R.drawable.cse, R.drawable.ece, R.drawable.eee, R.drawable.ei, R.drawable.fashion, R.drawable.food, R.drawable.it, R.drawable.lang, R.drawable.maths, R.drawable.mba, R.drawable.mech, R.drawable.mecht, R.drawable.phy, R.drawable.tex,R.drawable.pet};
    String[] gridViewString = new String[]{"Aeronautical", "Agri", "Auto", "Biotech", "Chemistry", "Civil", "CSE", "ECE", "EEE", "E&I", "Fashion", "Food", "IT", "Language", "Mathematics", "MBA", "Mechanical", "Mechatronics", "Physical Science", "Textile" , "Physical Education"};
    String[] gridViewdeptString = new String[]{"AERONAUTICAL ENGINEERING", "AGRICULTURE ENGINEERING", "AUTOMOBILE ENGINEERING", "BIOTECHNOLOGY", "CHEMISTRY", "CIVIL", "COMPUTER SCIENCE AND ENGINEERING", "ELECTRONICS AND COMMUNICATION ENGINEERING", "ELECTRICAL AND ELECTRONICS ENGINEERING", "ELECTRONICS AND INSTRUMENTATION ENGINEERING", "FASHION TECHNOLOGY", "FOOD TECHNOLOGY", "INFORMATION TECHNOLOGY", "LANGUAGE", "MATHEMATICS", "MBA", "MECHANICAL ENGINEERING", "MECHATRONICS", "PHYSICAL SCIENCE", "TEXTILE TECHNOLOGY", "PHYSICAL EDUCATION"};


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);
        if (!getApplicationContext().getDatabasePath(DBHELPER1.DATABASE_NAME).exists()) {
            new DBHELPER1(this).getReadableDatabase();
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copy Database Success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy Database failure", Toast.LENGTH_SHORT).show();
            }
        }
        CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(this, this.gridViewString, this.gridViewImageId);
        this.androidGridView = (GridView) findViewById(R.id.gridview);
        this.androidGridView.setAdapter(adapterViewAndroid);
        this.androidGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(dept.this, Listall.class);
                intent.putExtra("dept", dept.this.gridViewdeptString[i]);
                dept.this.startActivity(intent);
            }
        });
    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DBHELPER1.DATABASE_NAME);
            OutputStream outputStream = new FileOutputStream("/data/data/com.example.arunfiddler/databases/staff.sql");
            byte[] buff = new byte[1024];
            while (true) {
                int length = inputStream.read(buff);
                if (length > 0) {
                    outputStream.write(buff, 0, length);
                } else {
                    outputStream.flush();
                    outputStream.close();
                    Log.v("MainActivity ", "DB Copied");
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
