package com.example.m1320.express;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FocuseActivity extends AppCompatActivity implements HttpRequestListener {
    private List<Msg> msgList = new ArrayList<Msg>();
    private ListView list_view;
    private EditText input_text;
    private Button send;
    private Context content=FocuseActivity.this;
    private MsgListViewBaseAdapter adapter;
    private HttpData httpData;
    private String url="http://www.tuling123.com/openapi/api?key=fafb8495c7644206b505b061e142eb15&userid='sunxiaohang'&info=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.focuse_main);
        list_view = (ListView) findViewById(R.id.chatmain);
        input_text = (EditText) findViewById(R.id.inputtext);
        Button btn_focusback= (Button) findViewById(R.id.focuseback);
        btn_focusback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        send = (Button) findViewById(R.id.btnsend);
        adapter = new MsgListViewBaseAdapter(this, R.layout.msglayout, msgList);
        list_view.setAdapter(adapter);
        //发送消息
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input_text.getText().toString();
                if (!"".equals(text)) {
                    Msg msg = new Msg(text, Msg.TYPE_SENT);
                    msgList.add(msg);
                    //当有新消息时，更新ListView
                    adapter.notifyDataSetChanged();
                    //将ListView定位到最后一行
                    list_view.setSelection(msgList.size());
                    input_text.setText("");//情况输入框的内容
                    String urls=url+text;
                    new AsyncTask<String,Void,Void>()
                    {

                        @Override
                        protected Void doInBackground(String... voids) {
                            submit(voids[0]);
                            return null;
                        }
                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                        }
                    }.execute(urls);
                }
            }
        });
    }

    private void submit(String message) {
        httpData =(HttpData) new HttpData(message,FocuseActivity.this).execute();
    }

    @Override
    public void getRequestData(String result) {
        String str=result.substring(22,result.length()-2);
        Msg remark=new Msg(str,Msg.TYPE_RECEIVED);
        msgList.add(remark);
        adapter.notifyDataSetChanged();
        list_view.setSelection(msgList.size());
    }
}

