package com.bawei.xinwdsc.counter;

import com.bawei.xinwdsc.bean.UserBean;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 10:36
 */
public interface Contrortr {
    interface IView{
        void ZhanSuccess(UserBean userBean);
        void ZhanError(String e);

    }

    interface IModel{

        void getZhan(String path, ZhanCallBack showCallBack);
        interface ZhanCallBack{
            void ZhanSuccess(UserBean userBean);
            void ZhanError(String e);
        }

    }
    interface IPresenter{
        void attachView(Contrortr.IView iView);
        void detachView();
        void getZhan(String path);
    }
}
