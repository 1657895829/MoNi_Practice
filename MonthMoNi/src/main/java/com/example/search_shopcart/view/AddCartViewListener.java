package com.example.search_shopcart.view;


import com.example.search_shopcart.bean.AddCartBean;

/**
 * 加入购物车 view层
 */
public interface AddCartViewListener {
    public void success(AddCartBean addCartBean);
    public void failure(Exception e);
}
