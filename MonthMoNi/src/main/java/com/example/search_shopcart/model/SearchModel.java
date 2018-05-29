package com.example.search_shopcart.model;

import com.example.search_shopcart.bean.SearchBean;
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
 * Created   by   Dewey .
 *  搜索框model层实现类
 *  https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
 */

public class SearchModel {
    //搜索数据方法
    public void getEditData(String edit, final SearchModelCallBack callBack){
        //使用Retrofit结合RxJava，okhttp封装类的单例模式,集合传参
        APIServiceInterface request = RetrofitUtils.getInstance();
        final Map<String,String>  map = new HashMap<>();
        map.put("source","android");
        map.put("keywords",edit);
        map.put("page","1");

        request.getEdit(map).enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                SearchBean searchBean = response.body();
                callBack.success(searchBean);
                System.out.println("搜索model搜索数据："+searchBean.toString()+map.toString());
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {
                callBack.failure(new Exception(""));
            }
        });


        APIGet_Post_Factory.getInstance().get("/product/searchProducts?keywords=笔记本&page=1", map, new AbstractObserver<SearchBean>() {
            @Override
            public void onSuccess(SearchBean searchBean) {
                callBack.success(searchBean);
            }

            @Override
            public void onFailure(Exception e) {
                callBack.failure(new Exception(""));
            }
        });
    }
}
