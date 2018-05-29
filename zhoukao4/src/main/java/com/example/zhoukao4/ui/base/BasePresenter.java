package com.example.zhoukao4.ui.base;

public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;

    @Override
    public void accachView(T view) {
        this.mView=view;
    }

    @Override
    public void detach() {
        if (mView!=null){
            mView=null;
        }
    }
}
