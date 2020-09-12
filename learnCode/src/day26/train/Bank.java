package day26.train;

/*
*@Description:银行有一个账户。
	有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
*@author:zhijm
*@Date:2020/6/26 13:48
*/
public class Bank {
    public static void main(String[] args) {
        Account account=new Account();
        new user("李四",account).start();
        new user("张三",account).start();
    }
}

class user extends Thread{
    private Account account;

    public user(String name, Account account) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for(int i=0;i<3;i++){
            account.deposit(1000);
        }
    }
}

/**
 * 账户类
 */
class Account{
    private double balance;
    public synchronized void deposit(double atm){
        if(atm>0)
            balance+=atm;
        System.out.println(Thread.currentThread().getName()+"存了"+atm+"当前余额为"+balance);
    }
}