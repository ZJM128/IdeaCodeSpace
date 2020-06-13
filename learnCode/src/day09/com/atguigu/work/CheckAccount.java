package day09.com.atguigu.work;

public class CheckAccount extends Account {
    private double overDraft;
    public CheckAccount(int id, double balance, double annualInterestRate,double overDraft) {
        super(id, balance, annualInterestRate);
        this.overDraft=overDraft;
    }

    public double getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(double overDraft) {
        this.overDraft = overDraft;
    }
    public void withDraw(double amount ){
        /*double balance = getBalance();
        double sum= overDraft+balance;
        if(sum>amount){
            setBalance(balance-amount);
            balance = getBalance();
            if(balance<0) {
                overDraft += balance;
                setBalance(0);
            }


        }else{
            System.out.println("您的已经透支了");
        }*/

        if(getBalance()>=amount){
            setBalance(getBalance()-amount);
        }else{
            double needOverDraft=amount-getBalance();
            if(needOverDraft>overDraft){
                System.out.println("你已经透支");
            }else{
                setOverDraft(overDraft-needOverDraft);
                setBalance(0);
            }
        }


    }
}
