package com.example.anichopr.digilocker5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by anichopr on 7/24/2015.
 */
public class DocumentInfo {
    String title;
    Bitmap bitmap;
    String uri;

    public DocumentInfo(String title, Bitmap bitmap) {
        this.title = title;
        this.bitmap = bitmap;
    }
}
