package com.example.zhoukao4.net;

import com.example.zhoukao4.bean.NewsBean;

import io.reactivex.Observable;

public class NewsApi {
    private NewsApiService newsApiService;
    private static NewsApi newsApi;

    private NewsApi(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    public static NewsApi getNewsApi(NewsApiService newsApiService){
        if (newsApi==null){
            newsApi=new NewsApi(newsApiService);
        }
        return newsApi;
    }

    public Observable<NewsBean> getNews(String page){
        return newsApiService.getNews(page);
    }
}
