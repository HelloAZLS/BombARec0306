package com.example.administrator.bombarec.domain;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/3/6.
 */

public class Person extends BmobObject {
    private String name;
    private  int age;
    private  String address;

    public Person() {
    }

    public Person(String name, int age, String addr) {
        this.name = name;
        this.age = age;
        this.address = address;
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
}
