package com.arunfiddler.sdc.myapplication;
        import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        txtName = (TextView) findViewById(R.id.name);
        txtDirector = (TextView) findViewById(R.id.director);
        txtDuration = (TextView) findViewById(R.id.duration);
        txtPrice = (TextView) findViewById(R.id.price);
        txtRating = (TextView) findViewById(R.id.rating);
        imgPoster = (ImageView) findViewById(R.id.poster);
        txtGenre = (TextView) findViewById(R.id.genre);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        imgPoster = (ImageView) findViewById(R.id.poster);
        txtError = (TextView) findViewById(R.id.txt_error);
        ticketView = (TicketView) findViewById(R.id.layout_ticket);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnsms = (Button) findViewById(R.id.btn_sms);
        Intent i3 = getIntent();
        /*String id = i3.getStringExtra("code");
        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();*/
        Staff stf =(Staff) i3.getSerializableExtra("code");
        setresult(stf);
    }

    private void showNoTicket() {
        txtError.setVisibility(View.VISIBLE);
        ticketView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    private void setresult(final Staff s) {
        if (s != null) {
            try {
                txtName.setText(s.getName());
                txtDirector.setText("Department Of " + s.getDept());
                txtDuration.setText(s.getQfn());
                txtGenre.setText(s.getDsn());
                txtRating.setText(s.getPhno());
                txtPrice.setText(s.getMail());
               /*Glide.with(getApplicationContext()).load("https://www.google.co.in/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png").thumbnail(Glide.with(getApplicationContext()).load(R.drawable.nt)).diskCacheStrategy(DiskCacheStrategy.RESULT).crossFade().into(imgPoster);*/
                imgPoster.setImageResource(R.drawable.nt);
                btnsms.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (s.getPhno() == null) {
                            Toast.makeText(Results.this.getApplicationContext(), "There is No Phone Number You Cant Call OR SMS", Toast.LENGTH_LONG).show();
                        } else {
                            new Dialog().showdialog(s.getName(), s.getPhno(), Results.this);
                        }
                    }
                });
                btnBuy.setText(getString(R.string.btn_buy_now));
                btnBuy.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        if (s.getPhno() == null) {
                            Toast.makeText(Results.this.getApplicationContext(), "There is No Phone Number You Cant Call OR SMS", Toast.LENGTH_LONG).show();
                        } else {
                            new Dialog().showdialog(s.getName(), s.getPhno(), Results.this);
                        }
                    }
                });
                btnBuy.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                ticketView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                return;
            } catch (Exception e) {
                showNoTicket();
                Toast.makeText(getApplicationContext(), "Error occurred. Check your LogCat for full report", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        showNoTicket();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Aboutmenu) {
            Intent i2 = new Intent(this, developer.class);
            startActivity(i2);
            return true;

        }
        if (id == R.id.campsmenu) {
            Intent intent = new Intent(this, Camps.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.staffmenu) {
            Intent i3 = new Intent(this, dept.class);
            startActivity(i3);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}