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

import com.qxy.helloword.Adapter.MovieAdapter;
import com.qxy.helloword.Adapter.VarietyAdapter;
import com.qxy.helloword.Bean.MovieBean;
import com.qxy.helloword.Bean.TelBean;
import com.qxy.helloword.Bean.VarietyBean;
import com.qxy.helloword.R;
import com.qxy.helloword.Utils.CallBacks;
import com.qxy.helloword.Utils.NetUtil;

public class VarietyActivity extends AppCompatActivity {
    private final String TAG="MovieActivity";

    private VarietyAdapter varietyAdapter;
    private TextView varietyListDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_variety);
        initView();
        initEvent();
    }

    private void initView() {
        findViewById(R.id.varietyLists);
        RecyclerView vRecyclerView = findViewById(R.id.varietyList);
        varietyListDate=findViewById(R.id.varietyListDate);
        vRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.
                VERTICAL,false));
        varietyAdapter=new VarietyAdapter(this);
        vRecyclerView.setAdapter(varietyAdapter);
    }

    private void initEvent() {

        NetUtil.getInstance().doGetToken(NetUtil.getInstance().getTokenHttp(), new CallBacks() {
            private VarietyBean varietyBean;

            @Override
            public void onSuccess(String result) {
                Log.d(TAG,"access_token："+result);

                NetUtil.getInstance().doGetList(NetUtil.getInstance().getHttp(result,3),3, new CallBacks() {

                    @Override
                    public void onSuccessList(TelBean telBean) {

                    }

                    @Override
                    public void onSuccessMovieList(MovieBean movieBean) {

                    }

                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccessVarietyList(VarietyBean varietyBean) {
                        varietyListDate.setText(varietyBean.active_time);
                        Log.d(TAG,"Time："+varietyBean.active_time);
                        varietyAdapter.addList(varietyBean.list);
                        Log.d(TAG,"List："+varietyBean.list);
                        varietyAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onSuccess(String result) {}

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(VarietyActivity.this,"请求失败",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(VarietyActivity.this,"请求失败",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccessList(TelBean telBean) {
            }

            @Override
            public void onSuccessMovieList(MovieBean movieBean) {

            }

            @Override
            public void onSuccessVarietyList(VarietyBean varietyBean) {
                this.varietyBean=varietyBean;
            }
        });
    }
}
