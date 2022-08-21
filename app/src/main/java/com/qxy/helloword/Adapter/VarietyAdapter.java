package com.qxy.helloword.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.helloword.Bean.MovieBean;
import com.qxy.helloword.Bean.VarietyBean;
import com.qxy.helloword.Holder.MovieHolder;
import com.qxy.helloword.Holder.VarietyHolder;
import com.qxy.helloword.R;

import java.util.ArrayList;
import java.util.List;

public class VarietyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TextView  varietyRank;
    private LayoutInflater vLayoutInflater;
    private List<VarietyBean.V> mylist=new ArrayList<>();

    public VarietyAdapter(Context context) {
        vLayoutInflater=LayoutInflater.from(context);
    }

    public void addList(List<VarietyBean.V> list){
        mylist.addAll(list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VarietyHolder(vLayoutInflater.inflate(R.layout.activity_model_variety,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        varietyRank=holder.itemView.findViewById(R.id.varietyRank);
        int rank=position+1;
        varietyRank.setText("TOP"+rank);
        ((VarietyHolder)(holder)).bindHolder(mylist.get(position));
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
