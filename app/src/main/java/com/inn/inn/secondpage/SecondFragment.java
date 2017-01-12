package com.inn.inn.secondpage;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inn.inn.R;
import com.inn.inn.customview.TopBarView;
import com.inn.inn.firstpage.model.DayBaseData;
import com.inn.inn.network.InnHttpClient;
import com.inn.inn.secondpage.model.BeautyListResult;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class SecondFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TopBarView topBarView;

    private Activity context;

    private BeautyListAdapter beautyListAdapter;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    private List<DayBaseData> baseDatas = new ArrayList<>();
    private int page = 1;
    private static final int PAGE_SIZE = 20;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.second_fragment_layout, container, false);
        context = getActivity();
        initView(view);
        initRecycle();
        initListener();
        return view;
    }

    private void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                baseDatas.clear();
                loadNetData();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    if (!recyclerView.canScrollVertically(1)) {
                        loadNetData();
                    }
                }
            }
        });
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.second_page_recycle_view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.second_page_swipe_refresh);
        topBarView = (TopBarView) view.findViewById(R.id.beauty_page_top_bar);
        topBarView.setTopBarTitle("福利");
    }

    private void initRecycle() {
        beautyListAdapter = new BeautyListAdapter(context);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(beautyListAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        loadNetData();
    }

    private void loadNetData() {
        swipeRefreshLayout.setRefreshing(true);
        Subscription subscription = InnHttpClient.getHttpServiceInstance().getBeautyList(PAGE_SIZE, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BeautyListResult>() {
                    @Override
                    public void call(BeautyListResult beautyListResult) {
                        baseDatas.addAll(beautyListResult.getResults());
                        beautyListAdapter.refreshBeautyList(baseDatas);
                        swipeRefreshLayout.setRefreshing(false);
                        page++;
                    }
                });
        compositeSubscription.add(subscription);
    }


}
