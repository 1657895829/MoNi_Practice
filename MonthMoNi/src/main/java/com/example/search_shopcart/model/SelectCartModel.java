package com.example.search_shopcart.model;

import com.example.search_shopcart.bean.SelectCartBean;
import com.example.search_shopcart.utils.APIGet_Post_Factory;
import com.example.search_shopcart.utils.APIServiceInterface;
import com.example.search_shopcart.utils.AbstractObserver;
import com.example.search_shopcart.utils.RetrofitUtils;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 查询购物车model层
 */

public class SelectCartModel {

    public void getData(final SelectCartModelCallBack selectCartModelCallBack) {
        //https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
        // https://www.zhaoapi.cn/product/addCart
        //"uid": 93
        // "token": "2FC3EF31EA25696D2715A971ADE38DE1"
        //使用Retrofit结合RxJava，okhttp封装类的单例模式,集合传参
        APIServiceInterface request = RetrofitUtils.getInstance();

        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","93");
        map.put("token","2FC3EF31EA25696D2715A971ADE38DE1");

        request.selectCart(map).enqueue(new Callback<SelectCartBean>() {
            @Override
            public void onResponse(Call<SelectCartBean> call, Response<SelectCartBean> response) {
                SelectCartBean selectCartBean = response.body();
                selectCartModelCallBack.success(selectCartBean);
            }

            @Override
            public void onFailure(Call<SelectCartBean> call, Throwable t) {
                selectCartModelCallBack.failure();
            }
        });


        APIGet_Post_Factory.getInstance().get("/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1", map, new AbstractObserver<SelectCartBean>() {
            @Override
            public void onSuccess(SelectCartBean selectCartBean) {
                selectCartModelCallBack.success(selectCartBean);
            }

            @Override
            public void onFailure(Exception e) {
                selectCartModelCallBack.failure();
            }
        });
    }
}
