package day19.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Test07 {
    public static void main(String[] args) throws FileNotFoundException {
        test02();
    }
    private static void test01() throws FileNotFoundException {
        Scanner scanner=new Scanner(System.in);
        PrintStream pe=new PrintStream("D:\\testoi\\word.txt");
        for(int i=0;i<3;i++){
            System.out.println("请输入"+(i+1)+"句话");
            String string = scanner.nextLine();
            pe.println(string);
        }
        scanner.close();
        pe.close();
    }

    private static void test02() throws FileNotFoundException {
        Scanner in=new Scanner(new File("D:\\testoi\\word.txt"));
        while(in.hasNextLine()){
            System.out.println(in.nextLine());
        }
        in.close();
    }
}
