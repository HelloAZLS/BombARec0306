package com.example.administrator.bombarec.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bombarec.MainActivity;
import com.example.administrator.bombarec.PersonDetailActivity;
import com.example.administrator.bombarec.R;
import com.example.administrator.bombarec.domain.Person;

import java.util.ArrayList;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/3/6.
 */

public class MyRecyAdapter2 extends RecyclerView.Adapter<MyRecyAdapter2.MyViewHolder> {

    private final MainActivity mainUI;
    private Context ctx;
    private  int REQUEST_CODE_ADA = 10011;
    private final ArrayList<Person> mArrays;


    public MyRecyAdapter2(ArrayList<Person> mArrays, MainActivity mainUI) {
        this.mainUI = mainUI;
        this.mArrays = mArrays;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(ctx)
                .inflate(R.layout.recy_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Person person = mArrays.get(position);

        holder.btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person p2 = new Person();
                p2.setObjectId(mArrays.get(position).getObjectId());
                p2.delete(new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(ctx, "删除成功", Toast.LENGTH_SHORT).show();
                          mArrays.remove(position);
                         /* if (){
                              MyRecyAdapter2 mRecyAdapter = new MyRecyAdapter2(mArrays,mainUI,mRecy);
                              mRecy.setAdapter(mRecyAdapter);
                          }*/

                        } else {
                            Toast.makeText(ctx, "删除失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
//修改
        holder.llEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, PersonDetailActivity.class);
                intent.putExtra("REQUEST","CESHI");
                intent.putExtra("name",person.getName());
                intent.putExtra("age",person.getAge()+"");
                intent.putExtra("addr",person.getAddress());
                intent.putExtra("position",position);
                mainUI.startActivityForResult(intent,REQUEST_CODE_ADA);
            }
        });
        holder.tvName.setText(person.getName());
        holder.tvAge.setText(person.getAge() + "");
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
        Button btDel;
        LinearLayout llEd;

        public MyViewHolder(View view) {
            super(view);
            llEd = (LinearLayout) view.findViewById(R.id.ll_ed);
            btDel = (Button) view.findViewById(R.id.btn_del);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvAge = (TextView) view.findViewById(R.id.tv_age);
            tvAddr = (TextView) view.findViewById(R.id.tv_addr);
        }
    }
}
