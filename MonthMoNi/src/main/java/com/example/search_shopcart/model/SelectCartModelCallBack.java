package com.example.search_shopcart.model;


import com.example.search_shopcart.bean.SelectCartBean;

public interface SelectCartModelCallBack {
    public void success(SelectCartBean selectCartBean);
    public void failure();

}
