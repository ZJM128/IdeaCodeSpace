package day14.review;

import java.util.Scanner;

public class Test09 {
    public static void main(String[] args) {
        User[]users=new User[3];
        users[0]=new User("jack","123");
        users[1]=new User("rose","236");
        users[2]=new User("tom","123");
        for (User user : users) {
            System.out.println(user);
        }

        checkUser(users);

    }

    public static void checkUser(User[]users){
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入用户名");
        String name = scanner.next();
        System.out.println("请输入密码");
        String password=scanner.next();

        boolean nameFlag=false;
        boolean passwordFlag=false;
        for (User user : users) {
            if(user.getName().equals(name)){
                nameFlag=true;
            }
            if(user.getPassword().equals(password)){
                passwordFlag=true;
            }
        }
        if(!nameFlag){
            System.out.println("用户名错误");
            return;
        }
        if(!passwordFlag){
            System.out.println("密码错误");
            return;
        }
        System.out.println("登录成功");

    }
}
class User{
    private String name;
    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return name+"---"+password;
    }
}