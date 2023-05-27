package com.example.demo.scroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;

import java.util.List;

public class HosGridViewAdapter extends RecyclerView.Adapter {
    private List<DateBean> list;
    private Context mContext;
    public int index;
    public OnRvItemClick onRvItemClick;

    public HosGridViewAdapter(List<DateBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        index = 0;
    }

    public void setOnHosGridItemClick(OnRvItemClick onRvItemClick) {
        this.onRvItemClick = onRvItemClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hosgrid, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.name.setText(list.get(position).getDate());
        viewHolder.name2.setText(list.get(position).getTime());
        if (position == index) {
            viewHolder.llitem.setBackground(mContext.getResources().getDrawable(R.drawable.filleheavybg));
//            viewHolder.name.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
//            viewHolder.name2.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        } else {
            viewHolder.llitem.setBackground(mContext.getResources().getDrawable(R.drawable.fillelightbg));
            viewHolder.name.setTextColor(Color.WHITE);
            viewHolder.name2.setTextColor(Color.WHITE);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != index) {
                    index = position;
                    notifyDataSetChanged();
                }
                onRvItemClick.OnRvItemClick(position, v);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, name2;
        LinearLayout llitem;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            name2 = itemView.findViewById(R.id.name2);
            llitem = itemView.findViewById(R.id.ll_item);
        }
    }
    public interface OnRvItemClick {
        void OnRvItemClick(int position, View view);
    }
}