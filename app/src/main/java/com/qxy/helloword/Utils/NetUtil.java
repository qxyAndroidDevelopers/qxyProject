package com.qxy.helloword.Utils;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;

import com.qxy.helloword.Bean.MovieBean;
import com.qxy.helloword.Bean.TelBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qxy.helloword.Bean.VarietyBean;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NetUtil {
    private String TAG="NetUtil";

    private NetUtil(){};
    private static NetUtil instance = new NetUtil();
    private OkHttpClient okHttpClient=new OkHttpClient();
    private Handler handler=new Handler(Looper.getMainLooper());

    public static NetUtil getInstance() {
        return instance;
    }

    public void doGetToken(Request request,CallBacks callBacks){
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBacks.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String dataString=null;
                Log.d(TAG, "onResponse: 获取client_token的请求码:" + response.code());
                if (response.code() != 200) {
                    return;
                }
                ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    Log.d(TAG,"MISTAKE1");
                    return;
                }

                dataString=responseBody.string();
                Log.d(TAG, "onResponse: 获取client_token成功, 获取到的数据为：" + dataString);
                String token=null;
                JSONObject jsonObject=null;
                try {
                    jsonObject=new JSONObject(dataString);
                    JSONObject object1=jsonObject.getJSONObject("data");
                    token=object1.getString("access_token");
                }catch (Exception e){
                    e.printStackTrace();
                }
                Log.d(TAG, "token：" + token);
                String finalToken = token;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBacks.onSuccess(finalToken);
                    }
                });
            }
        });
    }

    public void doGetList(Request request,int type,CallBacks callBacks){
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBacks.onError(e);
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String dataString=null;
                JSONObject jsonObject=null,object1=null;

                Log.d(TAG, "onResponse: 获取排行榜的请求码:" + response.code());
                if (response.code() != 200) {
                    return;
                }
                ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    Log.d(TAG,"MISTAKE1");
                    return;
                }

                dataString=responseBody.string();
                Log.d(TAG, "onResponse: 获取排行榜成功, 获取到的数据为：" + dataString);
                try {
                    jsonObject=new JSONObject(dataString);
                    object1=jsonObject.getJSONObject("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Gson gson=new Gson();
                if (type==1){
                    Type ListType=new TypeToken<MovieBean>(){}.getType();
                    MovieBean MovieBean = gson.fromJson(String.valueOf(object1),ListType);

                    Log.d(TAG, "MovieListBean：" + MovieBean);
                    Log.d(TAG, "----------------------");

                    for (int i = 0; i < MovieBean.list.size() ; i++) {
                        Log.d(TAG, "MovieListBean：" + MovieBean.list.get(i).release_date);
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBacks.onSuccessMovieList(MovieBean);
                        }
                    });
                }else
                    if(type==2){
                    Type ListType=new TypeToken<TelBean>(){}.getType();
                    TelBean telBean =gson.fromJson(String.valueOf(object1),ListType);

                    Log.d(TAG, "TelListBean：" + telBean);
                    Log.d(TAG, "----------------------");

                    for (int i = 0; i < telBean.list.size() ; i++) {
                        Log.d(TAG, "TelListBean：" + telBean.list.get(i).name);
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBacks.onSuccessList(telBean);
                        }
                    });
                }else
                    if (type==3){
                    Type ListType=new TypeToken<VarietyBean>(){}.getType();
                    VarietyBean varietyBean =gson.fromJson(String.valueOf(object1),ListType);

                    Log.d(TAG, "VarietyListBean：" + varietyBean);
                    Log.d(TAG, "----------------------");

                    for (int i = 0; i < varietyBean.list.size() ; i++) {
                        Log.d(TAG, "VarietyListBean：" + varietyBean.list.get(i).name);
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBacks.onSuccessVarietyList(varietyBean);
                        }
                    });
                }

            }
        });
    }

    public Request getTokenHttp(){
//        获取token
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse("https://open.douyin.com/oauth/client_token/")).newBuilder();
        HttpUrl url = urlBuilder.addQueryParameter("client_key", Constants.CLIENT_KEY)
                .addQueryParameter("client_secret", Constants.CLIENT_SECRET)
                .addQueryParameter("grant_type", "client_credential").build();

        return new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "multipart/form-data")
                .get()
                .build();
    }

    public Request getHttp(String result,int type){
//        地址设置
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse("https://open.douyin.com/discovery/ent/rank/item/")).newBuilder();
        HttpUrl url2 = urlBuilder.addQueryParameter("type", String.valueOf(type)).build();

        return new Request.Builder()
                .url(url2)
                .addHeader("Content-Type","application/json")
                .addHeader("access-token",result)
                .get()
                .build();
    }
}
