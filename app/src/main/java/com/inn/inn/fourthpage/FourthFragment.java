package com.inn.inn.fourthpage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.inn.inn.R;
import com.inn.inn.customview.TopBarView;
import com.inn.inn.secondpage.savemoney.SaveMoneyActivity;


public class FourthFragment extends Fragment {

    private Context context;
    private TopBarView topBarView;
    private LinearLayout saveMoney;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        View view = inflater.inflate(R.layout.fourth_fragment_layout, container, false);
        initView(view);
        initListener();
        return view;
    }

    private void initListener() {
        saveMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveMoneyActivity.startSaveMoneyActivity(context);
            }
        });
    }

    private void initView(View view) {
        saveMoney = (LinearLayout) view.findViewById(R.id.fourth_page_save_money);
        topBarView = (TopBarView) view.findViewById(R.id.fourth_page_top_bar);
        topBarView.setTopBarTitle("我的");
    }
}
