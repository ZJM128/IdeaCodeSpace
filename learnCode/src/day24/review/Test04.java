package day24.review;

import java.util.Random;

/*
*@Description:​
* 1、创建一个银行账户类，
​	（1）属性：账号，余额，
​	（2）get/set，
​	（3）toString()：返回：账户：xxx，余额：xxx
​	2、创建一个丈夫类
​		负责往里存钱，每次存款[0,10000)以内不等
​	3、创建一个妻子类
​		负责取钱，每次取款[0,10000)以内不等，如果余额不足，要等丈夫存够了才能取
*@author:zhijm
*@Date:2020/6/24 8:00
*/
public class Test04 {
    public static void main(String[] args) {
        Account account=new Account("1112",0.0);
        Husband husband=new Husband(account);
        Wife wife =new Wife(account);
        new Thread(wife).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(husband).start();
    }

}
class Husband  implements Runnable{
    private Account account;

    public Husband(Account account) {
        this.account = account;
    }
    @Override
    public void run() {
        Random random =new Random();
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized ("A") {
                double balance = account.getBalance();
                System.out.println("丈夫开始存钱,目前账户状态:账户:" + account.getId() + ",余额" + account.getBalance());
                double money = random.nextInt(10000);
                System.out.println("本次丈夫存钱:" + money);
                account.setBalance(balance + money);
                System.out.println("丈夫存钱结束,目前账户状态:账户:" + account.getId() + ",余额" + account.getBalance());
                "A".notify();
            }
        }
    }
}
class Wife implements Runnable{
    private Account account;
    public Wife(Account account) {
        this.account = account;
    }
    @Override
    public void run() {
        Random random =new Random();
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized ("A") {
                double balance = account.getBalance();
                double money = random.nextInt(10000);
                if (balance < money) {
                    System.out.println("妻子想取:" + money + ",但余额不足,等待....");
                    try {
                        "A".wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("妻子开始取钱,目前账户状态:账户:" + account.getId() + ",余额" + account.getBalance());
                    System.out.println("本次妻子取钱:" + money);
                    account.setBalance(balance - money);
                    System.out.println("妻子取钱结束,目前账户状态:账户:" + account.getId() + ",余额" + account.getBalance());
                }
            }
        }
    }
}

class Account{
    private String id;
    private double balance;


    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}