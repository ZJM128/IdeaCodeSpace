package day19.review;

import org.junit.Test;

public class Recursion {
    @Test
    public void test01(){
        System.out.println(add(100));
    }
    public int add(int i){
        if(i>0){
            return i+add(i-1);// 递归循环条件
        }else{
            return 0;// 有递归的终止条件
        }
    }
}
