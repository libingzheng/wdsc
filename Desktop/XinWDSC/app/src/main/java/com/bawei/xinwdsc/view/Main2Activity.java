package com.bawei.xinwdsc.view;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bawei.xinwdsc.R;
import com.bawei.xinwdsc.adapter.FragmAdapter;
import com.bawei.xinwdsc.base.BaseActivity;
import com.bawei.xinwdsc.fragment.Fragm;
import com.bawei.xinwdsc.fragment.Fragm1;
import com.bawei.xinwdsc.fragment.Fragm2;
import com.bawei.xinwdsc.fragment.Fragm3;
import com.bawei.xinwdsc.fragment.Fragm4;

import java.util.ArrayList;

public class Main2Activity extends BaseActivity{

    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment>fragments=new ArrayList<>();
    private ArrayList<String>list=new ArrayList<>();
    @Override
    protected int initLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
    }

    @Override
    protected void initData() {
        fragments.add(new Fragm());
        fragments.add(new Fragm1());
        fragments.add(new Fragm2());
        fragments.add(new Fragm3());
        fragments.add(new Fragm4());

        list.add("首页");
        list.add("搜索");
        list.add("购物车");
        list.add("订单");
        list.add("我的");

        FragmAdapter adapter = new FragmAdapter(getSupportFragmentManager(), fragments, list);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }
}
