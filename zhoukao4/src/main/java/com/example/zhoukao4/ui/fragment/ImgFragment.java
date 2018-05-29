package com.example.zhoukao4.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhoukao4.MainActivity;
import com.example.zhoukao4.R;
import com.example.zhoukao4.util.ImageCacheUtil;
import com.example.zhoukao4.util.ImageLruCache;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImgFragment extends Fragment {

    private ImageView img_tu;
    protected static final int CHANGE_UI = 1;
    protected static final int ERROR = 2;
    // 主线程创建消息处理器
    /*private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == CHANGE_UI) {
                Bitmap bitmap = (Bitmap) msg.obj;
                img_tu.setImageBitmap(bitmap);
            } else if (msg.what == ERROR) {
//                Toast.makeText(getActivity(), "显示图片错误", 0).show();
            }
        }
    };*/
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_img, null);
        img_tu = view.findViewById(R.id.img_tu);
        btn = view.findViewById(R.id.btn);
        setListener();
        return view;
    }

    private void setListener() {
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //子线程请求网络,Android4.0以后访问网络不能放在主线程中
                new Thread() {
                    public void run() {
                        // 连接服务器 get 请求 获取图片.
                        try {
                            URL url = new URL("http://img.taopic.com/uploads/allimg/140729/240450-140HZP45790.jpg");       //创建URL对象
                            // 根据url 发送 http的请求.
                            HttpURLConnection conn = (HttpURLConnection) url
                                    .openConnection();
                            // 设置请求的方式
                            conn.setRequestMethod("GET");
                            //设置超时时间
                            conn.setConnectTimeout(5000);
                            // 得到服务器返回的响应码
                            int code = conn.getResponseCode();
                            //请求网络成功后返回码是200
                            if (code == 200) {
                                //获取输入流
                                InputStream is = conn.getInputStream();
                                //将流转换成Bitmap对象
                                Bitmap bitmap = BitmapFactory.decodeStream(is);
                                //iv.setImageBitmap(bitmap);
                                //TODO: 告诉主线程一个消息:帮我更改界面。内容:bitmap
                                Message msg = new Message();
                                msg.what = CHANGE_UI;
                                msg.obj = bitmap;
                                handler.sendMessage(msg);
                            } else {
                                //返回码不是200  请求服务器失败
                                Message msg = new Message();
                                msg.what = ERROR;
                                handler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Message msg = new Message();
                            msg.what = ERROR;
                            handler.sendMessage(msg);
                        }
                    }

                }.start();
            }
        });*/
        final ImageCacheUtil imageCacheUtil = new ImageCacheUtil(getActivity());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCacheUtil.loadPic("http://img.taopic.com/uploads/allimg/140729/240450-140HZP45790.jpg",img_tu);
            }
        });

    }


}
