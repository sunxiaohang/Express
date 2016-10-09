package com.example.m1320.express;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by m1320 on 2016/8/4.
 */
public class TopNewsListViewBaseAdapter extends BaseAdapter {
    private List<TopNewsRemark> lists;
    private Context context;
    private ImageView imageView1;
    private Bitmap bitmap;
    private URL imageurl;

    public TopNewsListViewBaseAdapter(List<TopNewsRemark> lists, Context context) {
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
        ll = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.news_item, null);
        imageView1 = (ImageView) ll.findViewById(R.id.imageview1);
        TextView title = (TextView) ll.findViewById(R.id.newstitle);
        TextView author = (TextView) ll.findViewById(R.id.author);
        TextView datetime = (TextView) ll.findViewById(R.id.datetime);
        try {
            imageurl= new URL(lists.get(i).getImg1());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                InputStream inputStream = null;
                try {
                    inputStream = getImageViewInputStream(imageurl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bitmap = BitmapFactory.decodeStream(inputStream);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                imageView1.setImageBitmap(bitmap);
            }
        }.execute();
        title.setText(lists.get(i).getTitle());
        author.setText(lists.get(i).getAuthor());
        datetime.setText(lists.get(i).getDatetime());
//        if (i % 2 == 0) ll.setBackgroundColor(0xffeeeeee);
        return ll;
    }

    public static InputStream getImageViewInputStream(URL url) throws IOException {
        InputStream inputStream = null;
        if (url != null) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(1000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            }
        }
        return inputStream;
    }

    static class ViewHolder {
        TextView textView;  //显示数据的view
        Button button;  //删除按钮
    }
}


