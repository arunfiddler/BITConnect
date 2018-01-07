package com.example.arunfiddler.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Listall extends AppCompatActivity {
    DBHELPER1 db = new DBHELPER1(this);
    ListView listView = null;
    MyCustomAdapter myCustomAdapter = null;
    ArrayList<Staff> staff = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listall);
        String dept = getIntent().getExtras().getString("dept");
        staff = db.getUsers(dept);
        /*Toast.makeText(getApplicationContext(),"inlistall"+staff.size(),Toast.LENGTH_SHORT).show();*/
        myCustomAdapter = new MyCustomAdapter(getApplicationContext(), R.layout.staff_details, staff);
        listView = (ListView) findViewById(R.id.simpleListView);
        listView.setAdapter(this.myCustomAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Staff stf = (Staff) Listall.this.staff.get(i);
                Intent intent;

                intent = new Intent(Listall.this.getApplicationContext(), Results.class);
                intent.putExtra("code", stf.getId());
                Listall.this.startActivity(intent);

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