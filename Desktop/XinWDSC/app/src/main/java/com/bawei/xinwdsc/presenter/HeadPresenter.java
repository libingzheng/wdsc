package com.bawei.xinwdsc.presenter;

import com.bawei.xinwdsc.bean.HeadReseponse;
import com.bawei.xinwdsc.counter.HardCount;
import com.bawei.xinwdsc.model.HeadModel;

import java.lang.ref.WeakReference;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 20:14
 */
public class HeadPresenter implements HardCount.HeadIPresenter {

    private HeadModel model;
    private WeakReference<HardCount.HeadIView> headIViewWeakReference;

    @Override
    public void getHead(String file) {
        model.getHead(file, new HardCount.HeadIModel.HeadCallBack() {
            @Override
            public void MySuccess(HeadReseponse headRespone) {
                getView().MySuccess(headRespone);
            }

            @Override
            public void MyErorr(String mag) {
                getView().MyErorr(mag);
            }
        });
    }

    @Override
    public void attchView(HardCount.HeadIView iView) {
        model = new HeadModel();
        headIViewWeakReference = new WeakReference<>(iView);
    }

    @Override
    public void deacth() {
        if (headIViewWeakReference!=null){
            headIViewWeakReference.clear();
            headIViewWeakReference=null;
        }
    }
    public HardCount.HeadIView getView(){
        return headIViewWeakReference.get();
    }
}
