package com.yarris.cordova.consol.watermarking;

import android.content.Context;
import android.util.Log;

/**
 * Created by gota on 14/03/18.
 */

public class Watermarking {
    private static final String LOG_TAG = "Watermarking";

    public Watermarking(Context context, String url, WatermarkLines details) {
        Log.d(LOG_TAG, "url: " + url + ", " + details);
        boolean success = watermark(context, url, details);
        Log.d(LOG_TAG, "success: " + success);
    }

    boolean watermark(Context context, String photoUrl, WatermarkLines details) {
        Log.d(LOG_TAG, "watermark photoUrl: " + photoUrl);
        Photo photo = new ReadPhoto(photoUrl).read();
        Log.d(LOG_TAG, "mark photo: " + photo);
        Photo created  = new Watermarker(context, photo, details).mark();
        Log.d(LOG_TAG, "mark created: " + created);
        return new SavePhoto(photoUrl).save(created);
    }

}