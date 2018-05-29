package com.example.zhoukao4.ui.contract;

import com.example.zhoukao4.bean.NewsBean;
import com.example.zhoukao4.ui.base.BaseContract;

public interface NewsContract {
    interface View extends BaseContract.BaseView{
        void showNews(NewsBean newsBean);
    }
    interface Presenter extends BaseContract.BasePresenter<View>{
        void getNews(String page);
    }
}
