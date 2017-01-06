package com.inn.inn.firstpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;
import com.inn.inn.firstpage.model.DayDetail;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DayDetailActivity extends InnBaseActivity {

    private static final String INTENT_KEY_DAY_DETAIL = "INTENT_KEY_DAY_DETAIL";

    private DayDetail dayDetail;
    private ImageView topImageView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_day_detail);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//决定左上角的图标是否可以点击
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//决定左上角图标的右侧是否有向左的小箭头
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        dayDetail = (DayDetail) intent.getSerializableExtra(INTENT_KEY_DAY_DETAIL);
        String imageUrl = dayDetail.getResults().get福利().get(0).getUrl();
        ImageLoader.getInstance().displayImage(imageUrl, topImageView);
    }

    private void initView() {
        topImageView = (ImageView) findViewById(R.id.day_detail_image);
        toolbar = (Toolbar) findViewById(R.id.day_detail_toolbar);
    }

    public static void startDayDetailActivity(Activity context, DayDetail dayDetail, ImageView imageView) {
        Intent intent = new Intent(context, DayDetailActivity.class);
        intent.putExtra(INTENT_KEY_DAY_DETAIL, dayDetail);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, imageView, "ImageView");
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
}
