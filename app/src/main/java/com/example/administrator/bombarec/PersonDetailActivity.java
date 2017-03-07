package com.example.administrator.bombarec;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Administrator on 2017/3/6.
 */

public class PersonDetailActivity extends AppCompatActivity {

    private EditText mEdName;
    private EditText mEdAge;
    private EditText mEdAddr;
    private Button btnAdd;
    private ImageView ivTouP;
    private int RESULT_MAIN = 10010;
    private int RESULT_ADA = 11111;
    private Intent intent;
    private int imags[] = {R.mipmap.mao, R.mipmap.mao1, R.mipmap.mao2, R.mipmap.mao3,};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initViews();

    }

    private void initViews() {
        mEdName = (EditText) findViewById(R.id.et_name);
        mEdAge = (EditText) findViewById(R.id.et_age);
        mEdAddr = (EditText) findViewById(R.id.et_addr);
        btnAdd = (Button) findViewById(R.id.btn_person_add);
        ivTouP = (ImageView)findViewById(R.id.iv_toux);
        Bundle bundle = getIntent().getExtras();
        String request = bundle.getString("REQUEST");
        //修改
        if (request.equals("CESHI")) {
            btnAdd.setText("修改");
            String oldName = bundle.getString("name");
            String oldAge = bundle.getString("age");
            String oldAddr = bundle.getString("addr");
            final int position = bundle.getInt("position");
            String stringBit = bundle.getString("stringBit");
            Bitmap bitmap = BitmapUtil.string2Bitmap(stringBit);
            ivTouP.setImageBitmap(bitmap);
            mEdName.setText(oldName);
            mEdAge.setText(oldAge);
            mEdAddr.setText(oldAddr);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    senfData();
                    intent.putExtra("position", position);
                    setResult(RESULT_ADA, intent);
                    finish();
                }
            });
        } else if (request.equals("MAIN")) {
            //增加
            Random random = new Random();
            int dex = random.nextInt(imags.length);
            ivTouP.setImageResource(imags[dex]);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    senfData();
                    setResult(RESULT_MAIN, intent);
                    finish();
                }

            });
        }
    }

    private void senfData() {
        String name = mEdName.getText().toString().trim();
        String age = mEdAge.getText().toString().trim();
        String addr = mEdAddr.getText().toString().trim();
        String bitmap2String = BitmapUtil.bitmap2String(ivTouP);
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age) && !TextUtils.isEmpty(addr)) {

            intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("age", age);
            intent.putExtra("addr", addr);
            intent.putExtra("mBitmap", bitmap2String);
        }
    }
}
