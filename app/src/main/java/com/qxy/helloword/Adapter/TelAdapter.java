package com.qxy.helloword.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.helloword.Bean.TelBean;
import com.qxy.helloword.Holder.TelHolder;
import com.qxy.helloword.R;

import java.util.ArrayList;
import java.util.List;

public class TelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private TextView  telRank;
    private LayoutInflater layoutInflater;
    private List<TelBean.S> mylist=new ArrayList<>();

    public TelAdapter(Context context) {
        layoutInflater=LayoutInflater.from(context);
    }

    public void addList(List<TelBean.S> list){
        mylist.addAll(list);

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TelHolder(layoutInflater.inflate(R.layout.activity_model_tel,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        telRank=holder.itemView.findViewById(R.id.telRank);
        int rank=position+1;
        telRank.setText("TOP"+rank);
         ((TelHolder)(holder)).bindHolder(mylist.get(position));
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
