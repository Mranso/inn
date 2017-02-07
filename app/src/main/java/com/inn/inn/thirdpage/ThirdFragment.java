package com.inn.inn.thirdpage;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.inn.inn.R;
import com.inn.inn.customview.TopBarView;
import com.inn.inn.network.InnHttpClient;
import com.inn.inn.thirdpage.model.ResourceDataResult;
import com.inn.inn.thirdpage.model.ResourceItemData;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ThirdFragment extends Fragment {

    private TopBarView topBarView;
    private RecyclerView recyclerView;
    private Context context;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    private ThirdPageRecycleViewAdapter thirdPageRecycleViewAdapter;
    private PopupWindow popupWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        View view = inflater.inflate(R.layout.third_fragment_layout, container, false);
        initView(view);
        initRecycleView();
        initListener();
        return view;
    }

    private void initListener() {
        topBarView.setOnTopBarClickListener(new TopBarView.TopBarClickListener() {
            @Override
            public void leftClickListener() {

            }

            @Override
            public void rightClickListener() {
                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {
        View view = LayoutInflater.from(context).inflate(R.layout.third_more_menu_layout, null, false);
        initPopupWindowListener(view);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAsDropDown(topBarView.getTopBarRightView(), -340, 40);
    }

    private void initPopupWindowListener(View view) {
        view.findViewById(R.id.third_page_menu_android).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData("Android", 20, 1);
                popupWindow.dismiss();
            }
        });
        view.findViewById(R.id.third_page_menu_ios).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData("iOS", 20, 1);
                popupWindow.dismiss();
            }
        });
        view.findViewById(R.id.third_page_menu_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData("前端", 20, 1);
                popupWindow.dismiss();
            }
        });
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.third_page_recycle_view);
        topBarView = (TopBarView) view.findViewById(R.id.third_page_top_bar);
        topBarView.setTopBarTitle("分类资源");
        topBarView.setTopBarRightVisibility(true);
    }

    private void initRecycleView() {
        thirdPageRecycleViewAdapter = new ThirdPageRecycleViewAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(thirdPageRecycleViewAdapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData("Android", 20, 1);
    }

    private void loadData(String type, int size, int page) {
        Subscription subscription = InnHttpClient.getHttpServiceInstance().getResourceDataList(type, size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResourceDataResult>() {
                    @Override
                    public void call(ResourceDataResult resourceDataResult) {
                        List<ResourceItemData> resourceItemDatas = resourceDataResult.getResults();
                        thirdPageRecycleViewAdapter.refreshResourceList(resourceItemDatas, 0);
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
