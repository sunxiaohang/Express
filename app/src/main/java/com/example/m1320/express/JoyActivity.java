package com.example.m1320.express;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JoyActivity extends AppCompatActivity implements HttpRequestListener{
    private List<String> joks;
    private PullToRefreshListView ptListView;
    private ArrayAdapter adapter;
    private HttpData httpData;
    private int position=1;
    private String url1="http://japi.juhe.cn/joke/content/list.from?key=b8ac73dbbd98860d9f2f2eb7c7181f3c&page=";
    private String url2="&pagesize=20&sort=asc&time=1418745237";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joy);
        Button btn_jokback= (Button) findViewById(R.id.joyback);
        btn_jokback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        joks=new ArrayList<String>();
        ptListView= (PullToRefreshListView) findViewById(R.id.jokslistview);
        ptListView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        httpData= (HttpData) new HttpData("http://japi.juhe.cn/joke/content/list.from?key=b8ac73dbbd98860d9f2f2eb7c7181f3c&page=1&pagesize=20&sort=asc&time=1418745237",JoyActivity.this).execute();
        ptListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new AsyncTask<Void,Void,Void>()
                {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        refreshJok();
                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void aVoid) {
                        ptListView.onRefreshComplete();
                    }
                }.execute();
            }
        });
    }

    @Override
    public void getRequestData(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject res = jsonObject.getJSONObject("result");
            JSONArray list = res.getJSONArray("data");
            for (int i = 0; i < list.length(); i++) {
                JSONObject subObj = list.getJSONObject(i);
                String str=subObj.getString("content");
                joks.add(str);
            }
            adapter=new ArrayAdapter(JoyActivity.this,R.layout.joksitem,joks);
            ptListView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void refreshJok()
    {
        joks.clear();
        httpData=(HttpData) new HttpData(url1+position+url2,JoyActivity.this).execute();
        position++;
    }

}
