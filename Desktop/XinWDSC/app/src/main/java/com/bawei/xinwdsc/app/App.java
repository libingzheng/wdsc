package com.bawei.xinwdsc.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 10:29
 */
public class App extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
