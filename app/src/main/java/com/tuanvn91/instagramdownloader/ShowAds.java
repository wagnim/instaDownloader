package com.tuanvn91.instagramdownloader;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;


public class ShowAds extends AppCompatActivity {
    private static ShowAds instance;
    private int countResume = 0;

    public static ShowAds getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Wellcome");
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.about);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                setTaskDescription(new ActivityManager.TaskDescription("", bitmap,
                        ContextCompat.getColor(getApplicationContext(), R.color.tuan)));

            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorTextPrimary)));
        } catch (Exception e) {
        }

        if (instance == null)
            instance = this;
    }

    @Override
    public void onResume() {
        super.onResume();
        // put your code here...
        countResume++;
        if (countResume >= 2) {
            try {
                if (Build.VERSION.SDK_INT < 21) {
                    finishAffinity();
                } else {
                    finishAndRemoveTask();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
