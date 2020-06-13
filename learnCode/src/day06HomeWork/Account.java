package day06HomeWork;

public class Account {
    private int id;
    private  double balance;
    private double annuallnterestRate;
    public Account(int id,double balance,double annuallnterestRate){
        this.id=id;
        this.balance=balance;
        this.annuallnterestRate=annuallnterestRate;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setBalance(double balance){
        this.balance=balance;
    }
    public void setAnnuallnterestRate(double annualnterestRate){
        this.annuallnterestRate=annualnterestRate;
    }

    public int getId(){
        return id;
    }

    public double getBalance(){
        return balance;
    }

    public double getAnnuallnterestRate(){
        return annuallnterestRate;
    }

    public void withdraw(double amount){
        if(amount>balance){
            System.out.println("您的余额不足");
        }else{
            balance-=amount;
            System.out.println("成功取出"+amount);
        }
    }

    public void deposit(double amount){
        balance+=amount;
        System.out.println("成功存入"+amount);
    }
}
