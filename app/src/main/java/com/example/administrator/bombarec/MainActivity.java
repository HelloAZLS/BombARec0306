package com.example.administrator.bombarec;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bombarec.domain.Person;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String AppKey = "686dd6cbf2d3f86022e7ca491b58504f";
    private int REQUEST_CODE = 103;
    private Button btnAdd;
    private Button btnReg;
    private Button btnLogn;
    private RecyclerView mRecy;
    private ArrayList<Person> mArrays;
    private MyRecyAdapter mRecyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Bmob.initialize(this, AppKey);
        initDate();
        mRecy.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyAdapter = new MyRecyAdapter(mArrays, MainActivity.this);
        //mRecy.setAdapter(mRecyAdapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        try {
            delPersonData(data, resultCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void delPersonData(Intent data, int resultCode) {
        String name = data.getStringExtra("name");
        String age = data.getStringExtra("age").trim();
        String addr = data.getStringExtra("addr");
        int position = data.getIntExtra("position", -1);
        String mBitmap = data.getStringExtra("mBitmap");
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(addr)) {
            int nAge = Integer.parseInt(age);
            Person person = new Person(name, nAge, addr, mBitmap);
            //增加
            if (resultCode == 10010) {
                person.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "增加成功", Toast.LENGTH_SHORT).show();
                            initDate();
                            mRecyAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(MainActivity.this, "增加失败" + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (resultCode == 11111) {
                //修改
                if (position != -1) {
                    person.update(mArrays.get(position).getObjectId(), new UpdateListener() {

                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(MainActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                                initDate();
                            } else {
                                Toast.makeText(MainActivity.this, "更新失败" + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
            }
        }
    }

    public void initDate() {
        mArrays = new ArrayList<>();
       /* for (int i=0;i<10;i++) {
             Person person = new Person("杨颖"+i,20+i,"广东"+i);
            mArrays.add(person);
        }*/
        BmobQuery<Person> bmobQuery = new BmobQuery<>();
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                if (e == null) {
                    for (Person p : list) {
                        mArrays.add(p);
                    }
                    if (mRecyAdapter != null) {
                        ////写到这里
                        mRecyAdapter = new MyRecyAdapter(mArrays, MainActivity.this);
                    }
                    mRecy.setAdapter(mRecyAdapter);

                } else {
                    Toast.makeText(MainActivity.this, "查询失败" + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        mRecy = (RecyclerView) findViewById(R.id.recycler);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnReg = (Button) findViewById(R.id.btn_reg);
        btnLogn = (Button) findViewById(R.id.btn_logn);
        btnReg.setOnClickListener(this);
        btnLogn.setOnClickListener(this);

        //增加
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonDetailActivity.class);
                intent.putExtra("REQUEST", "MAIN");
                startActivityForResult(intent, REQUEST_CODE);


            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reg:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_logn:
                Intent intent1 = new Intent(MainActivity.this,LognActivity.class);
                startActivity(intent1);
                break;

        }
    }


    //RecyclerView 适配器
    class MyRecyAdapter extends RecyclerView.Adapter<MyRecyAdapter.MyViewHolder> {

        private final MainActivity mainUI;
        private Context ctx;
        private int REQUEST_CODE_ADA = 10011;
        private final ArrayList<Person> mArrays;


        public MyRecyAdapter(ArrayList<Person> mArrays, MainActivity mainUI) {
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

            holder.tvName.setText(person.getName());
            holder.tvAge.setText(person.getAge() + "");
            holder.tvAddr.setText(person.getAddress());
            Bitmap string2Bitmap = BitmapUtil.string2Bitmap(person.getmBit());
            holder.Tou.setImageBitmap(string2Bitmap);
            //删除按钮处理
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
                                initDate();

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
                    intent.putExtra("REQUEST", "CESHI");
                    intent.putExtra("name", person.getName());
                    intent.putExtra("age", person.getAge() + "");
                    intent.putExtra("addr", person.getAddress());
                    intent.putExtra("position", position);
                    intent.putExtra("stringBit", person.getmBit());
                    mainUI.startActivityForResult(intent, REQUEST_CODE_ADA);
                }
            });

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
            ImageView Tou;

            public MyViewHolder(View view) {
                super(view);
                llEd = (LinearLayout) view.findViewById(R.id.ll_ed);
                btDel = (Button) view.findViewById(R.id.btn_del);
                tvName = (TextView) view.findViewById(R.id.tv_name);
                tvAge = (TextView) view.findViewById(R.id.tv_age);
                tvAddr = (TextView) view.findViewById(R.id.tv_addr);
                Tou = (ImageView) view.findViewById(R.id.iv_tou);
            }
        }
    }


}
