package com.example.search_shopcart.presenter;

import com.example.search_shopcart.base.BasePresenter;
import com.example.search_shopcart.bean.SelectCartBean;
import com.example.search_shopcart.model.SelectCartModel;
import com.example.search_shopcart.model.SelectCartModelCallBack;
import com.example.search_shopcart.view.SelectCartViewListener;

/**
 * 查询购物车p层
 */

public class SelectCartPresenter extends BasePresenter<SelectCartViewListener>{
    SelectCartModel selectCartModel;

    public SelectCartPresenter() {
        this.selectCartModel = new SelectCartModel();
    }
    //调用model 层的请求数据
    public void getData(){
        selectCartModel.getData(new SelectCartModelCallBack() {
            @Override
            public void success(SelectCartBean selectCartBean) {
                if(view!=null) {
                    view.success(selectCartBean);
                }
            }

            @Override
            public void failure() {
                if(view!=null) {
                    view.failure();
                }
            }
        });
    }


}
