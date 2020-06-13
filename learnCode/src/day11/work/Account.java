package day11.work;

public class Account {
    private static int accountId;
    private String password;
    private double balance;
    private double rate;
    private double minBalace;

    public Account() {
    }

    public Account(String password, double balance, double rate, double minBalace) {
        accountId=accountId+1;
        this.password = password;
        this.balance = balance;
        this.rate = rate;
        this.minBalace = minBalace;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMinBalace() {
        return minBalace;
    }

    public void setMinBalace(double minBalace) {
        this.minBalace = minBalace;
    }

    @Override
    public String toString() {
        return "Account{" +"accountId="+accountId+
                " password='" + password + '\'' +
                ", balance=" + balance +
                ", rate=" + rate +
                ", minBalace=" + minBalace +
                '}';
    }

    public static void main(String[] args) {
        Account account=new Account("123",1000,0.012,100);
        System.out.println(account.toString());
        Account account1=new Account("124",1020,0.018,200);
        System.out.println(account1.toString());
        Account account2=new Account("125",1030,0.019,300);
        System.out.println(account2.toString());
    }
}
