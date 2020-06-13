package recursion;

public class recursionDemo {
    public static void main(String []args){
        //recursionTest(4);
        System.out.println(factorial(2));
    }
    public static void recursionTest(int i){
        if(i>2){
            recursionTest(i-1);// 递归要有一个结束的条件
        }
        System.out.println(i);
    }

    public static int  factorial(int n){
        if(n==1){//递归要有一个结束的条件
            return 1;
        }
        return factorial(n-1)*n;//递归条件

    }
}
