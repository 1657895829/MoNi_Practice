package com.example.search_shopcart.model;

import com.example.search_shopcart.bean.DeleteBean;
import com.example.search_shopcart.utils.APIGet_Post_Factory;
import com.example.search_shopcart.utils.AbstractObserver;
import java.util.HashMap;
import java.util.Map;

/**
 * 删除购物车model层
 */

public class DeleteCartModel {

    public void delete(String pid, final DeleteCartModelCallBack deleteCartModelCallBack) {
        //https://www.zhaoapi.cn/product/deleteCart?uid=1650&pid=58

        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","1650");
        map.put("pid",pid);

        APIGet_Post_Factory.getInstance().get("/product/deleteCart?uid=1650&pid=58", map, new AbstractObserver<DeleteBean>() {
            @Override
            public void onSuccess(DeleteBean deleteBean) {
                deleteCartModelCallBack.success(deleteBean);
            }

            @Override
            public void onFailure(Exception e) {
                deleteCartModelCallBack.failure();
            }
        });
    }

}
