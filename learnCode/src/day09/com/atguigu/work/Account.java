package day09.com.atguigu.work;

public class Account {
    private int id;
    private double balance;
    private double annualInterestRate;

    public Account(int id,double balance,double annualInterestRate){
        this.id=id;
        this.balance=balance;
        this.annualInterestRate=annualInterestRate;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public double getMonthlyInterest(){
        return annualInterestRate/12;
    }
    public void withDraw(double amount){
        if(balance<amount){
            System.out.println("余额不足");
            System.out.println("您的余额为"+balance);
            return;
        }
        balance-=amount;
    }
    public void deposit(double amount){
            balance+=amount;
    }



}
