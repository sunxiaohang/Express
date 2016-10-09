package com.example.m1320.express;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by m1320 on 2016/8/3.
 */
public class WelcomeActivity extends Activity{
    private static final int GO_HOME=1000;//跳转到主界面activity 跳转标记
    private static final int GO_GUIDE=1001;//跳转到引导页 跳转标记
    private boolean flag=false;  //定义标记变量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        //发送消息
        sendMessage();
    }
    private void sendMessage()
    {
        SharedPreferences preferences=getSharedPreferences("ssyt",MODE_PRIVATE);
        flag=preferences.getBoolean("flag",true);//取回preference的flag值，如果不存在赋值为true
        if(!flag) handler.sendEmptyMessageDelayed(GO_HOME,3000);
        else
        {
            handler.sendEmptyMessageDelayed(GO_GUIDE,3000);
            SharedPreferences.Editor editor=preferences.edit();//获取shared preferences的editor
            editor.putBoolean("flag",false);//执行结束后修改flag的变量为false
            editor.commit();
        }
    }
    /***
     * Handler
     */
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case GO_HOME:
                     goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
                default:break;
            }
        }
    };
    private void goHome() {
        Intent i1=new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(i1);
        finish();
    }
    private void goGuide() {
        Intent i2=new Intent(WelcomeActivity.this,Guide.class);
        startActivity(i2);
        finish();
    }
}
