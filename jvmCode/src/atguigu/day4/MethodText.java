package atguigu.day4;
/**
 * 1,编写程序，声明一个method方法，在方法中打印一个10*8的矩形，在main方法中调用该方法。
 * 2,修改上一个程序，在method方法中，除打印一个10*8的矩形外，再计算该矩形的面积，并将其作为方法返回值。
 * 在main方法中调用该方法，接收返回的面积值并打印
 * 3,修改上一个程序，在method方法提供m和n两个参数，方法中打印一个m*n的矩形，并计算该矩形的面积， 将其作为方法返回值。
 * 在main方法中调用该方法，接收返回的面积值并打印。
 */

public class MethodText {
    public static void main(String []args){
        print();
        System.out.println("面积是"+print2());

        System.out.println("面积是"+print(4, 5));
    }
    public static void print(){
        for(int i=0;i<10;i++){
            for(int j=0;j<8;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static int print2(){
        return 8*10;
    }

    public static int print(int n,int m){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print("@");
            }
            System.out.println();
        }
        return n*m;
    }
}
