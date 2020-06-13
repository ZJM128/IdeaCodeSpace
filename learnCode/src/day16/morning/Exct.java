package day16.morning;

import java.util.Scanner;

public class Exct {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        while(true) {
            System.out.println("输入用户名");
            String userName=scanner.next();
            System.out.println("请输入密码");
            String password =scanner.next();

            try {
                logo(userName,password);
            } catch (LogoException e) {

                System.out.println(e.getMessage());
            }
        }
    }
    public  static void logo(String userName,String password){
        if(!"admin".equals(userName)){
            throw new LogoException("用户名有误");
        }

        if(!"123456".equals(password)){
            throw  new LogoException("密码有误");
        }

        System.out.println("登录成功");

    }
}
