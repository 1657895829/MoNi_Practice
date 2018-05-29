package com.example.moni.view;

import com.example.moni.bean.NewsBean;

/**
 * Created   by   Dewey .
 */

public interface MyView {
    void onSuccess(NewsBean newsBean);
    void onFailure();
}
