package com.example.search_shopcart.app;

import android.app.Application;
import com.example.search_shopcart.dao.DaoMaster;
import com.example.search_shopcart.dao.DaoSession;
import com.example.search_shopcart.utils.ImageLoaderUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import org.greenrobot.greendao.database.Database;

/**
 * Fresco  GreenDao  的初始化全局配置类
 */
public class MyApplication extends Application {
    //设置session为公用
    public static DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();
        //配置imageLoader
        ImageLoaderUtil.init(this);

        //Fresco
        Fresco.initialize(this);

        //初始化数据库
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "rikao");

        //获取数据库
        Database db = helper.getWritableDb();

        //操作数据库,等于打开数据库
        session = new DaoMaster(db).newSession();
    }
}
