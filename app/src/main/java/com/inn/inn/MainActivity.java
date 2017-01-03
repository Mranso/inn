package com.inn.inn;

import android.os.Bundle;

import com.inn.inn.common.InnBaseActivity;

public class MainActivity extends InnBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initListener() {

    }

    private void initView() {
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }
}
