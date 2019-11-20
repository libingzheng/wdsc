package com.bawei.xinwdsc.presenter;

import com.bawei.xinwdsc.bean.HeadReseponse;
import com.bawei.xinwdsc.bean.UserBean;
import com.bawei.xinwdsc.counter.Contrortr;
import com.bawei.xinwdsc.model.HomeModel;

import java.lang.ref.WeakReference;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 10:37
 */
public class HomePresenter implements Contrortr.IPresenter {

    private HomeModel model;
    private WeakReference<Contrortr.IView> iViewWeakReference;

    @Override
    public void attachView(Contrortr.IView iView) {
        model = new HomeModel();
        iViewWeakReference = new WeakReference<>(iView);
    }

    public Contrortr.IView getView(){
        return iViewWeakReference.get();
    }
    @Override
    public void detachView() {
        if (iViewWeakReference!=null){
            iViewWeakReference.clear();
            iViewWeakReference=null;
        }
    }

    @Override
    public void getZhan(String path) {
        model.getZhan(path, new Contrortr.IModel.ZhanCallBack() {
            @Override
            public void ZhanSuccess(UserBean userBean) {
                getView().ZhanSuccess(userBean);
            }

            @Override
            public void ZhanError(String e) {
                getView().ZhanError(e);
            }
        });
    }
}
