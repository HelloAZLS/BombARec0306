package com.example.administrator.bombarec.domain;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/3/9.
 */

public class MyUser extends BmobUser {
    private String username;
    private String password;
   // private String mobilePhoneNumber;

    public MyUser() {
    }

    public MyUser(String username, String password, String mobilePhoneNumber) {
        this.username = username;
        this.password = password;
       // this.mobilePhoneNumber = mobilePhoneNumber;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

   /* @Override
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    @Override
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }*/
}
