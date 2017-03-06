package com.example.administrator.bombarec.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.bombarec.R;
import com.example.administrator.bombarec.domain.Person;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/6.
 */

public class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.MyViewHolder> {


    private final ArrayList<Person> mArrays;

    public MyRecyAdapter(ArrayList<Person> mArrays) {
        this.mArrays = mArrays;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recy_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Person person = mArrays.get(position);

        holder.tvName.setText(person.getName());
        holder.tvAge.setText(person.getAge()+"");
        holder.tvAddr.setText(person.getAddress());
    }

    @Override
    public int getItemCount() {
        return mArrays.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvAge;
        TextView tvAddr;
        public MyViewHolder(View view) {
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvAge = (TextView) view.findViewById(R.id.tv_age);
            tvAddr = (TextView) view.findViewById(R.id.tv_addr);
        }
    }
}
