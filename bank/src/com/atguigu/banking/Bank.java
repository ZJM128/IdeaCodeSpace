package com.atguigu.banking;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer>customers;
    private int numberOfCustomer;
    private static Bank bank=null;
    private Bank() {
        customers=new ArrayList<>();
    }
    public static Bank getBanking(){
        if(bank==null) {
            synchronized (Bank.class) {
                if (bank == null) {
                    bank = new Bank();
                }
            }
        }
        return bank;
    }

    /**
     * 添加客户
     * @param f 姓
     * @param l 名
     */
    public void addCustomer(String f,String l){

        customers.add(new Customer(f,l));
    }

    public int getNumberOfCustomer() {
        return numberOfCustomer;
    }

    public void setNumberOfCustomer(int numberOfCustomer) {
        this.numberOfCustomer = numberOfCustomer;
    }

    /**
     * 获取有多少个客户
     * @return 客户的个数
     */
    public int getNunOfCustomer(){
        return numberOfCustomer;
    }

    public Customer getCustomer(int index){
        if(index<0 || index>customers.size()){
            return null;
        }
        return customers.get(index);
    }
}
