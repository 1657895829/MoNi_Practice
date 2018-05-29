package com.example.zhoukao4.ui.presenter;

import com.example.zhoukao4.bean.NewsBean;
import com.example.zhoukao4.net.NewsApi;
import com.example.zhoukao4.ui.base.BasePresenter;
import com.example.zhoukao4.ui.contract.NewsContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {

    private NewsApi newsApi;
    @Inject
    public NewsPresenter(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    @Override
    public void getNews(String page) {
        newsApi.getNews(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<NewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        if (mView!=null){
                            mView.showNews(newsBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
