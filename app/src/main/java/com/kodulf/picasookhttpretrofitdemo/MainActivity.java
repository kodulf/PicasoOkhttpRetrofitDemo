package com.kodulf.picasookhttpretrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Callback {

    private Call call;
    private TextView textView;
    private ListView listView;
    private ItemAdapter adapter;
    //private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textView = (TextView) findViewById(R.id.main_text);
        listView = (ListView) findViewById(R.id.main_listView);
        adapter = new ItemAdapter(this);
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(adapter);

        OkHttpClient client = new OkHttpClient();
        //Request request = new Request.Builder().url("http://www.baidu.com/").get().build();
        Request request = new Request.Builder().url("http://m2.qiushibaike.com/article/list/suggest?page=1").get().build();
        call = client.newCall(request);
        //同步请求
        //Response response = call.execute();//这个方法使用频率较低。
        call.enqueue(this);//用的比较多的是这个异步请求。
    }

    @Override
    public void onFailure(Request request, IOException e) {
        e.printStackTrace();
        Log.d("151229MY", "onFailure" + Thread.currentThread().getName());

    }

    @Override
    public void onResponse(Response response) throws IOException {
        Log.d("151229MY", "onSuccessful" + Thread.currentThread().getName());
        final String s = response.body().string();//response.body().可以有很多参数I如内容长度，bytes等等
        try {
            JSONObject object = new JSONObject(s);
            JSONArray items = object.getJSONArray("items");
            //final List<String> list = new ArrayList<String>();
            final List<Item> list = new ArrayList<Item>();
            Log.d("151229MY","item"+items.length());
            for (int i = 0; i <items.length(); i++) {
                //list.add(items.getJSONObject(i).getString("content"));
                list.add(new Item(items.getJSONObject(i)));
                Log.d("151229MY",items.getJSONObject(i).toString());
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //textView.setText(s);
                    //adapter.addAll(list);
                    adapter.addAll(list);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        call.cancel();
    }
}
