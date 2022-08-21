package com.qxy.helloword.Holder;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qxy.helloword.Bean.MovieBean;
import com.qxy.helloword.Bean.VarietyBean;
import com.qxy.helloword.R;
import com.qxy.helloword.Utils.StrUtil;

import java.util.Objects;

public class VarietyHolder extends RecyclerView.ViewHolder {
    private final String TAG="Data";
    public ImageView varietyPoster;
    public TextView vTitle,vDirector,vStartTime,vHot,vEname;

    public VarietyHolder(@NonNull View itemView) {
        super(itemView);
        varietyPoster=itemView.findViewById(R.id.varietyPoster);
        vTitle=itemView.findViewById(R.id.varietyTitle);
        vDirector=itemView.findViewById(R.id.varietyDirectors);
        vStartTime=itemView.findViewById(R.id.varietyStartTime);
        vHot=itemView.findViewById(R.id.varietyHot);
        vEname=itemView.findViewById(R.id.varietyEname);
    }

    @SuppressLint("SetTextI18n")
    public void bindHolder(VarietyBean.V list){

        Glide.with(varietyPoster).load(list.poster).fitCenter().into(varietyPoster);

        vTitle.setText(list.name);

        if(!Objects.equals(list.name_en,"")){
            vEname.setText("英文名："+list.name_en);
        }else {
            vEname.setText("英文名：无");
        }

        vDirector.setText("导演："+list.directors.get(0));

        vStartTime.setText(list.release_date+" 上映");

        StrUtil strUtil=new StrUtil();
        vHot.setText(strUtil.hotData(list.hot));
    }
}
