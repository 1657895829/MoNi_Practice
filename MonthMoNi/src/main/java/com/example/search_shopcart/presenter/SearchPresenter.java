package com.example.search_shopcart.presenter;

import android.text.TextUtils;
import com.example.search_shopcart.base.BasePresenter;
import com.example.search_shopcart.bean.SearchBean;
import com.example.search_shopcart.model.SearchModel;
import com.example.search_shopcart.model.SearchModelCallBack;
import com.example.search_shopcart.view.SearchViewListener;

/**
 * Created   by   Dewey .
 */

public class SearchPresenter extends BasePresenter<SearchViewListener> {
    private SearchModel searchModel;

    public SearchPresenter() {
        this.searchModel = new SearchModel();
    }

    //搜索数据的方法
    public void getData(String edit) {
        //判断输入为空情况下
        if (TextUtils.isEmpty(edit) || edit.length() == 0) {
            view.empty();
            return;
        }
        //判断输入错误的情况
        if (!edit.equals("笔记本") && !edit.equals("手机")) {
            view.falseEdit();
            return;
        }

        searchModel.getEditData(edit, new SearchModelCallBack() {
            @Override
            public void success(SearchBean searchBean) {
                view.success(searchBean);
                System.out.println("搜索presenter搜索数据：" + searchBean.toString());
            }

            @Override
            public void failure(Exception e) {
                view.failure(e);
            }
        });

    }

}
