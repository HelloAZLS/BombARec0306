package com.example.administrator.bombarec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/3/9.
 */

public class LognActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPassWord;
    private Button btnLogn;
    private String AppKey = "686dd6cbf2d3f86022e7ca491b58504f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logn);
        Bmob.initialize(this, AppKey);
        initViews();

    }

    private void initViews() {
        etName = (EditText) findViewById(R.id.et_logn_name);
        etPassWord = (EditText) findViewById(R.id.et_logn_pwd);
        btnLogn = (Button) findViewById(R.id.btn_logn);
        btnLogn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String password = etPassWord.getText().toString().trim();
                BmobUser bu2 = new BmobUser();
                bu2.setUsername(name);
                bu2.setPassword(password);
                bu2.login(new SaveListener<BmobUser>() {

                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null) {
                            Toast.makeText(LognActivity.this, bmobUser.getUsername() + "用户登陆成功,将要跳转到主页面", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LognActivity.this, MainActivity.class);
                            intent.putExtra("username",bmobUser.getUsername());
                            startActivity(intent);
                        } else {
                            Toast.makeText(LognActivity.this, "用户登陆失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.i("消息", e.getMessage());
                        }
                    }
                });
            }
        });
    }
}
