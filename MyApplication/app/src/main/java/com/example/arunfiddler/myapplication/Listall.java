package com.example.arunfiddler.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import java.util.ArrayList;

public class Listall extends AppCompatActivity {
    DBHELPER1 db = null;
    ListView listView = null;
    MyCustomAdapter myCustomAdapter = null;
    ArrayList<Staff> staff = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listall);
        String dept = getIntent().getExtras().getString("dept");
        this.db = new DBHELPER1(this);
        this.staff = this.db.getUsers(dept);
        this.myCustomAdapter = new MyCustomAdapter(this, R.layout.staff_details, this.staff);
        this.listView = (ListView) findViewById(R.id.simpleListView);
        this.listView.setAdapter(this.myCustomAdapter);
        this.listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Staff stf = (Staff) Listall.this.staff.get(i);
                Intent intent;
                if (stf.getDept().equals("SCIENCE AND HUMANTIES")) {
                    if (!stf.getName().equals("NON-Teaching Staff") && !stf.getName().equals("CHEMISTRY") && !stf.getName().equals("PHYSICS") && !stf.getName().equals("MATHEMATICS") && !stf.getName().equals("ENGLISH")) {
                        intent = new Intent(Listall.this.getApplicationContext(), Results.class);
                        intent.putExtra("code", stf.getId());
                        Listall.this.startActivity(intent);
                    }
                } else if (!stf.getName().equals("NON-Teaching Staff")) {
                    intent = new Intent(Listall.this.getApplicationContext(), Results.class);
                    intent.putExtra("code", stf.getId());
                    Listall.this.startActivity(intent);
                }
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