package com.qxy.helloword.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.helloword.Bean.ListBean;
import com.qxy.helloword.Holder.telHolder;
import com.qxy.helloword.R;

import java.util.ArrayList;
import java.util.List;

public class TelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<ListBean.S> mylist=new ArrayList<>();

    public TelAdapter(Context context) {
        layoutInflater=LayoutInflater.from(context);
    }

    public void addList(List<ListBean.S> list){
        mylist.addAll(list);

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new telHolder(layoutInflater.inflate(R.layout.activity_model,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
         ((telHolder)(holder)).bindHolder(mylist.get(position));
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
