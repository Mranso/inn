package com.inn.inn.mainpage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;
import com.taobao.hotfix.HotFixManager;

public class MainActivity extends InnBaseActivity {

    private Button pullNewPatch, test;
    private String testData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initData() {
        testData = "¥¥¥¥¥¥这是最新的数据¥¥¥¥¥¥¥¥";
        Toast.makeText(this, testData, Toast.LENGTH_SHORT).show();
    }

    private void initListener() {

        pullNewPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotFixManager.getInstance().queryNewHotPatch();
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    private void initView() {
        pullNewPatch = (Button) findViewById(R.id.pull_new_patch);
        test = (Button) findViewById(R.id.test);
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }
}
