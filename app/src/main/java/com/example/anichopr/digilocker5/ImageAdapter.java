package com.example.anichopr.digilocker5;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anichopr on 7/24/2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private DocumentInfo[] mDocumentsInfo;

    public ImageAdapter(Context c, DocumentInfo[] documentsInfo) {
        mContext = c;
        mDocumentsInfo = documentsInfo;
    }

    public int getCount() {
        return mDocumentsInfo.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView;

        LayoutInflater li = ((Activity) mContext).getLayoutInflater();
        myView = li.inflate(R.layout.document_layout, null);
        ImageView img = (ImageView) myView.findViewById(R.id.img2);
        TextView txt = (TextView) myView.findViewById(R.id.txt2);

        Bitmap bitmap = mDocumentsInfo[position].bitmap;
        Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap, 450, 650, true);

        img.setImageBitmap(bitmap2);
        txt.setText(mDocumentsInfo[position].title);

        return myView;
    }
}