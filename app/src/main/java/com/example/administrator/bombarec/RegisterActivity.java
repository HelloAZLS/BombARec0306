package com.example.administrator.bombarec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.bombarec.domain.MyUser;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/3/9.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private String AppKey = "686dd6cbf2d3f86022e7ca491b58504f";
    private EditText etName;
    private EditText etPassWord;
    private EditText etPhone;
    private EditText etCode;
    private Button btnCode;
    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);
        Bmob.initialize(this, AppKey);
        initViews();

    }

    private void initViews() {
        etName = (EditText) findViewById(R.id.et_name);
        etPassWord = (EditText) findViewById(R.id.et_pwd);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etCode = (EditText) findViewById(R.id.et_code);
        btnCode = (Button) findViewById(R.id.btn_code);
        btnReg = (Button) findViewById(R.id.btn_reg_reg);
        btnCode.setOnClickListener(this);
        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_code:
                BmobSMS.requestSMSCode("13711197343", "叶双贵", new QueryListener<Integer>() {

                    @Override
                    public void done(Integer smsId, BmobException ex) {
                        if (ex == null) {//验证码发送成功
                            Toast.makeText(RegisterActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.btn_reg_reg:
                final BmobUser bu = new BmobUser();
                bu.setUsername(etName.getText().toString().trim());
                bu.setPassword(etPassWord.getText().toString().trim());
                bu.setMobilePhoneNumber(etPhone.getText().toString().trim());
                bu.setMobilePhoneNumberVerified(true);
//注意：不能用save方法进行注册
                BmobSMS.verifySmsCode("13711197343", etCode.getText().toString().trim(), new UpdateListener() {

                    @Override
                    public void done(BmobException ex) {
                        if (ex == null) {//短信验证码已验证成功
                            bu.signUp(new SaveListener<MyUser>() {
                                @Override
                                public void done(MyUser s, BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "注册失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Log.i("smile", "验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                        }
                    }
                });


                break;
        }
    }
}
