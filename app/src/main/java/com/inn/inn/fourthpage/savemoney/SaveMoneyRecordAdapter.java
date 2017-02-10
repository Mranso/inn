package com.inn.inn.fourthpage.savemoney;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inn.inn.R;

import java.util.List;


public class SaveMoneyRecordAdapter extends RecyclerView.Adapter<SaveMoneyRecordAdapter.SaveMoneyRecordViewHolder> {

    private List<SaveMoney> saveMoneys;
    private Context context;

    public SaveMoneyRecordAdapter(Context context, List<SaveMoney> saveMoneys) {
        this.context = context;
        this.saveMoneys = saveMoneys;
    }

    @Override
    public SaveMoneyRecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SaveMoneyRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.save_money_record_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(SaveMoneyRecordViewHolder holder, int position) {
        SaveMoney saveMoney = saveMoneys.get(position);
        holder.time.setText(saveMoney.getUsedTime());
        holder.money.setText(saveMoney.getMoney()+" 元");
    }

    @Override
    public int getItemCount() {
        return saveMoneys.size();
    }

    class SaveMoneyRecordViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView money;

        public SaveMoneyRecordViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.save_money_record_item_time);
            money = (TextView) itemView.findViewById(R.id.save_money_record_item_money);
        }
    }
}
