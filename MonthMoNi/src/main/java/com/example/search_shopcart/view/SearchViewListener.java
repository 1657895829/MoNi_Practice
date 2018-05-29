package com.example.search_shopcart.view;

import com.example.search_shopcart.bean.SearchBean;

/**
 * Created   by   Dewey .
 * 搜索数据view层
 */

public interface SearchViewListener {
    void success(SearchBean searchBean);
    void failure(Exception e);

    void empty();            //判断是否为空
    void falseEdit();        //输入错误
}
