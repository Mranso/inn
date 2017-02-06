package com.inn.inn.thirdpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inn.inn.R;
import com.inn.inn.customview.TopBarView;

public class ThirdFragment extends Fragment {

    private TopBarView topBarView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.third_fragment_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        topBarView = (TopBarView) view.findViewById(R.id.third_page_top_bar);
        topBarView.setTopBarTitle("分类资源");
    }
}
