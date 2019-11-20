package com.bawei.xinwdsc.model;


import com.bawei.xinwdsc.app.ApiService;
import com.bawei.xinwdsc.bean.HeadReseponse;
import com.bawei.xinwdsc.bean.UserBean;
import com.bawei.xinwdsc.counter.Contrortr;
import com.bawei.xinwdsc.util.RotrefitUtil;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 10:37
 */
public class HomeModel implements Contrortr.IModel {
    @Override
    public void getZhan(String path, final ZhanCallBack zhanCallBack) {
        Retrofit retrofit = RotrefitUtil.getInstance().getRetrofit();
        ApiService service = retrofit.create(ApiService.class);
        Observable<UserBean> goods = service.getGoods();
        goods.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        zhanCallBack.ZhanSuccess(userBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        zhanCallBack.ZhanError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
