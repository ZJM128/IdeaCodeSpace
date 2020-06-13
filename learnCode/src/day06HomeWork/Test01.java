package day06HomeWork;

public class Test01 {
    public static void main(String []args){
        Customer c=new Customer("Jnae","Smith");
        Account account=new Account(1000,2000,1.23);
        c.setAccount(account);
        Account jsC=c.getAccount();
        jsC.deposit(100);
        jsC.withdraw(960);
        jsC.withdraw(2000);

        System.out.println("Customer["+c.getLastName()+","+c.getFirstName()+"] has a account" +
                ":id is"+jsC.getId()+"annualInterestRate  is "+jsC.getAnnuallnterestRate()+"%"
        +", balance id "+jsC.getBalance());

    }
}
