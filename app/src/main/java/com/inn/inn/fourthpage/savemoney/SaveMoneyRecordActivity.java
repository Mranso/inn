package com.inn.inn.fourthpage.savemoney;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.inn.inn.R;
import com.inn.inn.common.InnBaseActivity;
import com.inn.inn.customview.TopBarView;

import java.util.List;

public class SaveMoneyRecordActivity extends InnBaseActivity {

    private RecyclerView recyclerView;
    private TopBarView topBarView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_money_record);
        context = this;
        initView();
        initData();
    }

    private void initData() {
        List<SaveMoney> saveMoneys = SaveMoneyDao.getInstance().loadUsedSaveMoneysAndOrderBy();
        SaveMoneyRecordAdapter saveMoneyRecordAdapter = new SaveMoneyRecordAdapter(context, saveMoneys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(saveMoneyRecordAdapter);

    }

    private void initView() {
        topBarView = (TopBarView) findViewById(R.id.save_money_record_top_bar);
        topBarView.setTopBarTitle("存钱记录");
        recyclerView = (RecyclerView) findViewById(R.id.save_money_record_recycle_view);
    }

    public static void startSaveMoneyRecordActivity(Context context){
        Intent intent = new Intent(context, SaveMoneyRecordActivity.class);
        context.startActivity(intent);
    }
}
