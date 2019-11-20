package com.bawei.xinwdsc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.xinwdsc.R;
import com.bawei.xinwdsc.bean.UserBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 10:48
 */
public class Datapter extends RecyclerView.Adapter {
    private Context context;
    private List<UserBean.ResultBean.RxxpBean.CommodityListBean> commodityList;

    public Datapter(Context context, List<UserBean.ResultBean.RxxpBean.CommodityListBean> commodityList) {
        this.context = context;
        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ViewHolder holder1= (ViewHolder) holder;

        holder1.goodsName.setText(commodityList.get(i).commodityName);
        holder1.goodsPrice.setText(commodityList.get(i).price+"");
        Glide.with(context).load(commodityList.get(i).masterPic).into(holder1.iv);
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;
        private final TextView goodsName;
        private final TextView goodsPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            goodsName = itemView.findViewById(R.id.goodsName);
            goodsPrice = itemView.findViewById(R.id.goodsPrice);
        }
    }
}
