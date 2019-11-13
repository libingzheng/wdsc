package com.bawei.ri1113.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/13
 * Time: 8:55
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    protected P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId=initLayoutId();
        setContentView(layoutId);
        presenter=initPresenter();
        presenter.attachView(this);
        initView();
    }

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int initLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
