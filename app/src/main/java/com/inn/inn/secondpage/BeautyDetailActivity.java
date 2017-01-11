package com.inn.inn.secondpage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.widget.ImageView;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;
import com.inn.inn.util.imageloader.InnImageDisplayOptions;
import com.inn.inn.util.imageloader.InnImageLoader;

public class BeautyDetailActivity extends InnBaseActivity {

    private static final String INTENT_KEY_BEAUTY_DETAIL = "INTENT_KEY_BEAUTY_DETAIL";

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beaty_detail);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(INTENT_KEY_BEAUTY_DETAIL);
        InnImageLoader.getInstance().displayImage(imageView, imageUrl, new InnImageDisplayOptions.Builder().cacheInDisk(true).cacheInMemory(true).build());
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.beauty_detail_image_view);
    }

    public static void startBeautyDetailActivity(Activity context, String imageUrl, ImageView imageView) {
        Intent intent = new Intent(context, BeautyDetailActivity.class);
        intent.putExtra(INTENT_KEY_BEAUTY_DETAIL, imageUrl);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(context, imageView, "BeautyImageView");
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
}
