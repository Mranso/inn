package com.inn.inn.fourthpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inn.inn.R;
import com.inn.inn.customview.TopBarView;


public class FourthFragment extends Fragment {

    private TopBarView topBarView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fourth_fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        topBarView = (TopBarView) view.findViewById(R.id.fourth_page_top_bar);
        topBarView.setTopBarTitle("我的");
    }
}
