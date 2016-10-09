package com.example.m1320.express;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopNewsMainActivity extends AppCompatActivity implements HttpRequestListener, View.OnClickListener {
    private RecyclerView rv;
    private List<TopNewsRemark> lists;
    private HttpData httpData;
    private PullToRefreshListView newsListView;
    private TopNewsListViewBaseAdapter listViewBaseAdapter;
    private WebConnectType wct;
    private String newsurl="http://v.juhe.cn/toutiao/index?type=top&key=331a104e0147ddbbbf1f36ca71237d61";
    private String networkstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topnewsmain);
        lists = new ArrayList<TopNewsRemark>();
        rv = (RecyclerView) findViewById(R.id.recycleViewTop);
        rv.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, false));
        RecycleViewAdapter rcva = new RecycleViewAdapter(DataList.newsTypes);
        rv.setHasFixedSize(true);
        rv.setAdapter(rcva);
        newsListView = (PullToRefreshListView) findViewById(R.id.mainlistview);
        newsListView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        newsListView.setShowDividers(0);
        listViewBaseAdapter = new TopNewsListViewBaseAdapter(lists, this);
        listViewBaseAdapter.notifyDataSetChanged();
        newsListView.setAdapter(listViewBaseAdapter);
        wct = new WebConnectType();
        this.refreshListView();
        /***
         *
         * pulltoRefresh
         */
        newsListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Context, Void, Void>() {
                    @Override
                    protected Void doInBackground(Context... params) {
                        try {
                            Thread.sleep(1500);
                            refreshListView();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result) {
                        newsListView.onRefreshComplete();
                    }
                }.execute(TopNewsMainActivity.this);
            }
        });
        /***
         * recycleView的itemsOnClickListener
         */
        rcva.setOnItemClickListener(new RecycleViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data) {
                switch (data)
                {
                    case "头条":
                    {
                        lists.clear();
                        newsurl="http://v.juhe.cn/toutiao/index?type=top&key=331a104e0147ddbbbf1f36ca71237d61";
                        httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
                    }
                    break;
                    case "社会":
                    {
                        lists.clear();
                        newsurl="http://v.juhe.cn/toutiao/index?type=shehui&key=331a104e0147ddbbbf1f36ca71237d61";
                        httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
                    }
                    break;
                    case "国内":
                    {
                        lists.clear();
                        newsurl="http://v.juhe.cn/toutiao/index?type=guonei&key=331a104e0147ddbbbf1f36ca71237d61";
                        httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
                    }
                    break;
                    case "国际":
                    {
                        lists.clear();
                        newsurl="http://v.juhe.cn/toutiao/index?type=guoji&key=331a104e0147ddbbbf1f36ca71237d61";
                        httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
                    }
                    break;
                    case "娱乐":
                    {
                        lists.clear();
                        newsurl="http://v.juhe.cn/toutiao/index?type=yule&key=331a104e0147ddbbbf1f36ca71237d61";
                        httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
                    }
                    break;
                    case "体育":
                    {
                        lists.clear();
                        newsurl="http://v.juhe.cn/toutiao/index?type=tiyu&key=331a104e0147ddbbbf1f36ca71237d61";
                        httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
                    }
                    break;
                    case "军事":
                    {
                        lists.clear();
                        newsurl="http://v.juhe.cn/toutiao/index?type=junshi&key=331a104e0147ddbbbf1f36ca71237d61";
                        httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
                    }
                    break;
                    case "科技":
                    {
                        lists.clear();
                        newsurl="http://v.juhe.cn/toutiao/index?type=keji&key=331a104e0147ddbbbf1f36ca71237d61";
                        httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
                    }
                    break;
                    case "财经":
                    {
                        lists.clear();
                        newsurl="http://v.juhe.cn/toutiao/index?type=caijing&key=331a104e0147ddbbbf1f36ca71237d61";
                        httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
                    }
                    break;
                    case "时尚":
                    {
                        lists.clear();
                        newsurl="http://v.juhe.cn/toutiao/index?type=shishang&key=331a104e0147ddbbbf1f36ca71237d61";
                        httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
                    }
                    break;
                }
            }
        });
        /***
         * listViewOnItemClickListener
         */
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String url = lists.get(i-1).getUrl();
                Intent intent = new Intent(TopNewsMainActivity.this, NewsContent.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        /***
         * imageButton点击动画效果
         */
        ImageButton ib1 = (ImageButton) findViewById(R.id.refresh);
        ImageButton ib2 = (ImageButton) findViewById(R.id.video);
        ImageButton ib3 = (ImageButton) findViewById(R.id.focuse);
        ImageButton ib4 = (ImageButton) findViewById(R.id.personal);
        ib1.setOnClickListener(this);
        ib2.setOnClickListener(this);
        ib3.setOnClickListener(this);
        ib4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refresh: {
                this.refreshListView();
            }
            break;
            case R.id.video: {
                Intent intent=new Intent(TopNewsMainActivity.this,JoyActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.focuse: {
                Intent intent=new Intent(TopNewsMainActivity.this,FocuseActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.personal: {
                Intent intent=new Intent(TopNewsMainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
            break;
        }
    }

    @Override
    public void getRequestData(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject res = jsonObject.getJSONObject("result");
            JSONArray list = res.getJSONArray("data");
            for (int i = 0; i < list.length(); i++) {
                JSONObject subObj = list.getJSONObject(i);
                TopNewsRemark remark = new TopNewsRemark(subObj.getString("title"), subObj.getString("date"), subObj.getString("author_name"), subObj.getString("thumbnail_pic_s"), subObj.getString("url"));
                lists.add(remark);
            }
            listViewBaseAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void refreshListView() {
        networkstatus = wct.checkNetworkType(this);
        if (!networkstatus.equals("DISCONNECT"))
            httpData = (HttpData) new HttpData(newsurl, TopNewsMainActivity.this).execute();
        else {
            AlertDiaLog();
        }
    }
    public void AlertDiaLog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TopNewsMainActivity.this);
        builder.setMessage("确认退出吗？");
        builder.setTitle("网络连接出错，是否进行网络设置");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");
                startActivity(wifiSettingsIntent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}

