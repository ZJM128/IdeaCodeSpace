/*
 * This class creates the program to test the banking classes.
 * It creates a new Bank, sets the Customer (with an initial balance),
 * and performs a series of transactions with the Account object.
 */
package com.atguigu.banking;


public class TestBanking {

  public static void main(String[] args) {

    Bank bank=Bank.getBanking();
    bank.addCustomer("李","四");
    CheckingAccount checkingAccount;
    checkingAccount = new CheckingAccount(200,500);
    Customer customer = bank.getCustomer(0);
    customer.setAccount(checkingAccount);
    try {
      checkingAccount.withdraw(150);
    } catch (OverdraftException e) {
      System.out.println(e.getMessage()+e.getDeficit());
    }
    checkingAccount.deposit(22.5);
    try {
      checkingAccount.withdraw(147.62);
    } catch (OverdraftException e) {
      System.out.println(e.getMessage()+e.getDeficit());
    }

    try {
      checkingAccount.withdraw(470.00);
    } catch (OverdraftException e) {
      System.out.println(e.getMessage()+e.getDeficit());
    }


  }
}
