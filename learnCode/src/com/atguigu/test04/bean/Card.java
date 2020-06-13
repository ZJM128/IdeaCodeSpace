package com.atguigu.test04.bean;

public class Card {
    private String color;
    private String number;
    public Card(){}
    public Card(String color,String number){
        this.color=color;
        this.number=number;
    }
    public void setColor(String color){
        this.color=color;
    }

    public void setNumber(String number){
        this.number=number;
    }

    public String getColor(){
        return color;
    }
    public String getNumber(){
        return number;
    }

    public void showCard(){
        System.out.println(color+number);
    }
}
