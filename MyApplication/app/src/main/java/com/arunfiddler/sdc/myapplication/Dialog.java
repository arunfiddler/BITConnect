package com.arunfiddler.sdc.myapplication;

/**
 * Created by Arun Fiddler on 1/2/2018.
 */

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

public class Dialog {
    private int REQUEST_CODE = 1;

    public void showdialog(String name, final String phoneno, final Activity activity) {
        Builder alertDialogBuilder = new Builder(activity);
        alertDialogBuilder.setTitle(name);
        alertDialogBuilder.setMessage(phoneno);
        alertDialogBuilder.setNeutralButton("Call", new OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent i = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneno));
                if (ContextCompat.checkSelfPermission(activity, "android.permission.CALL_PHONE") == 0) {
                    activity.startActivity(i);
                    return;
                }
                ActivityCompat.requestPermissions(activity, new String[]{"android.permission.CALL_PHONE"}, Dialog.this.REQUEST_CODE);
                Toast.makeText(activity, "Click Call Now Again To Call", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNegativeButton("Sms", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Dialog.this.sendSMS(activity, phoneno);
            }
        });
        alertDialogBuilder.setPositiveButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialogBuilder.create().show();
    }

    protected void sendSMS(Context activity, String phoneno) {
        try {
            Intent smsIntent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + phoneno));
            smsIntent.putExtra("sms_body", "SMS application launched from BIT Connect");
            activity.startActivity(smsIntent);
        } catch (Exception e) {
            Toast.makeText(activity, "SMS faild, please try again later!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
