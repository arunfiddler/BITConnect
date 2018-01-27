package com.arunfiddler.sdc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Camps extends AppCompatActivity {public static final String USER_AGENT = "Mozilla/5.0 (Linux;Android 4.1.1; Galaxy Nexus Build/JRO03C) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.766 Mobile Safari/535.19";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camps);
        Toast.makeText(this,"Please wait while Camps is loading.....",Toast.LENGTH_LONG).show();
        WebView view = (WebView) this.findViewById(R.id.camps);
        view.setWebViewClient(new WebViewClient());
        view.loadUrl("https://camps.bitsathy.ac.in");
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setLoadsImagesAutomatically(true);
        view.getSettings().setUserAgentString(USER_AGENT);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
