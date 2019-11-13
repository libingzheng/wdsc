package com.bawei.ri1113.base;

import java.lang.ref.WeakReference;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/13
 * Time: 8:51
 */
public abstract class BasePresenter<V extends BaseView> {

    private WeakReference<V> weakReference;

    public BasePresenter(){
        initModel();
    }

    public void attachView(V v){
        weakReference = new WeakReference<>(v);
    }
    public void detachView(){
        if (weakReference!=null){
            weakReference.clear();
            weakReference=null;
        }
    }
    protected abstract void initModel();
    public V getView(){
        V v = weakReference.get();
        return v;
    }
}
