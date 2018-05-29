package com.example.zhoukao4.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhoukao4.R;
import com.example.zhoukao4.bean.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class XrvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsBean.NewslistBean> list;
    private Context context;
    private final LayoutInflater inflater;

    public XrvAdapter(List<NewsBean.NewslistBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.xrv_item, parent, false);
        XrvViewHolder xrvViewHolder=new XrvViewHolder(view);
        return xrvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        XrvViewHolder xrvViewHolder= (XrvViewHolder) holder;
        xrvViewHolder.img_simp.setImageURI(list.get(position).getPicUrl());
        xrvViewHolder.tv_title.setText(list.get(position).getTitle());
        xrvViewHolder.tv_time.setText(list.get(position).getDescription()+"--"+list.get(position).getCtime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class XrvViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView img_simp;
        TextView tv_title;
        TextView tv_time;

        public XrvViewHolder(View view) {
            super(view);
            this.img_simp = (SimpleDraweeView) view.findViewById(R.id.img_simp);
            this.tv_title = (TextView) view.findViewById(R.id.tv_title);
            this.tv_time = (TextView) view.findViewById(R.id.tv_time);
        }
    }

    public void refresh(List<NewsBean.NewslistBean> newslist){
        this.list.clear();
        this.list.addAll(newslist);
        notifyDataSetChanged();
    }

    public void loadMore(List<NewsBean.NewslistBean> newslist){
        this.list.addAll(newslist);
        notifyDataSetChanged();
    }

}
