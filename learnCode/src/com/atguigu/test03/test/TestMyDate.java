package com.atguigu.test03.test;

import com.atguigu.test03.bean.MyDate;

public class TestMyDate {
    public static void main(String []args){
        MyDate myDate=new MyDate(1900,1,1);
        myDate.showDate();
        System.out.println(myDate.getYear()+"年"+(myDate.isLeapYear()?"是":"不是")+"闰年");
    }
}
