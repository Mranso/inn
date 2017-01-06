package com.inn.inn.mainpage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;
import com.inn.inn.customview.NoScrollViewPager;
import com.inn.inn.firstpage.FirstFragment;

import java.util.ArrayList;

public class MainTabActivity extends InnBaseActivity{

    private static final int VIEW_PAGE_FLAG_ONE = 0;
    private static final int VIEW_PAGE_FLAG_TWO = 1;
    private static final int VIEW_PAGE_FLAG_THREE = 2;
    private static final int VIEW_PAGE_FLAG_FOUR = 3;

    private RadioButton firstButton, secondButton, thirdButton, fourthButton;
    private NoScrollViewPager viewPager;
    private RadioGroup radioGroup;
    private Context context;
    private FragmentPageViewAdapter fragmentPageViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        context = this;
        initView();
        initFragment();
        initListener();
        switchViewPage(VIEW_PAGE_FLAG_ONE);
    }

    private void initListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_tab_radio_first:
                        switchViewPage(VIEW_PAGE_FLAG_ONE);
                        break;
                    case R.id.main_tab_radio_second:
                        switchViewPage(VIEW_PAGE_FLAG_TWO);
                        break;
                    case R.id.main_tab_radio_third:
                        switchViewPage(VIEW_PAGE_FLAG_THREE);
                        break;
                    case R.id.main_tab_radio_fourth:
                        switchViewPage(VIEW_PAGE_FLAG_FOUR);
                        break;
                }
            }
        });
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new FirstFragment());
        fragmentArrayList.add(new SecondFragment());
        fragmentArrayList.add(new ThirdFragment());
        fragmentArrayList.add(new FourthFragment());
        fragmentPageViewAdapter = new FragmentPageViewAdapter(fragmentManager, fragmentArrayList);
        viewPager.setOffscreenPageLimit(fragmentArrayList.size() - 1);
        viewPager.setAdapter(fragmentPageViewAdapter);
    }

    private void initView() {
        viewPager = (NoScrollViewPager) findViewById(R.id.main_tab_view_page);
        firstButton = (RadioButton) findViewById(R.id.main_tab_radio_first);
        secondButton = (RadioButton) findViewById(R.id.main_tab_radio_second);
        thirdButton = (RadioButton) findViewById(R.id.main_tab_radio_third);
        fourthButton = (RadioButton) findViewById(R.id.main_tab_radio_fourth);
        radioGroup = (RadioGroup) findViewById(R.id.main_tab_radio_group);
    }

    private void switchViewPage(int flag){
        viewPager.setCurrentItem(flag, false);
        switch (flag){
            case VIEW_PAGE_FLAG_ONE:
                firstButton.setChecked(true);
                break;
            case VIEW_PAGE_FLAG_TWO:
                secondButton.setChecked(true);
                break;
            case VIEW_PAGE_FLAG_THREE:
                thirdButton.setChecked(true);
                break;
            case VIEW_PAGE_FLAG_FOUR:
                fourthButton.setChecked(true);
                break;
        }
    }
}
