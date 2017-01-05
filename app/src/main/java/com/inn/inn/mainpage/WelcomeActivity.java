package com.inn.inn.mainpage;

import android.content.Intent;
import android.os.Bundle;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;
import com.inn.inn.firstpage.model.TimeList;
import com.inn.inn.network.InnHttpClient;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class WelcomeActivity extends InnBaseActivity {

    public static final String BASE_TIME_DATA = "BASE_TIME_DATA";
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBaseData();
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_welcome);
    }

    public void getBaseData() {
        Subscription subscription = InnHttpClient.getHttpServiceInstance().getTimeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TimeList>() {
                    @Override
                    public void call(TimeList timeList) {
                        goMainTabActivity(timeList);
                    }
                });
        compositeSubscription.add(subscription);
    }

    private void goMainTabActivity(TimeList timeList) {
        Intent intent = new Intent(WelcomeActivity.this, MainTabActivity.class);
        intent.putExtra(BASE_TIME_DATA, timeList);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}
