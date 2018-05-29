package com.example.moni.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.moni.R;
import com.example.moni.adapter.MyListAdapter;
import com.example.moni.base.BaseMvpFragment;
import com.example.moni.bean.NewsBean;
import com.example.moni.presenter.MyPresenter;
import com.example.moni.view.MyView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created   by    Dewey
 * 列表
 */
public class List_Fragment extends BaseMvpFragment<MyView,MyPresenter> implements MyView {
    private List<NewsBean.NewslistBean> list = new ArrayList<>();
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    private Unbinder unbinder;
    private MyListAdapter adapter;
    private Handler handler = new Handler();
    int num = 10;               //页数

    @Override
    public MyPresenter initPresenter() {
        //p与v交互，请求数据
        return new MyPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //请求数据
        p.get(Integer.toString(num));

        //设置布局管理器以及数据适配器,分割线
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xRecyclerView.setLayoutManager(manager);
        xRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new MyListAdapter(getActivity(),list);
        xRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //XRecyclerview的上拉下拉方法
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * 在子线程内完成上拉刷新数据
                         * 对于本接口来说，只要你把p层请求数据的方法再写一遍重新请求数据，数据就会刷新，即使你不传递页数参数
                         */
                        p.get(Integer.toString(num));
                        adapter.notifyDataSetChanged();
                        xRecyclerView.refreshComplete();
                    }
                },888);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         *    在子线程内完成下拉加载更多数据
                         *    对于本接口来说，只要你把p层请求数据的方法再写一遍重新请求数据，数据就会加载，即使你不传递页数参数
                         */
                        p.get(Integer.toString(num++));
                        adapter.notifyDataSetChanged();
                        xRecyclerView.loadMoreComplete();
                    }
                },888);
            }
        });
    }

    @Override
    public void onSuccess(NewsBean newsBean) {
        if (newsBean != null){
            adapter.addData(newsBean.getNewslist());
        }
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        p.detach();
    }
}
