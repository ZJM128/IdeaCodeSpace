package day26.train;

import java.util.Objects;

/*
*@Description:模拟银行取钱的问题
1.定义一个Account类
    1）该Account类封装了账户编号（String）和余额（double）两个属性
    2）设置相应属性的getter和setter方法
    3）提供无参和有两个参数的构造器
    4）系统根据账号判断与用户是否匹配，需提供hashCode()和equals()方法的重写
2.提供两个取钱的线程类：小明、小明’s wife
    1）提供了Account类的account属性和double类型的取款额的属性
    2）提供带线程名的构造器
    3）run()方法中提供取钱的操作
3.在主类中创建线程进行测试。考虑线程安全问题。
*@author:zhijm
*@Date:2020/6/26 15:08
*
*/
public class Banking {
    public static void main(String[] args) {
        Account1 account=new Account1("122",10000);
        Consumer consumer=new Consumer("小明",account,1220);
        Consumer consumer1=new Consumer("小明老婆",account,20000);
        consumer.start();
        consumer1.start();
    }
}
class Consumer extends Thread{
    private Account1 account;
    private double atm;

    public Consumer(String name, Account1 account, double atm) {
        super(name);
        this.account = account;
        this.atm = atm;
    }

    @Override
    public void run() {
        account.getMoney(atm);
    }
}
class Account1{
    private String accountId;
    private double balance;
    public Account1() {
    }
    public Account1(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public synchronized void getMoney(double atm){

        if(balance>=atm){
            balance-=atm;
            System.out.println("取了"+atm+" 剩余余额"+balance);
        }else{
            System.out.println("余额不足,剩余额数"+balance+" 要取"+atm);
        }

    }
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account1 account1 = (Account1) o;
        return Double.compare(account1.balance, balance) == 0 &&
                Objects.equals(accountId, account1.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance);
    }
}