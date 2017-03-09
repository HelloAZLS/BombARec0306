package com.example.administrator.bombarec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2017/3/9.
 */

public class StartActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnReg;
    private Button btnLogn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
       initViews();

    }

    private void initViews() {
        btnReg = (Button) findViewById(R.id.btn_s_reg);
        btnLogn = (Button) findViewById(R.id.btn_s_logn);
        btnLogn.setOnClickListener(this);
        btnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_s_reg:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_s_logn:
                Intent intent1 = new Intent(this,LognActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
