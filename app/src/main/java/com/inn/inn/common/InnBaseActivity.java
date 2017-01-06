package com.inn.inn.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.inn.inn.R;
import com.inn.inn.util.StatusBarUtils;

public abstract class InnBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.BaseTheme);
        StatusBarUtils.setColor(this, getResources().getColor(R.color.base_color), 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
