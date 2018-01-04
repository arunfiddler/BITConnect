package com.example.arunfiddler.myapplication;

/**
 * Created by Arun Fiddler on 1/2/2018.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MyCustomAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Staff> staff;

    private class ViewHolder {
        TextView staffName;

        private ViewHolder() {
        }
    }

    public MyCustomAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.staff = objects;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.staff_details, null);
            holder = new ViewHolder();
            holder.staffName = (TextView) convertView.findViewById(R.id.staff_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Staff stf = (Staff) this.staff.get(position);
        holder.staffName.setText(BuildConfig.FLAVOR + stf.getName() + " " );
        return convertView;
    }
}

