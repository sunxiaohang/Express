package com.example.m1320.express;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by m1320 on 2016/8/4.
 */
public class ExpressActivity extends Activity implements HttpRequestListener, View.OnClickListener {
    private Spinner spinner;
    private String[] data = {"顺丰", "圆通", "中通","申通","韵达","天天","EMS","汇通","全峰","德邦"};

//    "com":"顺丰","no":"sf"
//            "com":"申通","no":"sto"
//            "com":"圆通","no":"yt"
//            "com":"韵达","no":"yd"
//            "com":"天天",
//            "no":"tt"
//            "com":"EMS",
//            "no":"ems"
//            "com":"中通",
//            "no":"zto"
//            "com":"汇通",
//            "no":"ht"
//            "com":"全峰",
//            "no":"qf"
//            "com":"德邦",
//            "no":"db"

    private Button query;
    private HttpData httpData;
    private String url;
    private List<Remark> lists;
    private ListView lvss;
    private ListViewBaseAdapter lvadapter;
    private AutoCompleteTextView actv;
    private List<String> auto_items;
    private  SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /***
         * 设置住布局视图
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);

        /***
         * spinner的下拉菜单适配器
         */
        lists = new ArrayList<Remark>();
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.textview_express, data);
        adapter.setDropDownViewResource(R.layout.textview_expresschecked);
        spinner.setAdapter(adapter);

        /***
         *
         * 返回按钮的监听事件
         */
        Button btn_back= (Button) findViewById(R.id.btn_expressback);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        /***
         * 设置查询按钮的事件监听
         */
        query = (Button) this.findViewById(R.id.query);
        query.setOnClickListener(this);

        /***
         * listView设置Adapter适配器
         */
        lvss = (ListView) findViewById(R.id.listviewlll);
        lvadapter = new ListViewBaseAdapter(lists, ExpressActivity.this);
        lvadapter.notifyDataSetChanged();
        lvss.setAdapter(lvadapter);
        /***
         * 自动补全快递单号
         */
        auto_items= new ArrayList<String>();
        preferences=getSharedPreferences("sxh",MODE_PRIVATE);
        Map<String, ?> allContent = preferences.getAll();
        //注意遍历map的方法
        for(Map.Entry<String, ?>  entry : allContent.entrySet()){
            String ite=preferences.getString(entry.getKey(),"没有历史纪录...");
            auto_items.add(ite);
        }

        actv= (AutoCompleteTextView) findViewById(R.id.editTextnum);
        actv.setThreshold(1);
        ArrayAdapter<String> autoItemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,auto_items);
        actv.setAdapter (autoItemsAdapter);

        /***
         * spinner的Items选中事件监听
         */
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=sf";
                        break;
                    case 1:
                        url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=yt";
                        break;
                    case 2:
                        url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=zto";
                        break;
                    case 3:
                        url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=sto";
                        break;
                    case 4:
                        url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=yd";
                        break;
                    case 5:
                        url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=tt";
                        break;
                    case 6:
                        url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=ems";
                        break;
                    case 7:
                        url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=ht";
                        break;
                    case 8:
                        url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=qf";
                        break;
                    case 9:
                        url = "http://v.juhe.cn/exp/index?key=b98217b676ca482855560fddcb4af250&com=db";
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /***
     * 实现回调返回的结果
     *
     * @param result
     */
    @Override
    public void getRequestData(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject res = jsonObject.getJSONObject("result");
            JSONArray list = res.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject subObj = list.getJSONObject(i);
                Remark remark = new Remark(subObj.getString("remark"), subObj.getString("datetime"), subObj.getString("zone"));
                lists.add(remark);
            }
            lvadapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.query: {
                EditText etNum = (EditText) findViewById(R.id.editTextnum);
                String nu = etNum.getText().toString();
                while(nu==null)
                    Toast.makeText(this,"快递单号不能为空，请重新输入",Toast.LENGTH_SHORT).show();
                //575677355677;
                //408150904255
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("n1",nu);
                editor.commit();
                url = url + "&no=" + nu;
                System.out.println(url);
                httpData = (HttpData) new HttpData(url, this).execute();
            }
            break;
            default:
                break;
        }
    }
}
