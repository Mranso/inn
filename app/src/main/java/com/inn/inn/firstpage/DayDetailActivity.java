package com.inn.inn.firstpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;
import com.inn.inn.customview.TopBarView;
import com.inn.inn.firstpage.model.DayDetail;

public class DayDetailActivity extends InnBaseActivity {

    private static final String INTENT_KEY_DAY_DETAIL = "INTENT_KEY_DAY_DETAIL";

    private TopBarView topBarView;
    private DayDetail dayDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        dayDetail = (DayDetail) intent.getSerializableExtra(INTENT_KEY_DAY_DETAIL);
    }

    private void initView() {
        topBarView = (TopBarView) findViewById(R.id.day_detail_top_bar);
        topBarView.setTopBarTitle("每日详情");
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_day_detail);
    }

    public static void startDayDetailActivity(Context context, DayDetail dayDetail){
        Intent intent = new Intent(context, DayDetailActivity.class);
        intent.putExtra(INTENT_KEY_DAY_DETAIL, dayDetail);
        context.startActivity(intent);
    }
}
