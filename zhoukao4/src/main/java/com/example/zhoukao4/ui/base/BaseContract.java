package com.example.zhoukao4.ui.base;

public interface BaseContract {

    interface BaseView{
        void showLoading();
        void dismissLoading();
    }

    interface BasePresenter<T extends BaseView>{
        void accachView(T view);
        void detach();
    }

}
