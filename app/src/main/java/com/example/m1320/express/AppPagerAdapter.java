package com.example.m1320.express;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by m1320 on 2016/8/3.
 */
public class AppPagerAdapter extends PagerAdapter{

    private List<View> views;//存放视图的集合
    private Context context;//上下文对象

    public AppPagerAdapter(List<View> views, Context context) {
        this.views = views;
        this.context = context;
    }
    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    /***
     * 判断视图是否当前显示的视图
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //销毁已经展示的ViewPager
        ((ViewPager)container).removeView(views.get(position));
    }
}
