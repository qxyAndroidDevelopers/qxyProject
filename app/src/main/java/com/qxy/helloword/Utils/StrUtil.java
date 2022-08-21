package com.qxy.helloword.Utils;

import java.text.DecimalFormat;

public class StrUtil {

    public String hotData(String data){
        float num=10000;
        DecimalFormat df=new DecimalFormat("0.0");
        float hotNum= Float.parseFloat(data)/10000;
        return df.format(hotNum)+"万";
    }

}
