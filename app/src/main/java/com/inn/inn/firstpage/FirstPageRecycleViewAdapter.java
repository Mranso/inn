package com.inn.inn.firstpage;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inn.inn.R;
import com.inn.inn.firstpage.model.DataTypeResult;
import com.inn.inn.firstpage.model.DayDetail;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class FirstPageRecycleViewAdapter extends RecyclerView.Adapter<FirstPageRecycleViewAdapter.TimeListViewHolder> {

    private Activity context;
    private List<DayDetail> dayDataList = new ArrayList<>();

    public FirstPageRecycleViewAdapter(Activity context) {
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
    public void onBindViewHolder(final TimeListViewHolder holder, final int position) {
        DataTypeResult dataTypeResult = dayDataList.get(position).getResults();
        if (dataTypeResult.get福利() != null) {
            ImageLoader.getInstance().displayImage(dataTypeResult.get福利().get(0).getUrl(), holder.imageView, new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build());
        }

        List<String> categoryLists = dayDataList.get(position).getCategory();
        for (int i = 0; i < 4; i++) {
            String sourceString = getSourceString(categoryLists.get(i), dataTypeResult);
            String content = "【" + categoryLists.get(i) + "】" + sourceString;
            switch (i) {
                case 0:
                    if (!"".equals(sourceString)) {
                        holder.sourceTwo.setText(content);
                    } else {
                        holder.sourceTwo.setVisibility(View.GONE);
                    }
                    break;
                case 1:
                    if (!"".equals(sourceString)) {
                        holder.sourceOne.setText(content);
                    } else {
                        holder.sourceOne.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    if (!"".equals(sourceString)) {
                        holder.sourceThree.setText(content);
                    } else {
                        holder.sourceThree.setVisibility(View.GONE);
                    }
                    break;
                case 3:
                    if (!"".equals(sourceString)) {
                        holder.sourceFour.setText(content);
                    } else {
                        holder.sourceFour.setVisibility(View.GONE);
                    }
                    break;
            }
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DayDetailActivity.startDayDetailActivity(context, dayDataList.get(position), holder.imageView);
            }
        });
    }

    private String getSourceString(String category, DataTypeResult dataTypeResult) {
        String sourceString = "";
        switch (category) {
            case "Android":
                sourceString = dataTypeResult.getAndroid().get(0).getDesc();
                break;
            case "iOS":
                sourceString = dataTypeResult.getiOS().get(0).getDesc();
                break;
            case "休息视频":
                sourceString = dataTypeResult.get休息视频().get(0).getDesc();
                break;
            case "拓展资源":
                sourceString = dataTypeResult.get拓展资源().get(0).getDesc();
                break;
            case "瞎推荐":
                sourceString = dataTypeResult.get瞎推荐().get(0).getDesc();
                break;
            case "前端":
                sourceString = dataTypeResult.get前端().get(0).getDesc();
                break;
            case "App":
                sourceString = dataTypeResult.getApp().get(0).getDesc();
                break;
            case "all":
                sourceString = dataTypeResult.getAll().get(0).getDesc();
                break;
        }
        return sourceString;
    }

    class TimeListViewHolder extends RecyclerView.ViewHolder {
        TextView sourceOne;
        TextView sourceTwo;
        TextView sourceThree;
        TextView sourceFour;
        ImageView imageView;
        RelativeLayout relativeLayout;

        public TimeListViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.first_page_list_item_image);
            sourceOne = (TextView) view.findViewById(R.id.first_page_list_item_source_one);
            sourceTwo = (TextView) view.findViewById(R.id.first_page_list_item_source_two);
            sourceThree = (TextView) view.findViewById(R.id.first_page_list_item_source_three);
            sourceFour = (TextView) view.findViewById(R.id.first_page_list_item_source_four);
            relativeLayout = (RelativeLayout) view.findViewById(R.id.first_page_list_item_layout);
        }
    }

    public void refreshData(List<DayDetail> dayDataList) {
        this.dayDataList = dayDataList;
        notifyDataSetChanged();
    }
}
