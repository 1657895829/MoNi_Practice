package com.example.search_shopcart.model;

import com.example.search_shopcart.bean.DeleteBean;

public interface DeleteCartModelCallBack {
    public void success(DeleteBean deleteBean);
    public void failure();
}
