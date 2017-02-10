package com.inn.inn.fourthpage.savemoney;

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
import com.inn.inn.util.DateUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SaveMoneyActivity extends InnBaseActivity {

    private Button saveMoneyButton;
    private TextView everydayMoney;
    private TextView moneyBoxBalance;
    private List<SaveMoney> moneyList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_money);
        initData();
        initView();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMoneyBoxBalance();
    }

    private void initData() {
        if (SaveMoneyDao.getInstance().checkDataIsEmpty()) {
            moneyList = SaveMoney.initSaveMoneyData();
            SaveMoneyDao.getInstance().insertPatients(moneyList);
        } else {
            moneyList = SaveMoneyDao.getInstance().loadUnusedSaveMoneys();
        }
    }

    private void initListener() {
        saveMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int position = random.nextInt(moneyList.size());
                SaveMoney saveMoney = moneyList.get(position);
                updateSaveMoneyDB(saveMoney);
                setEverydayMoney(saveMoney);
                setMoneyBoxBalance();
                moneyList.remove(position);
            }
        });
    }

    private void updateSaveMoneyDB(SaveMoney saveMoney){
        saveMoney.setUsedStatus(1);
        saveMoney.setUsedTime(DateUtil.getTodayStr());
        SaveMoneyDao.getInstance().updateSaveMoney(saveMoney);
    }

    private void setEverydayMoney(SaveMoney saveMoney){
        animationTo(everydayMoney, String.valueOf(saveMoney.getMoney()));
    }

    private void setMoneyBoxBalance(){
        List<SaveMoney> savedMoney = SaveMoneyDao.getInstance().loadUsedSaveMoneys();
        int moneySum = 0;
        for (SaveMoney money : savedMoney) {
            moneySum += money.getMoney();
        }
        animationTo(moneyBoxBalance, String.valueOf(moneySum));
    }

    private void initView() {
        saveMoneyButton = (Button) findViewById(R.id.save_money_everyday_button);
        everydayMoney = (TextView) findViewById(R.id.save_money_everyday_text);
        moneyBoxBalance = (TextView) findViewById(R.id.my_money_box_balance);
    }

    public static void startSaveMoneyActivity(Context context) {
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
