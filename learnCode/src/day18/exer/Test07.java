package day18.exer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test07 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        boolean flag=true;
        List<Studen>list=new ArrayList<>();
        do{
            System.out.println("选择(1,录入;0,退出)");
            char c = scanner.next().charAt(0);
            switch(c){
                case '1':
                    System.out.print("姓名:");
                    String name = scanner.next();
                    System.out.print("年龄:");
                    int age  = scanner.nextInt();
                    list.add(new Studen(name,age));
                    break;
                case '0':
                    flag=false;
                    scanner.close();;
                    break;
            }

        }while(flag);

        for(Studen studen:list){
            System.out.println(studen);
        }
    }
}
class Studen{
    private String name;
    private int age;

    public Studen(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Studen{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}