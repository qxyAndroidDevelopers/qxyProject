package com.qxy.helloword;

import android.app.Application;

import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;
import com.bytedance.sdk.open.douyin.DouYinOpenConfig;
import com.qxy.helloword.Utils.Constants;

import java.util.HashMap;
import java.util.Map;


/**
 * 主要功能：自定义{@link Application}
 * since: 2018/12/25
 */
public class MyApplication extends Application {

    public static final String AUTH_CODE = "auth_code";
    public static final String ACCESS_TOKEN = "access-token";
    public static final String CLIENT_TOKEN = "client-token";

    public static MyApplication instance;

    public static MyApplication getInstance() {

        return instance;
    }

    private final Map<String, String> map = new HashMap<>();


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DouYinOpenApiFactory.init(new DouYinOpenConfig(Constants.CLIENT_KEY));
    }

    public void put(String key, String data) {
        map.put(key, data);
    }

    public String get(String key) {
        return map.get(key);
    }

}

