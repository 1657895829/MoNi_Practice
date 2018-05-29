package com.example.zhoukao4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.zhoukao4.ui.fragment.ImgFragment;
import com.example.zhoukao4.ui.fragment.NewsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 列表
     */
    private TextView tv_biao;
    /**
     * 图片
     */
    private TextView tv_img;
    private NewsFragment newsFragment;
    private ImgFragment imgFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        newsFragment = new NewsFragment();
        imgFragment = new ImgFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,newsFragment).commit();
    }

    private void initView() {
        tv_biao = (TextView) findViewById(R.id.tv_biao);
        tv_biao.setOnClickListener(this);
        tv_img = (TextView) findViewById(R.id.tv_img);
        tv_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_biao:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,newsFragment).commit();
                break;
            case R.id.tv_img:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,imgFragment).commit();
                break;
        }
    }
}
