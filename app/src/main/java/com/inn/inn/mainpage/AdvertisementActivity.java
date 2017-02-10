package com.inn.inn.mainpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;

public class AdvertisementActivity extends InnBaseActivity {

    private TextView advertisementTime;
    private Button skipAdvertisement;
    private Handler handler = new Handler();
    private Runnable timeRunnable;
    private int time = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        initView();
        initTime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(timeRunnable, 500);
    }

    private void initTime() {

        timeRunnable = new Runnable() {
            @Override
            public void run() {
                if (time == 0) {
                    Toast.makeText(AdvertisementActivity.this, "111", Toast.LENGTH_SHORT).show();
                } else {
                    advertisementTime.setText(String.valueOf(time));
                    time--;
                    handler.postDelayed(timeRunnable, 1000);
                }
            }
        };

    }

    private void initView() {
        advertisementTime = (TextView) findViewById(R.id.advertisement_time);
        skipAdvertisement = (Button) findViewById(R.id.skip_advertisement);
    }

    public static void startAdvertisementActivity(Context context) {
        Intent intent = new Intent(context, AdvertisementActivity.class);
        context.startActivity(intent);
    }
}
