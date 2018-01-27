package com.arunfiddler.sdc.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by Arun Fiddler on 12/31/2017.
 */

public class CustomGridViewActivity extends BaseAdapter {
    private final int[] gridViewImageId;
    private final String[] gridViewString;
    private Context mContext;



    public CustomGridViewActivity(Context context, String[] gridViewString, int[] gridViewImageId) {
        this.mContext = context;
        this.gridViewImageId = gridViewImageId;
        this.gridViewString = gridViewString;
    }

    public int getCount() {
        return this.gridViewString.length;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View convertView, ViewGroup parent) {
        View gridViewAndroid;
        LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        if (convertView == null) {
            gridViewAndroid = new View(this.mContext);
            gridViewAndroid = inflater.inflate(R.layout.gridview_layout, null);
        } else {
            gridViewAndroid = convertView;
        }
        ImageView imageViewAndroid = (ImageView) gridViewAndroid.findViewById(R.id.android_gridview_image);
        ((TextView) gridViewAndroid.findViewById(R.id.android_gridview_text)).setText(this.gridViewString[i]);
        imageViewAndroid.setImageResource(this.gridViewImageId[i]);
        return gridViewAndroid;
    }
}