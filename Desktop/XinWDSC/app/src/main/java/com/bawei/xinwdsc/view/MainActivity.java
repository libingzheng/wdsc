package com.bawei.xinwdsc.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bawei.xinwdsc.R;

public class MainActivity extends AppCompatActivity {
    private int Time=5;
    private TextView time;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==0){
                if (Time>0){
                    Time--;
                    time.setText(Time+"s");
                    handler.sendEmptyMessageDelayed(0,1000);
                }else {
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.tv_time);
        handler.sendEmptyMessageDelayed(0,1000);
    }
}
