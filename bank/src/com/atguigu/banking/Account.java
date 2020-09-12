package com.atguigu.banking;

public class Account {
    protected double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public double getBalance() {
        return balance;
    }


    /**
     * 存款
     * @param amt 存款金额
     */
    public boolean deposit(double amt){
        balance+=amt;
        return true;
    }

    /**
     * 取款
     * @param amt 取款金额
     */
    public void withdraw(double amt) throws OverdraftException {
        if(balance<amt) {
          throw new OverdraftException("余额不足,不足数额:",amt-balance);
        }
        balance -= amt;

    }
}
