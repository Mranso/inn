package com.inn.inn.firstpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inn.inn.R;
import com.inn.inn.firstpage.model.DataTypeResult;
import com.inn.inn.firstpage.model.DayList;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class FirstPageRecycleViewAdapter extends RecyclerView.Adapter<FirstPageRecycleViewAdapter.TimeListViewHolder> {

    private Context context;
    private List<DayList> dayDataList = new ArrayList<>();

    public FirstPageRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TimeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TimeListViewHolder(LayoutInflater.from(context).inflate(R.layout.first_time_list_item_layout, parent, false));
    }

    @Override
    public int getItemCount() {
        return dayDataList.size();
    }

    @Override
    public void onBindViewHolder(TimeListViewHolder holder, int position) {
        DataTypeResult dataTypeResult = dayDataList.get(position).getResults();
        if (dataTypeResult.get福利() != null) {
            ImageLoader.getInstance().displayImage(dataTypeResult.get福利().get(0).getUrl(), holder.imageView);
        }
        if (dataTypeResult.getAndroid() != null) {
            holder.sourceOne.setText(dataTypeResult.getAndroid().get(0).getDesc());
        }

        if (dataTypeResult.getiOS() != null) {
            holder.sourceTwo.setText(dataTypeResult.getiOS().get(0).getDesc());
        }

        if (dataTypeResult.get前端() != null) {
            holder.sourceThree.setText(dataTypeResult.get前端().get(0).getDesc());
        }

        if (dataTypeResult.get拓展资源() != null) {
            holder.sourceFour.setText(dataTypeResult.get拓展资源().get(0).getDesc());
        }

    }

    class TimeListViewHolder extends RecyclerView.ViewHolder {
        TextView sourceOne;
        TextView sourceTwo;
        TextView sourceThree;
        TextView sourceFour;
        ImageView imageView;

        public TimeListViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.first_page_list_item_image);
            sourceOne = (TextView) view.findViewById(R.id.first_page_list_item_source_one);
            sourceTwo = (TextView) view.findViewById(R.id.first_page_list_item_source_two);
            sourceThree = (TextView) view.findViewById(R.id.first_page_list_item_source_three);
            sourceFour = (TextView) view.findViewById(R.id.first_page_list_item_source_four);
        }
    }

    public void refreshData(List<DayList> dayDataList) {
        this.dayDataList = dayDataList;
        notifyDataSetChanged();
    }

}
