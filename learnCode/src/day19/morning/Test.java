package day19.morning;

public class Test {
    @org.junit.Test
    public void test(){
        print(1);
    }

    public void print(int i){
        if(i>3){
            return ;
        }
        System.out.println("好好学习");
        print(i+1);
    }
}
