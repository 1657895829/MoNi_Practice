package com.example.search_shopcart.view;


import com.example.search_shopcart.bean.DeleteBean;

public interface DeleteCartListener {
    public void success(DeleteBean deleteBean);
    public void failure();
}
