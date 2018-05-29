package com.example.moni.presenter;

import com.example.moni.base.BasePresenter;
import com.example.moni.bean.NewsBean;
import com.example.moni.model.MyModel;
import com.example.moni.view.MyView;

/**
 * Created   by   Dewey .
 */
public class MyPresenter extends BasePresenter<MyView> {
    private MyModel myModel;

    public MyPresenter() {
        this.myModel = new MyModel();
    }

    //请求数据的方法
    public void get(){
        myModel.getData(new MyView() {
            @Override
            public void onSuccess(NewsBean newsBean) {
                if (view != null){
                    view.onSuccess(newsBean);
                }
            }

            @Override
            public void onFailure() {
                if (view != null){
                    view.onFailure();
                }
            }
        });
    }

    //取消绑定，防止内存泄露
    @Override
    public void detach(){
        view = null;
    }
}
