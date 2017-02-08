package com.inn.inn.secondpage.savemoney;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;
import com.inn.inn.util.SharedPreferencesUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SaveMoneyActivity extends InnBaseActivity {

    private Button saveMoneyButton;
    private TextView everydayMoney;
    private TextView moneyBoxBalance;
    private List<String> moneyList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_money);
        initData();
        initView();
        initListener();
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
                animationTo(everydayMoney, moneyList.get(position));
                moneyList.remove(position);
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : moneyList) {
                    stringBuilder.append(s).append("|");
                }
                SharedPreferencesUtils.getInstance().putMoneyString(stringBuilder.toString());
            }
        });
    }

    private void initView() {
        saveMoneyButton = (Button) findViewById(R.id.save_money_everyday_button);
        everydayMoney = (TextView) findViewById(R.id.save_money_everyday_text);
        moneyBoxBalance = (TextView) findViewById(R.id.my_money_box_balance);
    }

    public static void startSaveMoneyActivity(Context context){
        Intent intent = new Intent(context, SaveMoneyActivity.class);
        context.startActivity(intent);
    }

    public void animationTo(final TextView targetTextView, String targetValue) {
        try {
            Float.valueOf(targetValue);
        } catch (Exception e) {
            targetValue = "0";
        }
        //fraction 是时间因子（时间因子变化一定是线性的）
        ValueAnimator objectAnimator = ObjectAnimator.ofObject(new TypeEvaluator<String>() {
            @Override
            public String evaluate(float fraction, String startStr, String endStr) {
                float startValue = Float.valueOf(startStr);
                float endValue = Float.valueOf(endStr);

                float targetValue = ((endValue - startValue) * new AccelerateDecelerateInterpolator().getInterpolation(fraction) + startValue);
                DecimalFormat df = new DecimalFormat("#0");
                String targetStr = df.format(targetValue);
                targetTextView.setText(targetStr);
                return targetStr;
            }
        }, "0", targetValue);
        objectAnimator.setDuration(900);
        objectAnimator.start();
    }
}
