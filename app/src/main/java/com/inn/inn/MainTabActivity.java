package com.inn.inn;

import android.os.Bundle;

import com.inn.inn.common.InnBaseActivity;

public class MainTabActivity extends InnBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main_tab);
    }
}
