package com.example.search_shopcart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.search_shopcart.R;
import com.example.search_shopcart.ShopActivity;
import java.util.List;

/**
 * 历史搜索记录数据 适配器
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private Context context;
    private List<String> list;

    public HistoryAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_history, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(list.get(position));

        //点击效果图一上的任意搜索记录，跳转到购物车界面
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,ShopActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.history_text);
        }
    }
}
