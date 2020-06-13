package day11;

import java.util.Date;

public class equalsTest {
    public static void main(String[] args) {
        Date date=new Date();
        String str = "11";
        if(str.equals(date)){
            System.out.println("比较数据和数据类型");
        }else{
            System.out.println("比较数据和数据类型======");
        }
    }
}
