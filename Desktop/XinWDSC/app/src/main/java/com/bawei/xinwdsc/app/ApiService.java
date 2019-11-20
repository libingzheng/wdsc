package com.bawei.xinwdsc.app;

import com.bawei.xinwdsc.Gloabl;
import com.bawei.xinwdsc.bean.HeadReseponse;
import com.bawei.xinwdsc.bean.UserBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Android Studio.
 * User: 李秉正
 * Date: 2019/11/14
 * Time: 10:27
 */
public interface ApiService {
    @GET("commodity/v1/commodityList")
    Observable<UserBean> getGoods();

    @POST(Gloabl.URL_UP_HEAD)
    @Multipart
    Observable<HeadReseponse> upHead(@Part("image") MultipartBody body);
}
