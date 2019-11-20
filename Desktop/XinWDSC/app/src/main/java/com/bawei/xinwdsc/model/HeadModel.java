package com.bawei.xinwdsc.model;

import android.util.Log;

import com.bawei.xinwdsc.app.ApiService;
import com.bawei.xinwdsc.bean.HeadReseponse;
import com.bawei.xinwdsc.counter.HardCount;
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
 * Time: 20:07
 */
public class HeadModel implements HardCount.HeadIModel {
    @Override
    public void getHead(String file, final HeadCallBack headCallBack) {
        File file1 = new File(file);
        if (!!file1.exists()){
            Log.d("tag", "找不到文件");
            return;
        }
        RequestBody requestBody=null;
        MediaType mediaType = MediaType.parse("multipart/form-data");
        requestBody=RequestBody.create(mediaType,file1);
        MultipartBody multipartBody=new MultipartBody.Builder()
                .addFormDataPart("image",file1.getName(),requestBody)
                .build();
        Retrofit retrofit = RotrefitUtil.getInstance().getRetrofit();
        ApiService service = retrofit.create(ApiService.class);
        Observable<HeadReseponse> observable = service.upHead(multipartBody);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HeadReseponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HeadReseponse headReseponse) {
                        Log.d("tag", headReseponse.getMessage());
                        headCallBack.MySuccess(headReseponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        headCallBack.MyErorr(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
