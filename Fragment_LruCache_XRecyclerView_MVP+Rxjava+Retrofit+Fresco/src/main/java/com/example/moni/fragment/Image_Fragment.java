package com.example.moni.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.moni.R;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created   by    Dewey
 * 图片
 */
public class Image_Fragment extends Fragment {
    protected static final int CHANGE_UI = 1;
    protected static final int ERROR = 2;
    @BindView(R.id.frame)
    TextView frame;
    @BindView(R.id.load)
    Button load;
    @BindView(R.id.image)
    ImageView image;
    Unbinder unbinder;

    // 主线程创建消息处理器
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == CHANGE_UI){
                Bitmap bitmap = (Bitmap) msg.obj;
                image.setImageBitmap(bitmap);
            }else if (msg.what == ERROR){
                Toast.makeText(getActivity(),"显示图片错误",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    //点击“加载图片”按钮，开启线程，使用HttpUrlConnection加载图片
    @OnClick(R.id.load)
    public void onViewClicked() {
        //连接路径
        final String path = "http://img.taopic.com/uploads/allimg/140729/240450-140HZP45790.jpg";

        if (TextUtils.isEmpty(path)){
            Toast.makeText(getActivity(),"图片路径不能为空",Toast.LENGTH_SHORT).show();
        }else {
            //子线程请求网络,Android4.0以后访问网络不能放在主线程中
            new Thread(){
                @Override
                public void run() {

                    // 连接服务器 get 请求 获取图片
                    try {
                        //1. 转换路径,创建URL对象
                        URL url = new URL(path);

                        //2. 打开网络连接,根据url 发送 http的请求
                        HttpURLConnection  connection = (HttpURLConnection) url.openConnection();

                        //3. 网络连接设置
                        connection.setRequestMethod("GET");   // 设置请求的方式
                        connection.setConnectTimeout(5000);   //设置超时时间
                        connection.setReadTimeout(5000);

                        //4. 判断获取服务器返回的响应状态码
                        int responseCode = connection.getResponseCode();

                        //请求网络成功后返回码是200
                        if (responseCode == 200){
                            //获取返回内容的字节流
                            InputStream inputStream = connection.getInputStream();

                            //将字节流转换成Bitmap对象
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                            //告诉主线程一个消息:帮我更改界面。内容:bitmap
                            Message message = new Message();
                            message.what = CHANGE_UI;
                            message.obj = bitmap;
                            handler.sendMessage(message);
                        }else {
                            //返回码不是200  请求服务器失败
                            Message message = new Message();
                            message.what = ERROR;
                            handler.sendMessage(message);
                        }

                    } catch (java.io.IOException e) {
                        e.printStackTrace();

                        //发生异常，就报错
                        Message msg = new Message();
                        msg.what = ERROR;
                        handler.sendMessage(msg);
                    }
                }
            }.start();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
