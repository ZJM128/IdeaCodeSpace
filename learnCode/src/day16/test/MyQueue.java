package day16.test;

import java.util.LinkedList;

/**
 * 堆栈:先进后出
 * 队列:先进先出
 */
public class MyQueue {

   private LinkedList list= new LinkedList();
    void add(Object value){
       list.add(value);
    }

    public Object get(){
       return  list.removeFirst();
    }

    Object get1(){
        return list.removeLast();
    }
}

class Test{
    public static void main(String[] args) {
        MyQueue queue=new MyQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);


        System.out.println(queue.get1());
        System.out.println(queue.get1());
        System.out.println(queue.get1());
    }
}
