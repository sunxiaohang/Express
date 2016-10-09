package com.example.m1320.express;


import android.os.AsyncTask;
import android.os.Process;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by m1320 on 2016/8/4.
 * params请求地址
 * process进度
 * Result 返回结果
 */
public class HttpData extends AsyncTask<Void,Process,String> {
    HttpClient httpClient;
    private HttpGet httpGet;
    private HttpResponse httpResponse;
    private HttpEntity httpEntity;
    private String str;
    private HttpRequestListener listener;
    private String url;
    //String url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=zto&no=408150904255";
    /***
     * 声明回调接口
     * @param url
     * @param listener
     */
    public HttpData(String url, HttpRequestListener listener) {
        this.url = url;
        this.listener = listener;
    }

    /***
     * 后台耗时操作
     *
     * @param voids
     * @return
     */
    @Override
    protected String doInBackground(Void... voids) {

        httpClient = new DefaultHttpClient();// 创建一个客户端
        httpGet = new HttpGet(url);
        try {
            httpResponse = httpClient.execute(httpGet);// 返回请求的结果
            httpEntity = httpResponse.getEntity();// 从相应结果 实体
            str = EntityUtils.toString(httpEntity);
            System.out.println(str);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    @Override
    protected void onPostExecute(String result) {
        listener.getRequestData(result);
        super.onPostExecute(result);
    }
}
