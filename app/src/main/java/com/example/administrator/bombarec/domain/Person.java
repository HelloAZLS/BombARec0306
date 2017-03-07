package com.example.administrator.bombarec.domain;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/6.
 */

public class Person extends BmobObject {
    private String name;
    private  int age;
    private  String address;
    private  String mBit;

    public Person(String name, int age, String address, String mBit) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.mBit = mBit;
    }
    //private  String objectId;

    public Person() {
    }

    /*public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }*/

    public String getmBit() {
        return mBit;
    }

    public void setmBit(String mBit) {
        this.mBit = mBit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*@Override
    public String getObjectId() {
        return objectId;
    }

    @Override
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }*/
}
