package com.example.moni.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.moni.R;
import com.example.moni.bean.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created   by    Dewey
 * RecyclerView 展示图片数据
 */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {
    private Context context;
    private List<NewsBean.NewslistBean> list;

    public MyListAdapter(Context context, List<NewsBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    //请求数据的方法
    public void addData(List<NewsBean.NewslistBean>  data){
        if (list != null){
            list.clear();
            list.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.list_adapter, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //设置参数数据
        holder.headImage.setImageURI(list.get(position).getPicUrl());
        holder.title.setText(list.get(position).getTitle());
        holder.desc.setText(list.get(position).getDescription());
        holder.time.setText(list.get(position).getCtime());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.headImage)
        SimpleDraweeView headImage;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.desc)
        TextView desc;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
