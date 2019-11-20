package com.bawei.xinwdsc.counter;

import com.bawei.xinwdsc.bean.HeadReseponse;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 20:03
 */
public interface HardCount {
    interface HeadIView{
        void MySuccess(HeadReseponse headRespone);
        void MyErorr(String mag);
    }
    interface HeadIModel{
        void getHead(String file,HeadCallBack headCallBack);

        interface HeadCallBack{
            void MySuccess(HeadReseponse headRespone);
            void MyErorr(String mag);
        }
    }
    interface HeadIPresenter{
        void getHead(String file);
        void attchView(HardCount.HeadIView iView);
        void deacth();
    }
}
