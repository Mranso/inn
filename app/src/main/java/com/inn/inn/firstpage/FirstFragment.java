package com.inn.inn.firstpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.inn.inn.R;
import com.inn.inn.customview.TopBarView;
import com.inn.inn.firstpage.model.DayList;
import com.inn.inn.firstpage.model.TimeList;
import com.inn.inn.mainpage.WelcomeActivity;
import com.inn.inn.network.InnHttpClient;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class FirstFragment extends Fragment {

    private Context context;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    private List<DayList> dayLists = new ArrayList<>();
    private List<String> timeLists = new ArrayList<>();

    private RecyclerView recyclerView;
    private TopBarView topBarView;
    private FirstPageRecycleViewAdapter firstPageRecycleViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        View view = inflater.inflate(R.layout.first_fragment_layout, container, false);
        initView(view);
        initRecycleView();
        initListener();
        return view;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.first_page_recycle_view);
        topBarView = (TopBarView) view.findViewById(R.id.first_page_top_bar);
        topBarView.setTopBarTitle("每日推荐");
    }

    private void initRecycleView() {
        firstPageRecycleViewAdapter = new FirstPageRecycleViewAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(firstPageRecycleViewAdapter);
    }

    private void initListener() {
        topBarView.setOnTopBarClickListener(new TopBarView.TopBarClickListener() {
            @Override
            public void leftClickListener() {

            }

            @Override
            public void rightClickListener() {

            }
        });

        firstPageRecycleViewAdapter.setOnItemClickListener(new FirstPageRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        loadNetData();
    }

    private void loadNetData() {
        for (int i = 0; i <= 20; i++) {
            getDayListData(timeLists.get(i));
        }
    }

    private void initData() {
        Intent intent = getActivity().getIntent();
        TimeList timeList = (TimeList) intent.getSerializableExtra(WelcomeActivity.BASE_TIME_DATA);
        timeLists = timeList.getResults();
    }

    private void getDayListData(String timeString) {
        String time = timeString.replaceAll("-", "/");
        Subscription subscription = InnHttpClient.getHttpServiceInstance().getDayList(time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DayList>() {
                    @Override
                    public void call(DayList dayList) {
                        dayLists.add(dayList);
                        firstPageRecycleViewAdapter.refreshData(dayLists);
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}
