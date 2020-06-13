package com.atguigu.test04.bean;

public class test04 {
    public static void main(String []args){
        Card card=new Card("黑桃","A");
        card.showCard();

        card.setColor("红桃");
        card.showCard();

        card.setNumber("K");
        card.showCard();
    }
}
