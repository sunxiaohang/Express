package com.example.m1320.express;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by m1320 on 2016/8/4.
 */
public class ListViewBaseAdapter extends BaseAdapter {
    private List<Remark> lists;
    private Context context;


    public ListViewBaseAdapter(List<Remark> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout ll = null;
        ll = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.listview_item, null);

        ImageView imageView= (ImageView) ll.findViewById(R.id.green_right);
        TextView remark = (TextView) ll.findViewById(R.id.tv_remark);
        TextView datetime = (TextView) ll.findViewById(R.id.tv_datime);
        TextView zone = (TextView) ll.findViewById(R.id.tv_zone);

        imageView.setImageResource(R.drawable.pp);
        remark.setText(lists.get(i).getRemark());
        datetime.setText(lists.get(i).getDatetime());
        zone.setText(lists.get(i).getZonen());
        return ll;
    }
}
