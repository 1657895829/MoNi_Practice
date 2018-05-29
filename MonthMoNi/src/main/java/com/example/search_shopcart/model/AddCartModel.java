package com.example.search_shopcart.model;

import com.example.search_shopcart.bean.AddCartBean;
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
 * 加入购物车的model层
 */
public class AddCartModel {
    /**
     * https://www.zhaoapi.cn/product/addCart
     * "uid": 1650
     * "token": "2FC3EF31EA25696D2715A971ADE38DE1"
     * "pid":57
     * @param pid
     * @param callBack
     */
    public void getData(String pid, final AddCartModelCallBack callBack){
        //使用Retrofit结合RxJava，okhttp封装类的单例模式,集合传参
        APIServiceInterface request = RetrofitUtils.getInstance();
        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","1650");
        map.put("token","2FC3EF31EA25696D2715A971ADE38DE1");
        map.put("pid",pid);

        request.addCart(map).enqueue(new Callback<AddCartBean>() {
            @Override
            public void onResponse(Call<AddCartBean> call, Response<AddCartBean> response) {
                AddCartBean addCartBean = response.body();
                callBack.success(addCartBean);
            }

            @Override
            public void onFailure(Call<AddCartBean> call, Throwable t) {
                callBack.failure(new Exception());
            }
        });

        APIGet_Post_Factory.getInstance().get("/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1", map, new AbstractObserver<AddCartBean>() {
            @Override
            public void onSuccess(AddCartBean addCartBean) {
                callBack.success(addCartBean);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.failure(new Exception(""));
            }
        });
    }

}
