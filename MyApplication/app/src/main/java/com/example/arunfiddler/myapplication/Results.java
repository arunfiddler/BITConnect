package com.example.arunfiddler.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends AppCompatActivity {
    private Button btnBuy;
    private Button btnsms;
    private ImageView imgPoster;
    private ProgressBar progressBar;
    private TicketView ticketView;
    private TextView txtDirector;
    private TextView txtDuration;
    private TextView txtError;
    private TextView txtGenre;
    private TextView txtName;
    private TextView txtPrice;
    private TextView txtRating;

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        this.txtName = (TextView) findViewById(R.id.name);
        this.txtDirector = (TextView) findViewById(R.id.director);
        this.txtDuration = (TextView) findViewById(R.id.duration);
        this.txtPrice = (TextView) findViewById(R.id.price);
        this.txtRating = (TextView) findViewById(R.id.rating);
        this.imgPoster = (ImageView) findViewById(R.id.poster);
        this.txtGenre = (TextView) findViewById(R.id.genre);
        this.btnBuy = (Button) findViewById(R.id.btn_buy);
        this.imgPoster = (ImageView) findViewById(R.id.poster);
        this.txtError = (TextView) findViewById(R.id.txt_error);
        this.ticketView = (TicketView) findViewById(R.id.layout_ticket);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.btnsms = (Button) findViewById(R.id.btn_sms);
        String barcode = getIntent().getStringExtra("code");
        if (TextUtils.isEmpty(barcode)) {
            Toast.makeText(getApplicationContext(), "Barcode is empty!", 1).show();
            finish();
        }
        searchBarcode(barcode);
    }

    private void searchBarcode(String barcode) {
        setresult(new DBHELPER1(this).getStaff(barcode));
    }

    private void showNoTicket() {
        this.txtError.setVisibility(View.INVISIBLE);
        this.ticketView.setVisibility(View.VISIBLE);
        this.progressBar.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("WrongConstant")
    private void setresult(final Staff s) {
        if (s != null) {
            try {
                this.txtName.setText(s.getName());
                this.txtDirector.setText("Department Of " + s.getDept());
                this.txtDuration.setText(s.getQfn());
                this.txtGenre.setText(s.getDsn());
                this.txtRating.setText(s.getPhno());
                this.imgPoster.setImageResource(R.drawable.nt);
                this.btnsms.setOnClickListener(new OnClickListener() {
                    @SuppressLint("WrongConstant")
                    public void onClick(View view) {
                        if (s.getPhno() == null) {
                            Toast.makeText(Results.this.getApplicationContext(), "There is No Phone Number You Cant Call OR SMS", 1).show();
                        } else {
                            new Dialog().showdialog(s.getName(), s.getPhno(), Results.this);
                        }
                    }
                });
                this.btnBuy.setText(getString(R.string.btn_buy_now));
                this.btnBuy.setOnClickListener(new OnClickListener() {
                    @SuppressLint("WrongConstant")
                    public void onClick(View view) {
                        if (s.getPhno() == null) {
                            Toast.makeText(Results.this.getApplicationContext(), "There is No Phone Number You Cant Call OR SMS", 1).show();
                        } else {
                            new Dialog().showdialog(s.getName(), s.getPhno(), Results.this);
                        }
                    }
                });
                this.btnBuy.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                this.ticketView.setVisibility(View.INVISIBLE);
                this.progressBar.setVisibility(View.VISIBLE);
                return;
            } catch (Exception e) {
                showNoTicket();
                Toast.makeText(getApplicationContext(), "Error occurred. Check your LogCat for full report", 0).show();
                return;
            }
        }
        showNoTicket();
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
