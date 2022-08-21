package com.qxy.helloword.Holder;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qxy.helloword.Bean.ListBean;
import com.qxy.helloword.R;

import java.text.DecimalFormat;
import java.util.Objects;

public class telHolder extends RecyclerView.ViewHolder {
    public ImageView poster;
    public TextView title,actor,startTime,hot;
    public telHolder(@NonNull View itemView) {
        super(itemView);
        poster=itemView.findViewById(R.id.poster);
        title=itemView.findViewById(R.id.title);
        actor=itemView.findViewById(R.id.actor);
        startTime=itemView.findViewById(R.id.startTime);
        hot=itemView.findViewById(R.id.hot);
    }

    @SuppressLint("SetTextI18n")
    public void bindHolder(ListBean.S list){
        String act=list.actors.get(0)+"/";
        Glide.with(poster).load(list.poster).fitCenter().into(poster);
        for (int i = 1; i < 3; i++) {
            if (i==2){
                act+=list.actors.get(i);
            }else{
                act+=list.actors.get(i)+"/";
            }
        }
        actor.setText(act);
        if (!Objects.equals(list.name_en, "")) {
            title.setText(list.name+"("+list.name_en+")");
        }else{
            title.setText(list.name);
        }
        startTime.setText(list.release_date+" 播出");
        DecimalFormat df=new DecimalFormat("0.0");
        float hotNum= Float.parseFloat(list.hot)/10000;
        hot.setText(df.format(hotNum)+"万");
    }
}
