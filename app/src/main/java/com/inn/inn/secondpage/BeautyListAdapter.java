package com.inn.inn.secondpage;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.inn.inn.R;
import com.inn.inn.firstpage.model.DayBaseData;
import com.inn.inn.util.imageloader.InnImageDisplayOptions;
import com.inn.inn.util.imageloader.InnImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BeautyListAdapter extends RecyclerView.Adapter<BeautyListAdapter.BeautyListViewHolder> {

    private List<DayBaseData> beautyLists = new ArrayList<>();
    private Activity context;

    public BeautyListAdapter(Activity context) {
        this.context = context;
    }

    @Override
    public BeautyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BeautyListAdapter.BeautyListViewHolder(LayoutInflater.from(context).inflate(R.layout.beauty_list_item_layout, parent, false));

    }

    @Override
    public void onBindViewHolder(final BeautyListViewHolder holder, int position) {
        final String url = beautyLists.get(position).getUrl();
        InnImageLoader.getInstance().displayImage(holder.imageView, url, new InnImageDisplayOptions.Builder().cacheInMemory(true).cacheInDisk(true).build());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyDetailActivity.startBeautyDetailActivity(context, url, holder.imageView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beautyLists.size();
    }

    class BeautyListViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BeautyListViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.beauty_list_item_image);
        }
    }

    public void refreshBeautyList(List<DayBaseData> beautyLists) {
        this.beautyLists = beautyLists;
        notifyDataSetChanged();
    }
}