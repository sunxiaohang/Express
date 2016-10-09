package com.example.m1320.express;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Guide extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager vp;
    private MyPagerAdapter adapter;//声明自定义的适配器类
    private List<View> views;//定义存放视图的集合
    private Button start_btn;

    //******************************
    //处理point部分
    private ImageView[] dots;
    private int ids[]={R.id.point1, R.id.point2, R.id.point3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        views=new ArrayList<View>();
        initViews();
        /***
         *views views数组
         * this 表示当前context
         */
        adapter=new MyPagerAdapter(views,this);//创建适配器对象
        vp= (ViewPager) findViewById(R.id.viewpager1);
        vp.setAdapter(adapter);
        start_btn= (Button) views.get(2).findViewById(R.id.button1);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Guide.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        vp.setOnPageChangeListener(this);
    }

    /***
     * 添加布局文件到集合
     * 初始化数据集合
     */
    private void initViews() {
        views=new ArrayList<View>();
        LayoutInflater inflater=LayoutInflater.from(this);
        views.add(inflater.inflate(R.layout.activity_one,null));
        views.add(inflater.inflate(R.layout.activity_two,null));
        views.add(inflater.inflate(R.layout.activity_three,null));

        //添加三张小图片到数组中
        dots=new ImageView[views.size()];
        for(int i=0;i<views.size();i++)
        {
            dots[i]=(ImageView) findViewById(ids[i]);
        }
    }

    /***
     *
     * 页面滑动时候调用
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /***
     * 页面被选中时候调调用
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        for(int i=0;i<dots.length;i++)
        {
            if(position==i)
            {
                dots[i].setImageResource(R.drawable.login_point_selected);
            }else
            {
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }

    /***
     * 页面
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
