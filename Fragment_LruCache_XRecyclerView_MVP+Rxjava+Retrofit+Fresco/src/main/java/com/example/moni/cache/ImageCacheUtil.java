package com.example.moni.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import com.jakewharton.DiskLruCache;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;

/**
 * 缓存工具类
 *
 * 加载图片时，
 * 1.先从内存中获取，如果得到，则进行显示，没有则从sd卡缓存中查找
 * 2.从sd卡缓存中查找，如果找到，则进行显示，并再次存放到内存缓存区，如果没有找到，则从网络上获取
 * 3.从网络上下载图片，下载完成后，将图片缓存在内存和sd卡上
 */
public class ImageCacheUtil {

    //内存缓存所有的图片，在程序内存达到预设值时会将最近最少使用的图片移除掉
    private LruCache<String,Bitmap> mLruCache;

    //sd卡缓存
    private DiskLruCache mDiskLruCache;



    public ImageCacheUtil(Context context) {

        //得到程序的内存
        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        int cacheSize=maxMemory/8;

        //对Lrucache进行初使化,需要设置缓存区的大小，一般是程序运行内存的 1/4或1/8
        mLruCache=new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();//自定义bitmap数据大小的计算方式
            }
        };

        //获取图片缓存路径
        File cacheFile=getDiskCacheDir(context,"mypics");
        if(!cacheFile.exists()){
            cacheFile.mkdirs();//创建这个路径
        }


        /**
         *对DiskLurCache进行初使化 使用open方法 需要四个参数
         * 第一个参数：缓存的路径
         * 第二个参数：应用程序的版本
         * 第三个参数：指定同一个key可以缓存多少个文件 基本上都是设置成1
         * 第四个参数：指定缓存空间大小
         */
        try {
            mDiskLruCache= DiskLruCache.open(cacheFile,getAppVersion(context),1,20*1024*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过key值得到缓存的图片
     * @param key 图片的url地址
     * @return Bitmap 或 null
     */
    public  Bitmap getPicFromMemory(String key){

        Bitmap bitmap=null;
        try {
            //进行md5加密
            String imgKey=encode(key);
            //通过key获取图片
            bitmap= mLruCache.get(imgKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 保存图片到内存缓存
     * @param key 图片的url
     * @param bitmap 图片
     */
    public void savePicToMemory(String key,Bitmap bitmap){
        try {
            String imgKey=encode(key);
            mLruCache.put(imgKey,bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 通过key从sd卡缓存目录中查找图片
     * @param key
     * @return
     */
    public Bitmap getPicFromSd(String key){
        try {
            //1.对key进行加密
            String imgKey=encode(key);
            //2.得到Snapshot对象
            DiskLruCache.Snapshot snapshot=mDiskLruCache.get(imgKey);
            //3.进行判断
            if(snapshot!=null){
                //4.得到输入流对象
                InputStream inputStream=snapshot.getInputStream(0);
                Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                if(bitmap!=null){
                    //保存至内存缓存
                    savePicToMemory(key,bitmap);
                }
                return  bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 保存图片到sd卡缓存
     * @param key
     * @param bitmap
     */
    public  void savePicToSd(String key,Bitmap bitmap){
        try {
            //1.进行加密
            String imgKey=encode(key);
            //2.通过key得到editor对象
            DiskLruCache.Editor editor=mDiskLruCache.edit(imgKey);
            if(editor!=null){
                //3.得到输出流
                OutputStream outputStream=editor.newOutputStream(0);
                //4.将bitmap对象存放到输出流中
                if(bitmap.compress(CompressFormat.JPEG,80,outputStream)){
                    editor.commit();
                }else {
                    editor.abort();
                }
                //刷新
                mDiskLruCache.flush();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 加载显示图片
     * @param imgUrl
     * @param imgview
     */
    public void loadPic(String imgUrl,ImageView imgview){
        //1.从内存缓存中获取
        Bitmap memoryBitmap=getPicFromMemory(imgUrl);
        if(memoryBitmap!=null){
            Log.d("zzz","从内存缓存获取LruCache");
            imgview.setImageBitmap(memoryBitmap);
            return;
        }

        //2.从sd缓存中获取
        Bitmap sdBitmap=getPicFromSd(imgUrl);
        if(sdBitmap!=null){
            Log.d("zzz","从sd缓存获取DiskLruCache");
            imgview.setImageBitmap(sdBitmap);
            return;

        }

        //3.从网络中获取
        loadPicFromNet(imgUrl,imgview);

    }

    /**
     * 从网络上面获取图片
     * @param imgUrl 图片网址
     * @param imgview 图片要显示的控件
     */
    public  void loadPicFromNet(final String imgUrl, final ImageView imgview){
        AsyncTask<Void,Void,Bitmap> myTask=new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                Bitmap bitmap=null;
                try {
                    URL url=new URL(imgUrl);
                    HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);

                    if(connection.getResponseCode()==200){
                        InputStream inputStream=connection.getInputStream();
                        bitmap= BitmapFactory.decodeStream(inputStream);
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                Log.d("zzz","从网络获取");
                //存放到LruCache
                savePicToMemory(imgUrl,bitmap);
                //存放到DiskCache
                savePicToSd(imgUrl,bitmap);
                //设置图片
                imgview.setImageBitmap(bitmap);
            }
        };
        myTask.execute();

    }


    /**
     * 把一个字符串以md5的放肆加密之后返回...因为url路径里面可能存在一些不可用的特殊字符,
     * 所以使用这种方式处理一下
     * @param string
     * @return
     * @throws Exception
     */
    private String encode(String string) throws Exception {
        byte[] hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 获取sd卡的缓存目录
     * @param context 上下文
     * @param cacheName 缓存文件夹的名子
     * @return
     */
    public File getDiskCacheDir(Context context,String cacheName){
        String cachefile="";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //得到 sdcard/Android/data/package.../cache
            cachefile=context.getExternalCacheDir().getPath();

        }else{//sd卡不可用的情况下
            //得到 data/data/<application package>/cache
            cachefile=context.getCacheDir().getPath();

        }
        return new File(cachefile+File.separator+cacheName);
    }

    /**
     * 获取当前应用程序的版本号
     * @param context 上下文
     * @return
     */
    public int getAppVersion(Context context){
        try {
            PackageInfo packageInfo=context.getPackageManager().getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

}
