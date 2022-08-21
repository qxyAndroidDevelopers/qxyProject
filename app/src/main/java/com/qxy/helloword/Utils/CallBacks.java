package com.qxy.helloword.Utils;

import com.qxy.helloword.Bean.ListBean;

public interface CallBacks {
    void onSuccess(String result);
    void onError(Exception e);
    void onSuccessList(ListBean listBean);
}
