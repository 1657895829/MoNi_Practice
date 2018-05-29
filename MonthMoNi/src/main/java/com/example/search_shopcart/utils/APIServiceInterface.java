package com.example.search_shopcart.utils;

import com.example.search_shopcart.bean.AddCartBean;
import com.example.search_shopcart.bean.DeleteBean;
import com.example.search_shopcart.bean.SearchBean;
import com.example.search_shopcart.bean.SelectCartBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 封装的网络数据接口请求类
 */

public interface APIServiceInterface {
    //搜索 笔记本 手机的接口,集合传参
    //https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
    @GET("/product/searchProducts")
    Call<SearchBean> getEdit(@QueryMap Map<String,String> map);

    /*
    * 加入购物车
    *  https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
    *  uid": 1650
    *  "token": "2FC3EF31EA25696D2715A971ADE38DE1"
    */
    @GET("product/addCart")
    Call<AddCartBean> addCart(@QueryMap Map<String,String> map);

    //https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
    //uid": 1650,
    // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
    @GET("product/getCarts")
    Call<SelectCartBean> selectCart(@QueryMap Map<String,String> map);

    //删除
    //https://www.zhaoapi.cn/product/deleteCart?uid=1650&pid=58
    @GET("/product/deleteCart")
    Call<DeleteBean> deleteCart(@QueryMap Map<String,String> map);


    //get请求方式，传入网址url，Map集合传参，使用Observer被观察者订阅执行
    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, String> map);

    //get请求方式，传入网址url，不传参，直接使用Observer被观察者订阅执行
    @GET
    Observable<String> get(@Url String url);

    //post请求方式，传入网址url，Map集合传参，使用Observer被观察者订阅执行
    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, String> map);

}
