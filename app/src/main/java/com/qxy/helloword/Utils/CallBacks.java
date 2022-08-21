package com.qxy.helloword.Utils;

import com.qxy.helloword.Bean.MovieBean;
import com.qxy.helloword.Bean.TelBean;
import com.qxy.helloword.Bean.VarietyBean;

public interface CallBacks {
    void onSuccess(String result);
    void onError(Exception e);
    void onSuccessList(TelBean telBean);
    void onSuccessMovieList(MovieBean movieBean);
    void onSuccessVarietyList(VarietyBean varietyBean);
}
