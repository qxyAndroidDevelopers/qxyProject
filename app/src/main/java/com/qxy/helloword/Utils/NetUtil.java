package com.qxy.helloword.Utils;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.NonNull;
import com.qxy.helloword.Bean.ListBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import okhttp3.Call;
import okhttp3.Callback;
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

    public void doGetList(Request request,CallBacks callBacks){
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
                HashMap<String, String> hashMap=new HashMap<>();
                String list=null;
                String startTime=null;
                String err_code=null;
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
                Type type=new TypeToken<ListBean>(){}.getType();
                ListBean listBean=gson.fromJson(String.valueOf(object1),type);

                Log.d(TAG, "listBean：" + listBean);
                Log.d(TAG, "----------------------");

                for (int i = 0; i <listBean.list.size() ; i++) {
                    Log.d(TAG, "ListBeanList：" + listBean.list.get(i).name);
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBacks.onSuccessList(listBean);
                    }
                });
            }
        });

    }
}
