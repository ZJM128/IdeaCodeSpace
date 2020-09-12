package com.atguigu.banking;

public class CustomerReport {
    public static void main(String[] args) {
        Bank bank=Bank.getBanking();
        bank.addCustomer("张","三");
      SavingAccount savingAccount= new SavingAccount(1000,23);



    }
}
