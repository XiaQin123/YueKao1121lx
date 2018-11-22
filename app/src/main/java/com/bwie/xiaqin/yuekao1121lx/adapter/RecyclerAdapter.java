package com.bwie.xiaqin.yuekao1121lx.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.xiaqin.yuekao1121lx.R;
import com.bwie.xiaqin.yuekao1121lx.bean.RecycleBean;

import java.util.List;

/**
 * Created by lenovo on 2018/11/21.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private Context context;
    private List<RecycleBean.DataBean> list;

    public RecyclerAdapter(Context context, List<RecycleBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.shou_item_adapter, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(holder.img);
        holder.txtTitle.setText(list.get(position).getTitle());

    }

    // 获取条目的数量
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView txtTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txtTitle = itemView.findViewById(R.id.title);


        }
    }


}
