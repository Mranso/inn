package com.inn.inn.firstpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inn.inn.R;

import java.util.ArrayList;
import java.util.List;


public class FirstPageRecycleViewAdapter extends RecyclerView.Adapter<FirstPageRecycleViewAdapter.TimeListViewHolder> {

    private Context context;
    private List<String> timesData = new ArrayList<>();

    public FirstPageRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TimeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimeListViewHolder(LayoutInflater.from(context).inflate(R.layout.first_time_list_item_layout, parent, false));
    }

    @Override
    public int getItemCount() {
        return timesData.size();
    }

    @Override
    public void onBindViewHolder(TimeListViewHolder holder, int position) {
        holder.tv.setText(timesData.get(position));
    }

    class TimeListViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public TimeListViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.first_time_list_item);
        }
    }

    public void refreshData(List<String> timesData){
        this.timesData = timesData;
        notifyDataSetChanged();
    }

}
