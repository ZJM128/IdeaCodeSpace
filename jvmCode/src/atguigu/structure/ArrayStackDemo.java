package atguigu.structure;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        // 实例化栈
        ArrayStack arrayStack=new ArrayStack(5);
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while(flag){
            System.out.println();
            System.out.println("-----------操作-------------");
            System.out.println("\t\t请输入相应的操作");
            System.out.println("\t\tA(push),入栈");
            System.out.println("\t\tG(pop),出栈");
            System.out.println("\t\tS(showStack),遍历栈");
            System.out.println("\t\tE(exit),退出");

            char ch=sc.next().charAt(0);
             switch(ch){
                 case 'A':
                     System.out.print("请输入整数");
                     int value=sc.nextInt();
                     arrayStack.push(value);
                     break;
                 case 'G':
                     try{
                         System.out.println("取出的数据是:"+ arrayStack.pop());

                     }catch(Exception e){
                         System.out.println(e);
                     }
                     System.out.println("抛异常了 还能运行么");
                     break;
                 case 'S':
                     arrayStack.showStack();;
                     break;
                 case 'E':
                     sc.close();
                     flag=false;
                     break;

             }

        }
    }
}
//  定义一个栈
class ArrayStack{
    private int maxSize;
    private int top=-1;
    private int [] arr;
    public ArrayStack(int maxSize){
        this.maxSize=maxSize;
        arr=new int[maxSize];
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return top==-1;
    }

    /**
     * 判断是否满了
     * @return
     */
    public boolean isFell(){
        return top==maxSize-1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value){
        // 先判断是否满了
        if(isFell()){
            System.out.println("栈已满");
            return;
        }
        // 入栈
        top++;
        arr[top]=value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈为空");
        }
        int result=arr[top];
        top--;
        return result;
    }

    /**
     * 遍历栈
     */
    public void showStack(){
        if(isEmpty()){
            System.out.println("栈为空");
            return;
        }
        for(int i=top;i>=0;i--) {
            System.out.println(arr[i]);
        }
    }
}