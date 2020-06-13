package atguigu.Exer;

import java.util.Scanner;

public class SwitchText3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("请输入小写字母");
        char ch=sc.next().charAt(0);

        switch(ch){
            case 'a':
                System.out.println((char)('a'-('a'-'A')));
                break;
            case 'b':
                System.out.println((char)('b'-('b'-'B')));
                break;
            case 'c':
                System.out.println((char)('c'-('c'-'C')));
                break;
            case 'd':
                System.out.println((char)('d'-('d'-'D')));
                break;
            case 'e':
                System.out.println((char)('e'-('e'-'E')));
                break;
            default:
                System.out.println("other");
                break;
        }
    }
}
