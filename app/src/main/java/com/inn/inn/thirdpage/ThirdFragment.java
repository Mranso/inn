package com.inn.inn.thirdpage;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.inn.inn.R;
import com.inn.inn.customview.TopBarView;

import java.util.zip.Inflater;

public class ThirdFragment extends Fragment {

    private TopBarView topBarView;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        View view = inflater.inflate(R.layout.third_fragment_layout, container, false);
        initView(view);
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
                View view = LayoutInflater.from(context).inflate(R.layout.third_more_menu_layout, null, false);
                PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
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
        });
    }

    private void initView(View view) {
        topBarView = (TopBarView) view.findViewById(R.id.third_page_top_bar);
        topBarView.setTopBarTitle("分类资源");
        topBarView.setTopBarRightVisibility(true);
    }
}
