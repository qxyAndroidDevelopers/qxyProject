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
import com.qxy.helloword.R;
import com.qxy.helloword.Utils.StrUtil;

import java.util.Objects;

public class MovieHolder extends RecyclerView.ViewHolder {
    private String TAG="Data";
    public ImageView moviePoster;
    public TextView mTitle,mActor,mDirector,mArea,mType,mStartTime,mHot;
    public MovieHolder(@NonNull View itemView) {
        super(itemView);
        moviePoster=itemView.findViewById(R.id.moviePoster);
        mTitle=itemView.findViewById(R.id.mTitle);
        mActor=itemView.findViewById(R.id.mActor);
        mDirector=itemView.findViewById(R.id.mDirector);
        mActor=itemView.findViewById(R.id.mActor);
        mType=itemView.findViewById(R.id.movieType);
        mArea=itemView.findViewById(R.id.mArea);
        mStartTime=itemView.findViewById(R.id.mStartTime);
        mHot=itemView.findViewById(R.id.hot);
    }

    @SuppressLint("SetTextI18n")
    public void bindHolder(MovieBean.M list){

        Glide.with(moviePoster).load(list.poster).fitCenter().into(moviePoster);

        String act="暂无";
        if (!Objects.equals(list.actors, null)){
           act=list.actors.get(0);
        }
        mActor.setText("主演："+act);
        Log.d(TAG,act);


        if (!Objects.equals(list.areas, null)){
            mArea.setText("地区："+list.areas.get(0));
        }else{
            mArea.setText("地区：暂无");
        }


        String type="暂无";
        if (!Objects.equals(list.tags, null)) {
            for (int i = 0; i < list.tags.size(); i++) {
                if (i==list.tags.size()-1){
                    type=list.tags.get(i);
                }else{
                    type=list.tags.get(i)+"/";
                }
            }
        }
        mType.setText("类型："+type);

        if (!Objects.equals(list.directors, null)){
            mDirector.setText("导演："+list.directors.get(0));
        }else{
            mDirector.setText("导演：暂无");
        }

        mTitle.setText(list.name);

        if (!Objects.equals(list.release_date, "")){
            mStartTime.setText(list.release_date+" 上映");
        }else{
            mStartTime.setText("2022 上映");
        }


        StrUtil strUtil=new StrUtil();
        mHot.setText(strUtil.hotData(list.hot));
    }
}
