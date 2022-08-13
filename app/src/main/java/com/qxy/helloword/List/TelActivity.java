package com.qxy.helloword.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.helloword.Adapter.TelAdapter;
import com.qxy.helloword.Bean.ListBean;
import com.qxy.helloword.R;
import com.qxy.helloword.Utils.CallBacks;
import com.qxy.helloword.Utils.Constants;
import com.qxy.helloword.Utils.NetUtil;
import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.Request;

public class TelActivity extends AppCompatActivity {
    private String TAG="TelActivitty";

    private RecyclerView recyclerView;
    private TelAdapter telAdapter;
    private TextView telListDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list );
        initView();
        initEvent();
    }

    private void initView() {
        findViewById(R.id.telList);
        recyclerView= findViewById(R.id.List);
        telListDate=findViewById(R.id.telListDate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.
                VERTICAL,false));
        telAdapter=new TelAdapter(this);
        recyclerView.setAdapter(telAdapter);
    }

    private void initEvent() {

        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse("https://open.douyin.com/oauth/client_token/")).newBuilder();
        HttpUrl url = urlBuilder.addQueryParameter("client_key", Constants.CLIENT_KEY)
                .addQueryParameter("client_secret", Constants.CLIENT_SECRET)
                .addQueryParameter("grant_type", "client_credential").build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "multipart/form-data")
                .get()
                .build();

        NetUtil.getInstance().doGetToken(request, new CallBacks() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG,"access_token："+result);

                HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse("https://open.douyin.com/discovery/ent/rank/item/")).newBuilder();
                HttpUrl url2 = urlBuilder.addQueryParameter("type", String.valueOf(2)).build();
                Request request2 = new Request.Builder()
                        .url(url2)
                        .addHeader("Content-Type","application/json")
                        .addHeader("access-token",result)
                        .get()
                        .build();

                NetUtil.getInstance().doGetList(request2, new CallBacks() {
                    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
                    @Override
                    public void onSuccessList(ListBean listBean) {
                        telListDate.setText(listBean.active_time);
                        telAdapter.addList(listBean.list);
                        telAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onSuccess(String result) {}

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(TelActivity.this,"请求失败",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(TelActivity.this,"请求失败",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccessList(ListBean listBean) {}
        });
    }
}
