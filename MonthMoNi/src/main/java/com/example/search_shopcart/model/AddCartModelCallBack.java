package com.example.search_shopcart.model;


import com.example.search_shopcart.bean.AddCartBean;

/**
 * 加入购物车model层接口
 */
public interface AddCartModelCallBack {
    public void success(AddCartBean addCartBean);
    public void failure(Exception e);
}
