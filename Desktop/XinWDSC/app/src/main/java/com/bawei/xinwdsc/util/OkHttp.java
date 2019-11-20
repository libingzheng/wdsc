package com.bawei.xinwdsc.util;

import android.os.Handler;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 10:00
 */
public class OkHttp {
    private static OkHttp instance=new OkHttp();
    private OkHttpClient okHttpClient;
    private Handler handler=new Handler();
    private OkHttp(){
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        if (message.startsWith("{")||message.startsWith("[")){
                            Logger.json(message);
                        }else {
                            Logger.d(message);
                        }
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    }
    public static OkHttp getInstance(){
        return instance;
    }
    public void doGet(String path,final DataCallBack callBack){
        Request.Builder builder=new Request.Builder();
        builder.url(path);
        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, IOException e) {
                final String message = e.getMessage();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailer(message);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                final String json = body.string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailer(json);
                    }
                });
            }
        });
    }
    public void doPost(String path, Map<String,String> params,final DataCallBack callBack){
        Request.Builder builder = new Request.Builder();
        builder.url(path);
        FormBody.Builder from = new FormBody.Builder();
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        for (Map.Entry<String,String> entry:entrySet){
            String key = entry.getKey();
            String value = entry.getValue();
            from.add(key,value);
        }
        FormBody body = from.build();
        builder.post(body);

        Request request = builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String message = e.getMessage();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailer(message);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                final String json = responseBody.string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(json);
                    }
                });
            }
        });
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    interface DataCallBack{
        void onSuccess(String data);
        void onFailer(String msg);
    }
}
