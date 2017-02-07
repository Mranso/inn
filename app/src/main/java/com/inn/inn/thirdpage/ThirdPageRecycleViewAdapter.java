package com.inn.inn.thirdpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inn.inn.R;
import com.inn.inn.thirdpage.model.ResourceItemData;

import java.util.ArrayList;
import java.util.List;


public class ThirdPageRecycleViewAdapter extends RecyclerView.Adapter<ThirdPageRecycleViewAdapter.ResourceViewHolder>{

    private List<ResourceItemData> resourceItemDatas = new ArrayList<>();
    private Context context;

    public ThirdPageRecycleViewAdapter(Context context){
        this.context = context;
    }

    @Override
    public ResourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResourceViewHolder(LayoutInflater.from(context).inflate(R.layout.third_page_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ResourceViewHolder holder, int position) {
        ResourceItemData resourceItemData = resourceItemDatas.get(position);
        holder.content.setText(resourceItemData.getDesc());
        holder.who.setText(resourceItemData.getWho());
        String time = resourceItemData.getCreatedAt().substring(0, 10);
        holder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return resourceItemDatas.size();
    }

    class ResourceViewHolder extends RecyclerView.ViewHolder{

        private TextView content;
        private TextView who;
        private TextView time;
        private RelativeLayout itemLayout;

        public ResourceViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.resource_list_item_content);
            who = (TextView) itemView.findViewById(R.id.resource_list_item_who);
            time = (TextView) itemView.findViewById(R.id.resource_list_item_time);
            itemLayout = (RelativeLayout) itemView.findViewById(R.id.resource_list_item_layout);
        }
    }

    public void refreshResourceList(List<ResourceItemData> resourceItemDatas, int startItemIndex){
        this.resourceItemDatas = resourceItemDatas;
        notifyItemRangeChanged(startItemIndex, 20);
    }
}
