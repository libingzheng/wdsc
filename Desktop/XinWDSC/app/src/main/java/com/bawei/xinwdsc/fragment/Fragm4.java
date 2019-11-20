package com.bawei.xinwdsc.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bawei.xinwdsc.R;
import com.bawei.xinwdsc.app.ApiService;
import com.bawei.xinwdsc.base.FragmActivity;
import com.bawei.xinwdsc.bean.HeadReseponse;
import com.bawei.xinwdsc.counter.HardCount;
import com.bawei.xinwdsc.presenter.HeadPresenter;
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
 * Time: 10:55
 */
public class Fragm4 extends FragmActivity implements HardCount.HeadIView {

    private TextView sctx;
    private HeadPresenter presenter;

    @Override
    protected void initView(View view) {
        sctx = view.findViewById(R.id.sctx);
        sctx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void initData() {
        presenter = new HeadPresenter();
        presenter.attchView(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&data!=null){
            Uri uri = data.getData();

            try{
                String path;
                Cursor cursor = getActivity().getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if (cursor==null){
                    path=uri.getPath();
                } else {
                    cursor.moveToFirst();
                    int index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    path=cursor.getString(index);
                    cursor.close();
                }
                File file = new File(path);
                if (!file.exists()){
                    Log.d("tag",path);
                    Log.d("tag", "找不到文件");
                    return;
                }
                RequestBody requestBody=null;
                MediaType mediaType = MediaType.parse("multipart/form-data");
                requestBody=RequestBody.create(mediaType,file);
                MultipartBody multipartBody=new MultipartBody.Builder()
                        .addFormDataPart("image",file.getName(),requestBody)
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
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.fragm4;
    }

    @Override
    public void MySuccess(HeadReseponse headRespone) {
        Log.d("tag", headRespone.getMessage());
    }

    @Override
    public void MyErorr(String mag) {

    }
}
