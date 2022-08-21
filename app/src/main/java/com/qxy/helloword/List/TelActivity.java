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
import com.qxy.helloword.Bean.MovieBean;
import com.qxy.helloword.Bean.TelBean;
import com.qxy.helloword.Bean.VarietyBean;
import com.qxy.helloword.R;
import com.qxy.helloword.Utils.CallBacks;
import com.qxy.helloword.Utils.NetUtil;

public class TelActivity extends AppCompatActivity {
    private final String TAG="TelActivitty";

    private RecyclerView recyclerView;
    private TelAdapter telAdapter;
    private TextView telListDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tel);
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

        NetUtil.getInstance().doGetToken(NetUtil.getInstance().getTokenHttp(), new CallBacks() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG,"access_token："+result);

                NetUtil.getInstance().doGetList(NetUtil.getInstance().getHttp(result,2),2, new CallBacks() {
                    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
                    @Override

                    public void onSuccessList(TelBean telBean) {
                        telListDate.setText(telBean.active_time);
                        telAdapter.addList(telBean.list);
                        telAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onSuccessMovieList(MovieBean movieBean) {

                    }

                    @Override
                    public void onSuccessVarietyList(VarietyBean varietyBean) {

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
            public void onSuccessList(TelBean telBean) {}

            @Override
            public void onSuccessMovieList(MovieBean movieBean) {

            }

            @Override
            public void onSuccessVarietyList(VarietyBean varietyBean) {

            }
        });
    }
}
