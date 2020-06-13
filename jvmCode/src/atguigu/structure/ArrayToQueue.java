package atguigu.structure;

import java.util.Scanner;

public class ArrayToQueue {
    public static void main(String[] args) {
        boolean flag=true;
        SimulationQueue arr = new SimulationQueue(3);
        while (flag){
        Scanner sc = new Scanner(System.in);
        System.out.println("S(showDate) 查看数据");
        System.out.println("A(addDate) 添加数据");
        System.out.println("G(getDate) 取数据");
        System.out.println("Q(queryTopData) 查看头数据");
        System.out.println("E(exit)退出程序");
        char ch=sc.next().charAt(0);
            switch(ch){
                case 'S':
                    arr.showData();
                    break;
                case 'A':
                    System.out.println("请输入整数");
                    int num=sc.nextInt();
                    arr.addData(num);
                    break;
                case 'G':
                    try{
                        System.out.println("取出的数据是"+arr.getData());
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 'Q':
                    arr.queryTopData();
                    break;
                case 'E':
                    sc.close();
                    flag=false;

            }
        }


    }


}
// 声明一个类模拟队列

class SimulationQueue {
    private int maxSize ;
    private int[] arr;
    private  int real ;
    private  int font ;

    public SimulationQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr=new int[this.maxSize];
        this.real=-1;
        this.font=-1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        if (font == real) {
            return true;
        }
        return false;
    }

    // 判断队列是否满
    public boolean isFull() {
        // 数组从零开始的
        if (real == maxSize - 1) {
            return true;
        }
        return false;
    }

    // 往队列中添加数据
    public void addData(int value) {
        // 先判断是否满了
        if (isFull()) {
            System.out.println("队列已经满 不能添加");
            return;
        }
        arr[++real] = value;

    }

    // 从队列中取数据
    public int getData() {
        // 先判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,没有数据可以取");
        }
        return arr[++font];
    }

    //查看全部数据
    public void showData(){
        for (int i=0;i<arr.length;i++){
            System.out.println("数据为:"+arr[i]);
        }
    }
    // 查看队列第一个数据
    public void queryTopData() {
        // 需要队列判断是否为空
        if (isEmpty()) {
            System.out.println("队列为空 没有数据");
            return;
        }

        System.out.println("第一个数据为:" + arr[font]);

    }
}