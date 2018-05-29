package com.example.search_shopcart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.search_shopcart.R;
import com.example.search_shopcart.XiangQingActivity;
import com.example.search_shopcart.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created   by   Dewey .
 * 搜索框结果适配器
 */
public class SearchActivityAdapter extends RecyclerView.Adapter<SearchActivityAdapter.SearchViewHolder> {
    @BindView(R.id.search_image)
    SimpleDraweeView searchImage;
    @BindView(R.id.search_text)
    TextView searchText;
    private Context context;
    private List<SearchBean.DataBean> list;

    public SearchActivityAdapter(Context context) {
        this.context = context;
    }

    //添加数据的方法
    public void addData(List<SearchBean.DataBean> data) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_item, null);
        SearchViewHolder tuijianViewHolder = new SearchViewHolder(view);
        return tuijianViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        if (list.size() > 0) {
            if (list.get(position).getImages().contains("|")) {
                String[] split = list.get(position).getImages().split("\\|");
                holder.searchImage.setImageURI(split[0]);
                holder.searchText.setText(list.get(position).getTitle());
            } else {
                holder.searchImage.setImageURI(list.get(position).getImages());
                holder.searchText.setText(list.get(position).getTitle());
            }
        }

        //条目的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传递商品数据将url传过去 访问详情页面
                Intent intent = new Intent(context, XiangQingActivity.class);
                intent.putExtra("pid", list.get(position).getPid() + "");
                intent.putExtra("images", list.get(position).getImages());
                intent.putExtra("bargainPrice", list.get(position).getBargainPrice() + "");
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("price", list.get(position).getPrice() + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_image)
        SimpleDraweeView searchImage;
        @BindView(R.id.search_text)
        TextView searchText;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
