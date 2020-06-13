package day09.com.atguigu.work;

import org.junit.Test;

public class AccountTest {

    @Test
    public void test01(){
        Account account=new Account(112,20000,0.045);
        account.withDraw(2500);
        account.deposit(3000);
        double rate = account.getAnnualInterestRate();
        System.out.println("您的余额为"+account.getBalance());
        System.out.println("月利率为:"+rate);
    }
    @Test
    public void test02(){
        CheckAccount checkAccount=new CheckAccount(112,20000,0.045,5000);
        checkAccount.withDraw(5000);
        System.out.println("余额"+checkAccount.getBalance());
        System.out.println("可透支额"+checkAccount.getOverDraft());

        checkAccount.withDraw(18000);
        System.out.println("余额"+checkAccount.getBalance());
        System.out.println("可透支额"+checkAccount.getOverDraft());

        checkAccount.withDraw(3000);
        System.out.println("余额"+checkAccount.getBalance());
        System.out.println("可透支额"+checkAccount.getOverDraft());


    }

}
