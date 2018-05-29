package com.example.search_shopcart.presenter;

import com.example.search_shopcart.bean.DeleteBean;
import com.example.search_shopcart.model.DeleteCartModel;
import com.example.search_shopcart.model.DeleteCartModelCallBack;
import com.example.search_shopcart.view.DeleteCartListener;

/**
 * 删除购物车p层
 */
public class DeleteCartPresenter {
    private DeleteCartModel deleteCartModel;
    private DeleteCartListener deleteCartListener;
    public DeleteCartPresenter(DeleteCartListener deleteCartListener) {
        this.deleteCartListener = deleteCartListener;
        this.deleteCartModel = new DeleteCartModel();
    }

    public void delete(String pid) {
        deleteCartModel.delete(pid, new DeleteCartModelCallBack() {
            @Override
            public void success(DeleteBean deleteBean) {
                deleteCartListener.success(deleteBean);
            }

            @Override
            public void failure() {
                deleteCartListener.failure();
            }
        });
    }

    //解除绑定，防止内存泄露
    public void detach(){
        this.deleteCartListener = null;
    }
}
