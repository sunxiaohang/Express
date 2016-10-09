package com.example.m1320.express;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,View.OnClickListener{
    private ViewPager vp;
    private AppPagerAdapter adapter;//声明自定义的适配器类
    private List<View> views;//定义存放视图的集合
    private Button btn_news;
    private Button btn_express;
    private Button btn_chatman;
    private Button btn_joks;
    private Button btn_fight;
    private Button btn_calender;
    private Button btn_share;
    private SlidingMenu slidingMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        views=new ArrayList<View>();
        initViews();
        /***
         *views views数组
         * this 表示当前context
         */
        adapter=new AppPagerAdapter(views,this);//创建适配器对象
        vp= (ViewPager) findViewById(R.id.appviewpager);
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(this);
        /***
         * 创建slidingmenu
         */
        slidingMenu=new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenuwidth);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
//        设置滑动时菜单的是否淡入淡出
        slidingMenu.setFadeEnabled(true);
        //设置淡入淡出的比例
        slidingMenu.setFadeDegree(0.4f);
        //设置滑动时拖拽效果
        slidingMenu.setBehindScrollScale(0);
        slidingMenu.setMenu(R.layout.activity_guidepageabout);
        FloatingActionButton btn_about= (FloatingActionButton) findViewById(R.id.btn_about);
        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenu.toggle(true);
            }
        });

        /***
         *
         * button事件监听
         */
        btn_news= (Button) views.get(0).findViewById(R.id.btn_topnews);
        btn_express = (Button) views.get(1).findViewById(R.id.btn_express);
        btn_chatman= (Button) views.get(2).findViewById(R.id.btn_chatman);
        btn_joks= (Button) views.get(3).findViewById(R.id.btn_joks);
        btn_fight= (Button) views.get(4).findViewById(R.id.btn_fight);
        btn_calender= (Button) views.get(5).findViewById(R.id.btn_calender);
        btn_share= (Button) findViewById(R.id.btn_share);
        btn_news.setOnClickListener(this);
        btn_express.setOnClickListener(this);
        btn_chatman.setOnClickListener(this);
        btn_joks.setOnClickListener(this);
        btn_fight.setOnClickListener(this);
        btn_calender.setOnClickListener(this);
        btn_share.setOnClickListener(this);
    }
    private void initViews() {
        views=new ArrayList<View>();
        LayoutInflater inflater=LayoutInflater.from(this);
        views.add(inflater.inflate(R.layout.activity_guidepagenews,null));
        views.add(inflater.inflate(R.layout.activity_guidepageexpress,null));
        views.add(inflater.inflate(R.layout.activity_guidepagechatman,null));
        views.add(inflater.inflate(R.layout.activity_guidepagejoks,null));
        views.add(inflater.inflate(R.layout.activity_guidepagefight,null));
        views.add(inflater.inflate(R.layout.activity_guidepagecalender,null));
    }
    /**
     * 设置状态栏背景状态
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position)
        {
            case 0:
            {
                TextView tv= (TextView) findViewById(R.id.topnews);
                tv.setTextColor(0xffffffff);
                TextView tv1= (TextView) findViewById(R.id.express);
                tv1.setTextColor(0xaa263137);
                TextView tv2= (TextView) findViewById(R.id.chatandroid);
                tv2.setTextColor(0xaa263137);
                TextView tv3= (TextView) findViewById(R.id.joke);
                tv3.setTextColor(0xaa263137);
                TextView tv4= (TextView) findViewById(R.id.fightplane);
                tv4.setTextColor(0xaa263137);
                TextView tv5= (TextView) findViewById(R.id.about);
                tv5.setTextColor(0xaa263137);
            }
                break;
            case 1:
            {
                TextView tv= (TextView) findViewById(R.id.express);
                tv.setTextColor(0xffffffff);
                TextView tv1= (TextView) findViewById(R.id.topnews);
                tv1.setTextColor(0xaa263137);
                TextView tv2= (TextView) findViewById(R.id.chatandroid);
                tv2.setTextColor(0xaa263137);
                TextView tv3= (TextView) findViewById(R.id.joke);
                tv3.setTextColor(0xaa263137);
                TextView tv4= (TextView) findViewById(R.id.fightplane);
                tv4.setTextColor(0xaa263137);
                TextView tv5= (TextView) findViewById(R.id.about);
                tv5.setTextColor(0xaa263137);
            }
            break;
            case 2:
            {
                TextView tv= (TextView) findViewById(R.id.chatandroid);
                tv.setTextColor(0xffffffff);
                TextView tv1= (TextView) findViewById(R.id.express);
                tv1.setTextColor(0xaa263137);
                TextView tv2= (TextView) findViewById(R.id.topnews);
                tv2.setTextColor(0xaa263137);
                TextView tv3= (TextView) findViewById(R.id.joke);
                tv3.setTextColor(0xaa263137);
                TextView tv4= (TextView) findViewById(R.id.fightplane);
                tv4.setTextColor(0xaa263137);
                TextView tv5= (TextView) findViewById(R.id.about);
                tv5.setTextColor(0xaa263137);
            }
            break;
            case 3:
            {
                TextView tv= (TextView) findViewById(R.id.joke);
                tv.setTextColor(0xffffffff);
                TextView tv1= (TextView) findViewById(R.id.express);
                tv1.setTextColor(0xaa263137);
                TextView tv2= (TextView) findViewById(R.id.chatandroid);
                tv2.setTextColor(0xaa263137);
                TextView tv3= (TextView) findViewById(R.id.topnews);
                tv3.setTextColor(0xaa263137);
                TextView tv4= (TextView) findViewById(R.id.fightplane);
                tv4.setTextColor(0xaa263137);
                TextView tv5= (TextView) findViewById(R.id.about);
                tv5.setTextColor(0xaa263137);
            }
            break;
            case 4:
            {
                TextView tv= (TextView) findViewById(R.id.joke);
                tv.setTextColor(0xaa263137);
                TextView tv1= (TextView) findViewById(R.id.express);
                tv1.setTextColor(0xaa263137);
                TextView tv2= (TextView) findViewById(R.id.chatandroid);
                tv2.setTextColor(0xaa263137);
                TextView tv3= (TextView) findViewById(R.id.topnews);
                tv3.setTextColor(0xaa263137);
                TextView tv4= (TextView) findViewById(R.id.about);
                tv4.setTextColor(0xaa263137);
                TextView tv5= (TextView) findViewById(R.id.fightplane);
                tv5.setTextColor(0xffffffff);
            }
            break;
            case 5:
            {
                TextView tv= (TextView) findViewById(R.id.joke);
                tv.setTextColor(0xaa263137);
                TextView tv1= (TextView) findViewById(R.id.express);
                tv1.setTextColor(0xaa263137);
                TextView tv2= (TextView) findViewById(R.id.chatandroid);
                tv2.setTextColor(0xaa263137);
                TextView tv3= (TextView) findViewById(R.id.topnews);
                tv3.setTextColor(0xaa263137);
                TextView tv4= (TextView) findViewById(R.id.fightplane);
                tv4.setTextColor(0xaa263137);
                TextView tv5= (TextView) findViewById(R.id.about);
                tv5.setTextColor(0xffffffff);
            }
            break;
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_topnews:
                Intent intent1=new Intent(MainActivity.this,TopNewsMainActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_express: {
                Intent intent2 = new Intent(MainActivity.this, ExpressActivity.class);
                startActivity(intent2);
            }
                break;
            case R.id.btn_chatman: {
                Intent intent3 = new Intent(MainActivity.this, FocuseActivity.class);
                startActivity(intent3);
            }
                break;
            case R.id.btn_joks: {
                Intent intent4 = new Intent(MainActivity.this, JoyActivity.class);
                startActivity(intent4);
            }
                break;
            case R.id.btn_fight:
                Intent intent5=new Intent(MainActivity.this,PanlewarActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_calender:
                Intent intent6=new Intent(MainActivity.this,CalendarActivity.class);
                startActivity(intent6);

                break;
            case R.id.btn_share: {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain"); // 分享发送的数据类型
                String msg = "http://123.206.70.183/app-debug.apk";
                intent.putExtra(Intent.EXTRA_TEXT, msg); // 分享的内容
                startActivity(Intent.createChooser(intent, "选择分享"));// 目标应用选择对话框的标题
            }
            break;
        }
    }
}