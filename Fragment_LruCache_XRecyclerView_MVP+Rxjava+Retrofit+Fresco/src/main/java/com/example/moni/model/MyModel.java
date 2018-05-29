package com.example.moni.model;

import com.example.moni.bean.NewsBean;
import com.example.moni.utils.APIGet_Post_Factory;
import com.example.moni.utils.AbstractObserver;
import com.example.moni.view.MyView;

/**
 * Created   by   Dewey .
 */

public class MyModel {
    //请求数据的方法
    public void getData(final MyView myView){
        APIGet_Post_Factory.getInstance().get("/mobile/?key=71e58b5b2f930eaf1f937407acde08fe&num=10", new AbstractObserver<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean) {
                myView.onSuccess(newsBean);
            }

            @Override
            public void onFailure(Exception e) {
                myView.onFailure();
            }
        });
    }
}
