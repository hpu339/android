package com.example.loginv01.entity;

//记事本实体类
public class list_data {
    //属性
    private String title,time;

    //构造方法

    public list_data() {
    }

    public list_data(String title, String time) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}