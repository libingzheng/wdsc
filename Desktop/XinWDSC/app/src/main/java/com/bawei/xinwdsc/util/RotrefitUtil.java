package com.bawei.xinwdsc.util;

import com.bawei.xinwdsc.app.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 10:25
 */
public class RotrefitUtil {
    private Retrofit retrofit;
    private static RotrefitUtil instabce=null;
    private RotrefitUtil(){
        retrofit=new Retrofit.Builder()
                .baseUrl(Api.path)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttp.getInstance().getOkHttpClient())
                .build();
    }
    public static RotrefitUtil getInstance(){
        if (instabce==null){
            instabce=new RotrefitUtil();
        }
        return instabce;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
