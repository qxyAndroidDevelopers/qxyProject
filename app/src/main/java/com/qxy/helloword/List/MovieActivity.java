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
import com.qxy.helloword.Bean.MovieBean;
import com.qxy.helloword.Bean.TelBean;
import com.qxy.helloword.Bean.VarietyBean;
import com.qxy.helloword.R;
import com.qxy.helloword.Utils.CallBacks;
import com.qxy.helloword.Utils.NetUtil;

public class MovieActivity extends AppCompatActivity {
    private final String TAG="MovieActivity";

    private MovieAdapter movieAdapter;
    private TextView movieListDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);
        initView();
        initEvent();
    }

    private void initView() {
        findViewById(R.id.movieList);
        RecyclerView mRecyclerView = findViewById(R.id.mvList);
        movieListDate=findViewById(R.id.MovieListDate);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.
                VERTICAL,false));
        movieAdapter=new MovieAdapter(this);
        mRecyclerView.setAdapter(movieAdapter);
    }

    private void initEvent() {

        NetUtil.getInstance().doGetToken(NetUtil.getInstance().getTokenHttp(), new CallBacks() {
            private MovieBean movieBean;

            @Override
            public void onSuccess(String result) {
                Log.d(TAG,"access_token："+result);

                NetUtil.getInstance().doGetList(NetUtil.getInstance().getHttp(result,1),1, new CallBacks() {
                    @Override
                    public void onSuccessList(TelBean telBean) {

                    }

                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onSuccessMovieList(MovieBean movieBean) {
                        movieListDate.setText(movieBean.active_time);
                        Log.d(TAG,"Time："+movieBean.active_time);
                        movieAdapter.addList(movieBean.list);
                        Log.d(TAG,"List："+movieBean.list);
                        movieAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onSuccessVarietyList(VarietyBean varietyBean) {

                    }

                    @Override
                    public void onSuccess(String result) {}

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(MovieActivity.this,"请求失败",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(MovieActivity.this,"请求失败",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccessList(TelBean telBean) {
            }

            @Override
            public void onSuccessMovieList(MovieBean movieBean) {
                this.movieBean=movieBean;
            }

            @Override
            public void onSuccessVarietyList(VarietyBean varietyBean) {

            }
        });
    }
}
