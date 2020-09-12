package com.atguigu.banking;

public class CheckingAccount extends Account{
    private double overdraftProtection;

    public CheckingAccount(double balance, double overdraftProtection) {
        super(balance);
        this.overdraftProtection = overdraftProtection;
    }

    public double getOverdraftProtection() {
        return overdraftProtection;
    }

    public void setOverdraftProtection(double overdraftProtection) {
        this.overdraftProtection = overdraftProtection;
    }


    public void withdraw(double amt)throws OverdraftException {
        if(amt<0){
            throw new OverdraftException("不能为负数",amt);
        }
        double num=balance-amt;
        if(num>0){
            balance-=amt;

        }else{
           if(overdraftProtection+num>=0){
               overdraftProtection+=num;
               balance=0;
              // throw new OverdraftException("no overdraft protection");
           }else{
               throw new OverdraftException("Insufficient funds for overdraft protection",amt);
           }
        }

    }
}
