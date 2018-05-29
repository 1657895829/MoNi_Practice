package com.example.search_shopcart.view;


import com.example.search_shopcart.bean.SelectCartBean;

public interface SelectCartViewListener {
    public void success(SelectCartBean selectCartBean);
    public void failure();

}
