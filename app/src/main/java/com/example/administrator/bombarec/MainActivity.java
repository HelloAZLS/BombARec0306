package com.example.administrator.bombarec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.administrator.bombarec.adapter.MyRecyAdapter;
import com.example.administrator.bombarec.domain.Person;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    private String AppKey = "686dd6cbf2d3f86022e7ca491b58504f";
    private Button btnAdd;
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
        mRecyAdapter = new MyRecyAdapter(mArrays);
        mRecy.setAdapter(mRecyAdapter);


    }

    private void initDate() {
        mArrays = new ArrayList<>();
        for (int i=0;i<10;i++) {
             Person person = new Person("杨颖"+i,20+i,"广东"+i);
            mArrays.add(person);
        }
     /*   BmobQuery<Person> bmobQuery = new BmobQuery<>();
        bmobQuery.setLimit(50);
        bmobQuery.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                if (e == null) {
                    for (Person p : list) {
                        mArrays.add(p);
                    }
                    mRecy.setAdapter(mRecyAdapter);

                }else{
                    Toast.makeText(MainActivity.this,"查询失败"+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    private void initViews() {
        mRecy = (RecyclerView) findViewById(R.id.recycler);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }
}
