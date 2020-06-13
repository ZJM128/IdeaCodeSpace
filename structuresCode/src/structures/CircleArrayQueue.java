package structures;

import java.util.Scanner;

public class CircleArrayQueue {
    public static void main(String[] args) {
        boolean flag=true;
        CircleArray arr = new CircleArray(4);
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
                    arr.addDate(num);
                    break;
                case 'G':
                    try{
                        System.out.println("取出的数据是"+arr.getDate());
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

class CircleArray{
    private int maxSize;// 表示数组的最大容量
    private int front;//就是指向队列的第一个元素 也就是说arr[front]
    private int rear;// 指向队列的最后一个元素的后一个位置,因为希望空一个位置
    private int []arr;

    public CircleArray(int maxSize){
        this.maxSize=maxSize;
        arr=new int[maxSize];
    }

    // 判断是否为空
    private boolean isEmpty(){
        return front==rear;
    }
    // 判断是否满了
    private boolean isFull(){
        return (rear+1)%maxSize==front;
    }
    // 添加数据
    public void addDate(int value){
        // 判断是否满了
        if(isFull()){
            System.out.println("队列已经满了");
            return ;
        }

        arr[rear]=value;
        rear=(rear+1)%maxSize;

    }

    // 取数据
    public int getDate(){
        // 判断是否为空
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int a=arr[front];
        // 下一个
        front=(front+1)%maxSize;
        return a;
    }

    // 查看头元素
    public void queryTopData(){
        if(isEmpty()){
            throw new RuntimeException("队列为空");
        }
        System.out.println("头元素为"+arr[front]);
    }

    // 查看队列元素
    public void showData(){
        for (int i=front;i<front+(rear+maxSize-front)%maxSize;i++){
            System.out.println("数据为:"+arr[i]);
        }
    }
}