package com.example.search_shopcart.presenter;

import com.example.search_shopcart.base.BasePresenter;
import com.example.search_shopcart.bean.AddCartBean;
import com.example.search_shopcart.model.AddCartModel;
import com.example.search_shopcart.model.AddCartModelCallBack;
import com.example.search_shopcart.view.AddCartViewListener;

/**
 * 加入购物车 p 层
 */

public class AddCartPresenter extends BasePresenter<AddCartViewListener> {
    private AddCartModel model;

    public AddCartPresenter() {
        this.model = new AddCartModel();
    }

    public void getData(String pid){
        model.getData(pid, new AddCartModelCallBack() {
            @Override
            public void success(AddCartBean addCartBean) {
                view.success(addCartBean);
            }

            @Override
            public void failure(Exception e) {
                view.failure(e);
            }
        });
    }
}
