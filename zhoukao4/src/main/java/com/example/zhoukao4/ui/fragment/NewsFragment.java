package com.example.zhoukao4.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.zhoukao4.R;
import com.example.zhoukao4.bean.NewsBean;
import com.example.zhoukao4.component.DaggerHttpComponent;
import com.example.zhoukao4.ui.adapter.XrvAdapter;
import com.example.zhoukao4.ui.base.BaseFragment;
import com.example.zhoukao4.ui.contract.NewsContract;
import com.example.zhoukao4.ui.presenter.NewsPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View{

    private XRecyclerView xrv_news;
    private List<NewsBean.NewslistBean> list=new ArrayList<>();
    private boolean isrefresh=false;
    private int page=1;
    private XrvAdapter xrvAdapter;

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .Inject(this);
    }

    @Override
    public int getContentLayoout() {
        return R.layout.frag_news;
    }

    @Override
    public void initView(View view) {
        xrv_news =view.findViewById(R.id.xrv_news);
        xrv_news.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrvAdapter = new XrvAdapter(list, getActivity());
        xrv_news.setAdapter(xrvAdapter);

        mPresenter.getNews(page+"");

        //设置滑动监听
        xrv_news.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isrefresh=true;
                page=1;
                mPresenter.getNews(page+"");
            }

            @Override
            public void onLoadMore() {
                isrefresh=false;
                page++;
                mPresenter.getNews(page+"");
            }
        });
    }

    @Override
    public void showNews(NewsBean newsBean) {
        List<NewsBean.NewslistBean> newslist = newsBean.getNewslist();
        if (isrefresh){
            xrvAdapter.refresh(newslist);
            xrv_news.refreshComplete();
        }else {
            xrvAdapter.loadMore(newslist);
            xrv_news.loadMoreComplete();
        }
    }
}
