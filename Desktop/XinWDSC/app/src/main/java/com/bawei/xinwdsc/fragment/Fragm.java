package com.bawei.xinwdsc.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bawei.xinwdsc.R;
import com.bawei.xinwdsc.adapter.Datapter;
import com.bawei.xinwdsc.app.Api;
import com.bawei.xinwdsc.base.FragmActivity;
import com.bawei.xinwdsc.bean.UserBean;
import com.bawei.xinwdsc.counter.Contrortr;
import com.bawei.xinwdsc.presenter.HomePresenter;

import java.util.List;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 10:55
 */
public class Fragm extends FragmActivity implements Contrortr.IView {

    private RecyclerView rlv,glv;
    private HomePresenter presenter;

    @Override
    protected int initLayout() {
        return R.layout.fragm;
    }

    @Override
    protected void initView(View view) {
        rlv = view.findViewById(R.id.rlv);
        glv = view.findViewById(R.id.glv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlv.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        glv.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void initData() {
        presenter = new HomePresenter();
        presenter.attachView(this);
        presenter.getZhan(Api.path);
    }

    @Override
    public void ZhanSuccess(UserBean userBean) {
        List<UserBean.ResultBean.RxxpBean.CommodityListBean> commodityListBeans = userBean.result.rxxp.commodityList;
        Datapter datapter = new Datapter(getContext(),commodityListBeans);
        rlv.setAdapter(datapter);
        glv.setAdapter(datapter);
    }

    @Override
    public void ZhanError(String e) {

    }
}
