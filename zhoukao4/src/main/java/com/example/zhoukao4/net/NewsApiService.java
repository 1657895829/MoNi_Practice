package com.example.zhoukao4.net;

import com.example.zhoukao4.bean.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("mobile/?key=71e58b5b2f930eaf1f937407acde08fe&num=10")
    Observable<NewsBean> getNews(@Query("page") String page);

}
