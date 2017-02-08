package com.inn.inn.fourthpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.inn.inn.R;
import com.inn.inn.customview.TopBarView;
import com.inn.inn.util.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class FourthFragment extends Fragment {

    private TopBarView topBarView;
    private Button saveMoneyButton;
    private TextView moneyTextView;
    private List<String> moneyList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fourth_fragment_layout, container, false);
        initView(view);
        initListener();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        String moneyString = SharedPreferencesUtils.getInstance().getMoneyString();
        StringBuilder spMoneyString = new StringBuilder();
        if (moneyString.equals("9999")) {
            for (int i = 1; i <= 365; i++) {
                moneyList.add(String.valueOf(i));
                if (i == 365) {
                    spMoneyString.append(String.valueOf(i));
                } else {
                    spMoneyString.append(String.valueOf(i)).append("|");
                }
            }
            Collections.shuffle(moneyList);
            SharedPreferencesUtils.getInstance().putMoneyString(spMoneyString.toString());
        } else {
            String[] moneys = moneyString.split("|");
            moneyList = Arrays.asList(moneys);
            Collections.shuffle(moneyList);
        }
    }

    private void initListener() {
        saveMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int position = random.nextInt(moneyList.size());

                moneyTextView.setText(moneyList.get(position));
                moneyList.remove(position);
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : moneyList) {
                    stringBuilder.append(s).append("|");
                }
                SharedPreferencesUtils.getInstance().putMoneyString(stringBuilder.toString());
            }
        });
    }

    private void initView(View view) {
        topBarView = (TopBarView) view.findViewById(R.id.fourth_page_top_bar);
        topBarView.setTopBarTitle("我的");
        saveMoneyButton = (Button) view.findViewById(R.id.save_money);
        moneyTextView = (TextView) view.findViewById(R.id.money);
    }
}
