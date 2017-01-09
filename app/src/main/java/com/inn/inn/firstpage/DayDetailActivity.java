package com.inn.inn.firstpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;
import com.inn.inn.customview.DayDetailTextView;
import com.inn.inn.firstpage.model.DayBaseData;
import com.inn.inn.firstpage.model.DayDetail;
import com.nostra13.universalimageloader.core.ImageLoader;

public class DayDetailActivity extends InnBaseActivity {

    private static final String INTENT_KEY_DAY_DETAIL = "INTENT_KEY_DAY_DETAIL";

    private DayDetail dayDetail;
    private ImageView topImageView;
    private Toolbar toolbar;
    private LinearLayout androidContentLayout, iOSContentLayout, webContentLayout,
            appContentLayout, allContentLayout, recommendContentLayout,
            expandContentLayout;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_day_detail);
        initView();
        initData();
        loadData();
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

    private void loadData() {
        if (null != dayDetail.getResults().getAndroid()) {
            for (DayBaseData dayBaseData : dayDetail.getResults().getAndroid()) {
                androidContentLayout.addView(getContentTextView(dayBaseData.getDesc(), dayBaseData.getUrl()));
            }
            androidContentLayout.setVisibility(View.VISIBLE);
        } else {
            androidContentLayout.setVisibility(View.GONE);
        }

        if (null != dayDetail.getResults().getiOS()) {
            for (DayBaseData dayBaseData : dayDetail.getResults().getiOS()) {
                iOSContentLayout.addView(getContentTextView(dayBaseData.getDesc(), dayBaseData.getUrl()));
            }
            iOSContentLayout.setVisibility(View.VISIBLE);
        } else {
            iOSContentLayout.setVisibility(View.GONE);
        }

        if (null != dayDetail.getResults().get前端()) {
            for (DayBaseData dayBaseData : dayDetail.getResults().get前端()) {
                webContentLayout.addView(getContentTextView(dayBaseData.getDesc(), dayBaseData.getUrl()));
            }
            webContentLayout.setVisibility(View.VISIBLE);
        } else {
            webContentLayout.setVisibility(View.GONE);
        }

        if (null != dayDetail.getResults().getApp()) {
            for (DayBaseData dayBaseData : dayDetail.getResults().getApp()) {
                appContentLayout.addView(getContentTextView(dayBaseData.getDesc(), dayBaseData.getUrl()));
            }
            appContentLayout.setVisibility(View.VISIBLE);
        } else {
            appContentLayout.setVisibility(View.GONE);
        }

        if (null != dayDetail.getResults().getAll()) {
            for (DayBaseData dayBaseData : dayDetail.getResults().getAll()) {
                allContentLayout.addView(getContentTextView(dayBaseData.getDesc(), dayBaseData.getUrl()));
            }
            allContentLayout.setVisibility(View.VISIBLE);
        } else {
            allContentLayout.setVisibility(View.GONE);
        }

        if (null != dayDetail.getResults().get瞎推荐()) {
            for (DayBaseData dayBaseData : dayDetail.getResults().get瞎推荐()) {
                recommendContentLayout.addView(getContentTextView(dayBaseData.getDesc(), dayBaseData.getUrl()));
            }
            recommendContentLayout.setVisibility(View.VISIBLE);
        } else {
            recommendContentLayout.setVisibility(View.GONE);
        }

        if (null != dayDetail.getResults().get拓展资源()) {
            for (DayBaseData dayBaseData : dayDetail.getResults().get拓展资源()) {
                expandContentLayout.addView(getContentTextView(dayBaseData.getDesc(), dayBaseData.getUrl()));
            }
            expandContentLayout.setVisibility(View.VISIBLE);
        } else {
            expandContentLayout.setVisibility(View.GONE);
        }
    }

    private DayDetailTextView getContentTextView(final String content, final String contentUrl) {
        DayDetailTextView textView = new DayDetailTextView(context);
        textView.setContentTextView(content);
        textView.setPadding(30, 20, 30, 20);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayDetailContentActivity.startDayDetailContentActivity(context, content, contentUrl);
            }
        });
        return textView;
    }

    private void initView() {
        topImageView = (ImageView) findViewById(R.id.day_detail_image);
        toolbar = (Toolbar) findViewById(R.id.day_detail_toolbar);
        androidContentLayout = (LinearLayout) findViewById(R.id.day_detail_content_android);
        iOSContentLayout = (LinearLayout) findViewById(R.id.day_detail_content_ios);
        webContentLayout = (LinearLayout) findViewById(R.id.day_detail_content_web);
        appContentLayout = (LinearLayout) findViewById(R.id.day_detail_content_app);
        allContentLayout = (LinearLayout) findViewById(R.id.day_detail_content_all);
        recommendContentLayout = (LinearLayout) findViewById(R.id.day_detail_content_recommend);
        expandContentLayout = (LinearLayout) findViewById(R.id.day_detail_content_expand);
    }

    public static void startDayDetailActivity(Activity context, DayDetail dayDetail, ImageView imageView) {
        Intent intent = new Intent(context, DayDetailActivity.class);
        intent.putExtra(INTENT_KEY_DAY_DETAIL, dayDetail);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, imageView, "ImageView");
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
}
