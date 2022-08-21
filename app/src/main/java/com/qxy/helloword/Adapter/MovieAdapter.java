package com.qxy.helloword.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.helloword.Bean.MovieBean;
import com.qxy.helloword.Holder.MovieHolder;
import com.qxy.helloword.R;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TextView  movieRank;
    private LayoutInflater mLayoutInflater;
    private List<MovieBean.M> mylist=new ArrayList<>();

    public MovieAdapter(Context context) {
        mLayoutInflater=LayoutInflater.from(context);
    }

    public void addList(List<MovieBean.M> list){
        mylist.addAll(list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieHolder(mLayoutInflater.inflate(R.layout.activity_model_movie,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        movieRank=holder.itemView.findViewById(R.id.movieRank);
        int rank=position+1;
        movieRank.setText("TOP"+rank);
        ((MovieHolder)(holder)).bindHolder(mylist.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mylist.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }
}
